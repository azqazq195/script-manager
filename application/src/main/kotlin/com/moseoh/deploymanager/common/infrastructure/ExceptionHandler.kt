package com.moseoh.deploymanager.common.infrastructure

import com.moseoh.deploymanager.common.dto.ResponseDto
import com.moseoh.deploymanager.common.exception.ApiException
import com.moseoh.deploymanager.common.exception.ErrorCode
import com.moseoh.deploymanager.common.logger
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class ExceptionHandler {
    private val logger = logger()

    // Api Exception
    @ExceptionHandler(ApiException::class)
    fun handleApiException(e: ApiException): ResponseEntity<ResponseDto> {
        logger.debug("ApiException: ", e)

        return ResponseDto.of(e.errorCode)
    }

    // Request Dto 양식이 올바르지 않는 경우 발생
    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ResponseDto> {
        logger.debug("HttpMessageNotReadableException: ", e)

        return ResponseDto.of(ErrorCode.BAD_REQUEST)
    }

    // Dto validation
    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleMethodArgumentNotValidException(e: MethodArgumentNotValidException): ResponseEntity<ResponseDto> {
        logger.debug("MethodArgumentNotValidException: ", e)

        fun MethodArgumentNotValidException.messages(): String {
            return bindingResult.fieldErrors.joinToString(", ") { "${it.field}: ${it.defaultMessage.orEmpty()}" }
        }
        return ResponseDto.of(HttpStatus.BAD_REQUEST, e.messages())
    }
}