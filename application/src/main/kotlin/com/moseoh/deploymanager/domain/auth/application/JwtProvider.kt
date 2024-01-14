package com.moseoh.deploymanager.domain.auth.application

import com.moseoh.deploymanager.common.environment.JwtProperties
import com.moseoh.deploymanager.common.logger
import com.moseoh.deploymanager.domain.auth.application.dto.RefreshTokenRequest
import com.moseoh.deploymanager.domain.auth.application.dto.TokenResponse
import com.moseoh.deploymanager.domain.auth.application.entity.BlackListToken
import com.moseoh.deploymanager.domain.auth.application.entity.Token
import com.moseoh.deploymanager.domain.auth.application.entity.repository.BlackListTokenRepository
import com.moseoh.deploymanager.domain.auth.application.entity.repository.TokenRepository
import com.moseoh.deploymanager.domain.auth.application.entity.repository.getEntityByAccessToken
import com.moseoh.deploymanager.domain.auth.application.entity.repository.getEntityByRefreshToken
import com.moseoh.deploymanager.domain.auth.application.exception.TokenNotMatchException
import com.moseoh.deploymanager.domain.user.application.entity.User
import com.moseoh.deploymanager.domain.user.application.entity.UserRepository
import com.moseoh.deploymanager.domain.user.application.entity.getEntityById
import io.jsonwebtoken.*
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import io.jsonwebtoken.security.SignatureException
import jakarta.annotation.PostConstruct
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.security.Key
import java.util.*

@Service
class JwtProvider(
    private val jwtProperties: JwtProperties,
    private val userRepository: UserRepository,
    private val tokenRepository: TokenRepository,
    private val blackListTokenRepository: BlackListTokenRepository,
) {
    private val log = logger()
    private lateinit var accessKey: Key
    private lateinit var refreshKey: Key

    companion object {
        const val ROLE = "role"
    }

    @PostConstruct
    protected fun init() {
        this.accessKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.accessSecret))
        this.refreshKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtProperties.refreshSecret))
    }

    @Transactional
    fun create(user: User): TokenResponse {
        val token = Token(
            userId = user.id,
            accessToken = createAccessToken(user),
            refreshToken = createRefreshToken(user),
            expiration = Date(System.currentTimeMillis() + jwtProperties.refreshTokenExpireTime).time
        )
        return tokenRepository.save(token).let(::TokenResponse)
    }

    @Transactional
    fun deleteByAccessToken(accessToken: String) {
        setBlackList(accessToken)

        val token = tokenRepository.getEntityByAccessToken(accessToken)
        tokenRepository.delete(token)
    }

    @Transactional
    fun refresh(tokenRequest: RefreshTokenRequest): TokenResponse {
        val token = tokenRepository.getEntityByRefreshToken(tokenRequest.refreshToken)
        check(token.accessToken == tokenRequest.accessToken, ::TokenNotMatchException)
        deleteByAccessToken(token.accessToken)
        return userRepository.getEntityById(token.userId).let(::create)
    }

    fun getAuthentication(accessToken: String): Authentication {
        val claims = getClaims(accessToken)
        val id = claims.subject.toLong()
        val authorities: Collection<GrantedAuthority> =
            claims[ROLE].toString()
                .split(",")
                .filter { it.isNotEmpty() }
                .map { SimpleGrantedAuthority("ROLE_$it") }
        return UsernamePasswordAuthenticationToken(id, accessToken, authorities)
    }

    fun validateToken(accessToken: String): Boolean {
        if (blackListTokenRepository.existsByAccessToken(accessToken)) {
            log.debug("black list token: {}", accessToken);
            return false
        }

        try {
            getClaims(accessToken)
            return true
        } catch (e: SignatureException) {
            log.debug("Invalid JWT signature: {}", e.message);
        } catch (e: MalformedJwtException) {
            log.debug("Invalid JWT token: {}", e.message);
        } catch (e: ExpiredJwtException) {
            log.debug("JWT token is expired: {}", e.message);
        } catch (e: UnsupportedJwtException) {
            log.debug("JWT token is unsupported: {}", e.message);
        } catch (e: IllegalArgumentException) {
            log.debug("JWT claims string is empty: {}", e.message);
        } catch (e: Exception) {
            log.debug("JWT token is invalid: {}", e.message);
        }

        return false
    }

    private fun createAccessToken(user: User): String {
        val now = Date()
        val expiration = Date(now.time + jwtProperties.accessTokenExpireTime)

        return Jwts.builder()
            .setSubject(user.id.toString())
            .claim(ROLE, user.role)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(accessKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun createRefreshToken(user: User): String {
        val now = Date()
        val expiration = Date(now.time + jwtProperties.refreshTokenExpireTime)

        return Jwts.builder()
            .setSubject(user.id.toString())
            .claim(ROLE, user.role)
            .setIssuedAt(now)
            .setExpiration(expiration)
            .signWith(refreshKey, SignatureAlgorithm.HS256)
            .compact()
    }

    private fun setBlackList(accessToken: String) {
        val claims = getClaims(accessToken)
        val expiration = claims.expiration.time
        blackListTokenRepository.save(BlackListToken(accessToken, expiration))
    }

    private fun getClaims(accessToken: String): Claims {
        return Jwts.parserBuilder()
            .setSigningKey(accessKey)
            .build()
            .parseClaimsJws(accessToken)
            .body
    }
}