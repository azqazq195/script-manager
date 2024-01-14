package com.moseoh.deploymanager.common.dto


import com.moseoh.deploymanager.common.exception.ErrorCode
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ResponseDto(
    val error: Boolean,
    val message: String? = null,
    val data: Any? = null
) {
    companion object {
        fun of(
            status: HttpStatus,
            data: Any? = null,
            message: String? = null,
        ): ResponseEntity<ResponseDto> {
            return ResponseEntity(
                ResponseDto(
                    error = false,
                    message = message,
                    data = data
                ), status
            )
        }

        fun of(
            error: ErrorCode
        ): ResponseEntity<ResponseDto> {
            return ResponseEntity(
                ResponseDto(
                    error = true,
                    message = error.message
                ), error.status
            )
        }
    }
}