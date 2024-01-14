package com.moseoh.deploymanager.domain.auth.presentation

import com.moseoh.deploymanager.common.dto.ResponseDto
import com.moseoh.deploymanager.domain.auth.application.AuthService
import com.moseoh.deploymanager.domain.auth.application.dto.RefreshTokenRequest
import com.moseoh.deploymanager.domain.auth.application.dto.SignInRequest
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/sign-in")
    fun signIn(
        @RequestBody @Valid signInRequest: SignInRequest
    ): ResponseEntity<ResponseDto> {
        val tokenResponse = authService.signIn(signInRequest)

        return ResponseDto.of(
            message = "로그인 완료.",
            status = HttpStatus.OK,
            data = tokenResponse
        )
    }

    @DeleteMapping("/sign-out")
    fun signOut(
        auth: Authentication
    ): ResponseEntity<ResponseDto> {
        authService.signOut(auth)

        return ResponseDto.of(
            message = "로그아웃 완료.",
            status = HttpStatus.OK,
        )
    }

    @PostMapping("/refresh")
    fun refresh(
        @RequestBody @Valid refreshTokenRequest: RefreshTokenRequest
    ): ResponseEntity<ResponseDto> {
        val tokenResponse = authService.refresh(refreshTokenRequest)

        return ResponseDto.of(
            message = "토큰 갱신 완료.",
            status = HttpStatus.OK,
            data = tokenResponse
        )
    }
}