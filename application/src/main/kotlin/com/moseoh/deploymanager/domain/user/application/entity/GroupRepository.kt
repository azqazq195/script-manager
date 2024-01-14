package com.moseoh.deploymanager.domain.user.application.entity

import com.moseoh.deploymanager.domain.user.application.exception.GroupNotFoundException
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface GroupRepository : JpaRepository<Group, Long>

fun GroupRepository.getEntityById(id: Long): Group =
    findById(id).orElseThrow(::GroupNotFoundException)