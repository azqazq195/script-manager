package com.moseoh.deploymanager.domain.auth.application.dto

import jakarta.validation.constraints.NotBlank

data class SignInRequest(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    val password: String,
)