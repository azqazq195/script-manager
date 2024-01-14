package com.moseoh.deploymanager.common.exception

open class ApiException(val errorCode: ErrorCode) : RuntimeException(errorCode.message)
