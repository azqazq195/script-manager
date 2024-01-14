package com.moseoh.deploymanager.domain.auth.application.entity.repository

import com.moseoh.deploymanager.domain.auth.application.entity.Token
import com.moseoh.deploymanager.domain.auth.application.exception.TokenNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface TokenRepository : JpaRepository<Token, Long> {
    fun findByAccessToken(accessToken: String): Optional<Token>
    fun findByRefreshToken(refreshToken: String): Optional<Token>
}

fun TokenRepository.getEntityByAccessToken(accessToken: String): Token =
    findByAccessToken(accessToken).orElseThrow(::TokenNotFoundException)

fun TokenRepository.getEntityByRefreshToken(refreshToken: String): Token =
    findByRefreshToken(refreshToken).orElseThrow(::TokenNotFoundException)