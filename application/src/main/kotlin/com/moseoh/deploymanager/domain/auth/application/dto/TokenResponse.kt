package com.moseoh.deploymanager.domain.auth.application.dto

import com.moseoh.deploymanager.domain.auth.application.entity.Token

data class TokenResponse(
    val accessToken: String,
    val refreshToken: String
) {
    constructor(token: Token) : this(
        accessToken = token.accessToken,
        refreshToken = token.refreshToken
    )
}