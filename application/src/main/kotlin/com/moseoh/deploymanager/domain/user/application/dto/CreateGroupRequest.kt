package com.moseoh.deploymanager.domain.user.application.dto

data class CreateGroupRequest(
    override val name: String
) : GroupRequest