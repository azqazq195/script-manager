package com.moseoh.deploymanager.domain.user.application.mapper

import com.moseoh.deploymanager.domain.user.application.dto.UserRequest
import com.moseoh.deploymanager.domain.user.application.entity.GroupRepository
import com.moseoh.deploymanager.domain.user.application.entity.User
import com.moseoh.deploymanager.domain.user.application.entity.getEntityById
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserMapper(
    private val groupRepository: GroupRepository,
    private val passwordEncoder: PasswordEncoder
) {
    fun dtoToEntity(userRequest: UserRequest): User {
        val group = groupRepository.getEntityById(userRequest.groupId)
        return User(
            name = userRequest.name,
            password = passwordEncoder.encode(userRequest.password),
            role = userRequest.role,
            group = group
        )
    }
}