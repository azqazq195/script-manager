package com.moseoh.deploymanager.domain.user.application

import com.moseoh.deploymanager.domain.user.application.dto.CreateGroupRequest
import com.moseoh.deploymanager.domain.user.application.dto.UpdateGroupRequest
import com.moseoh.deploymanager.domain.user.application.entity.GroupRepository
import com.moseoh.deploymanager.domain.user.application.entity.getEntityById
import com.moseoh.deploymanager.domain.user.application.mapper.GroupMapper
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class GroupService(
    val groupMapper: GroupMapper,
    val groupRepository: GroupRepository
) {
    @Transactional
    fun create(groupDto: CreateGroupRequest) {
        val group = groupMapper.dtoToEntity(groupDto)
        groupRepository.save(group)
    }

    @Transactional
    fun update(id: Long, groupDto: UpdateGroupRequest) {
        val group = groupRepository.getEntityById(id)
        val updateValue = groupMapper.dtoToEntity(groupDto)
        group.update(updateValue)
        groupRepository.save(group)
    }
}