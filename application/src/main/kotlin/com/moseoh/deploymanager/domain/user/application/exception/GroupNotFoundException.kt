package com.moseoh.deploymanager.domain.user.application.exception

import com.moseoh.deploymanager.common.exception.ApiException
import com.moseoh.deploymanager.common.exception.ErrorCode

class GroupNotFoundException : ApiException(ErrorCode.GROUP_NOT_FOUND)
