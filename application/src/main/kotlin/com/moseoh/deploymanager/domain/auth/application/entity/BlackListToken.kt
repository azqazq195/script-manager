package com.moseoh.deploymanager.domain.auth.application.entity

import com.moseoh.deploymanager.common.entity.BaseEntity
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    indexes = [
        Index(columnList = "accessToken")
    ]
)
class BlackListToken(
    accessToken: String,
    expiration: Long
) : BaseEntity() {
    var accessToken: String = accessToken
        private set

    var expiration: Long = expiration
        private set

    constructor(token: Token) : this(
        token.accessToken,
        token.expiration
    )
}