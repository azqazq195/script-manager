package com.moseoh.deploymanager.domain.user.application.mapper

import com.moseoh.deploymanager.domain.user.application.dto.GroupRequest
import com.moseoh.deploymanager.domain.user.application.entity.Group
import org.springframework.stereotype.Component

@Component
class GroupMapper {
    fun dtoToEntity(groupRequest: GroupRequest): Group {
        return Group(
            name = groupRequest.name
        )
    }
}