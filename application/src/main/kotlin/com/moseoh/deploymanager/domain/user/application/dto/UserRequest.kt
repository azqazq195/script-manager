package com.moseoh.deploymanager.domain.user.application.dto

import com.moseoh.deploymanager.domain.user.application.entity.Role

interface UserRequest {
    val name: String
    val password: String
    val role: Role
    val groupId: Long
}