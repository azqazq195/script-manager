package com.moseoh.deploymanager.domain.auth.application.exception

import com.moseoh.deploymanager.common.exception.ApiException
import com.moseoh.deploymanager.common.exception.ErrorCode

class PasswordNotMatchException : ApiException(ErrorCode.PASSWORD_NOT_MATCH)