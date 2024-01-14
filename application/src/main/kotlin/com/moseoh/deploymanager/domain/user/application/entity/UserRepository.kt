package com.moseoh.deploymanager.domain.user.application.entity

import com.moseoh.deploymanager.domain.user.application.exception.UserNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface UserRepository : JpaRepository<User, Long> {
    fun findByName(name: String): Optional<User>
}

fun UserRepository.getEntityById(id: Long): User =
    findById(id).orElseThrow(::UserNotFoundException)

fun UserRepository.getEntityByName(name: String): User =
    findByName(name).orElseThrow(::UserNotFoundException)
