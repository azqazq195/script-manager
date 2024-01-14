package com.moseoh.deploymanager.domain.auth.application.exception

import com.moseoh.deploymanager.common.exception.ApiException
import com.moseoh.deploymanager.common.exception.ErrorCode

class TokenNotMatchException : ApiException(ErrorCode.TOKEN_NOT_MATCH)