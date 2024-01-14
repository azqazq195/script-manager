package com.moseoh.deploymanager.domain.auth.application.exception


import com.moseoh.deploymanager.common.exception.ApiException
import com.moseoh.deploymanager.common.exception.ErrorCode

class TokenNotFoundException : ApiException(ErrorCode.TOKEN_NOT_FOUND)
