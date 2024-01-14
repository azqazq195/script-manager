package com.moseoh.deploymanager.domain.auth.application.entity.repository

import com.moseoh.deploymanager.domain.auth.application.entity.BlackListToken
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface BlackListTokenRepository : JpaRepository<BlackListToken, Long> {
    fun existsByAccessToken(accessToken: String): Boolean
}