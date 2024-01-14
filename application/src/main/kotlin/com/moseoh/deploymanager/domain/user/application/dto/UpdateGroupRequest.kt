package com.moseoh.deploymanager.domain.user.application.dto

data class UpdateGroupRequest(
    override val name: String
) : GroupRequest