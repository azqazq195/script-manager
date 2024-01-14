package com.moseoh.deploymanager.domain.user.application

import com.moseoh.deploymanager.domain.user.application.dto.CreateUserRequest
import com.moseoh.deploymanager.domain.user.application.dto.UpdateUserRequest
import com.moseoh.deploymanager.domain.user.application.entity.Role
import com.moseoh.deploymanager.domain.user.application.entity.User
import com.moseoh.deploymanager.domain.user.application.entity.UserRepository
import com.moseoh.deploymanager.domain.user.application.entity.getEntityById
import com.moseoh.deploymanager.domain.user.application.mapper.UserMapper
import jakarta.annotation.PostConstruct
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class UserService(
    val userMapper: UserMapper,
    val userRepository: UserRepository,
    val passwordEncoder: PasswordEncoder,
) {

    @PostConstruct
    protected fun init() {
        userRepository.save(User("문성하", passwordEncoder.encode("문성하"), Role.SUPER, null))
    }


    @Transactional
    fun create(userDto: CreateUserRequest) {
        val user = userMapper.dtoToEntity(userDto)
        userRepository.save(user)
    }

    @Transactional
    fun update(id: Long, userDto: UpdateUserRequest) {
        val user = userRepository.getEntityById(id)
        val updateValue = userMapper.dtoToEntity(userDto)
        user.update(updateValue)
        userRepository.save(user)
    }
}