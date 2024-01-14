package com.moseoh.deploymanager.domain.user.application.dto

import com.moseoh.deploymanager.domain.user.application.entity.Role

data class CreateUserRequest(
    override val name: String,
    override val role: Role,
    override val groupId: Long
) : UserRequest {
    override val password: String = name
}