package com.moseoh.deploymanager.common.environment

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "jwt")
data class JwtProperties(
    val accessSecret: String,
    val accessTokenExpireTime: Long,
    val refreshSecret: String,
    val refreshTokenExpireTime: Long,
)