package com.moseoh.deploymanager.domain.auth.utils

import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder

object AuthUtils {
    val loginUserId: Long
        get() {
            val authentication = SecurityContextHolder.getContext().authentication
            check(authentication != null) { "인증정보가 없습니다." }
            return authentication.principal as Long
        }

    val loginUser: Authentication
        get() {
            val authentication = SecurityContextHolder.getContext().authentication
            check(authentication != null) { "인증정보가 없습니다." }
            return authentication
        }
}