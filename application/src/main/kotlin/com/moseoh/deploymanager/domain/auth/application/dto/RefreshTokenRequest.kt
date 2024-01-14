package com.moseoh.deploymanager.domain.auth.application.dto

import jakarta.validation.constraints.NotBlank

data class RefreshTokenRequest(
    @field:NotBlank
    val accessToken: String,

    @field:NotBlank
    val refreshToken: String
)