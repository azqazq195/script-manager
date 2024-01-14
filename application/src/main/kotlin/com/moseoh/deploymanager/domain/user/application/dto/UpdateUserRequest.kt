package com.moseoh.deploymanager.domain.user.application.dto

import com.moseoh.deploymanager.domain.user.application.entity.Role

data class UpdateUserRequest(
    override val name: String,
    override val password: String,
    override val role: Role,
    override val groupId: Long,
) : UserRequest