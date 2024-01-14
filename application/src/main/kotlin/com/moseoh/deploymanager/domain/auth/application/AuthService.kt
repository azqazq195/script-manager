package com.moseoh.deploymanager.domain.auth.application

import com.moseoh.deploymanager.domain.auth.application.dto.RefreshTokenRequest
import com.moseoh.deploymanager.domain.auth.application.dto.SignInRequest
import com.moseoh.deploymanager.domain.auth.application.dto.TokenResponse
import com.moseoh.deploymanager.domain.auth.application.exception.PasswordNotMatchException
import com.moseoh.deploymanager.domain.user.application.entity.UserRepository
import com.moseoh.deploymanager.domain.user.application.entity.getEntityByName
import org.springframework.security.core.Authentication
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class AuthService(
    private val passwordEncoder: PasswordEncoder,
    private val userRepository: UserRepository,
    private val jwtProvider: JwtProvider,
) {
    @Transactional
    fun signIn(signInRequest: SignInRequest): TokenResponse {
        val user = userRepository.getEntityByName(signInRequest.name)
        check(
            passwordEncoder.matches(signInRequest.password, user.password),
            ::PasswordNotMatchException
        )
        return jwtProvider.create(user)
    }

    @Transactional
    fun signOut(auth: Authentication) {
        val accessToken = auth.credentials as String
        jwtProvider.deleteByAccessToken(accessToken)
    }

    @Transactional
    fun refresh(tokenRequest: RefreshTokenRequest): TokenResponse {
        return jwtProvider.refresh(tokenRequest)
    }
}