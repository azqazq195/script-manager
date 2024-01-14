package com.moseoh.deploymanager.common.exception

import org.springframework.http.HttpStatus

enum class ErrorCode(
    val status: HttpStatus,
    val message: String,
) {
    /**
     * COMMON
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "올바른 요청형식이 아닙니다."),


    /**
     * USER
     */
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
}