package com.moseoh.deploymanager.domain.auth.application.entity

import com.moseoh.deploymanager.common.entity.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Index
import jakarta.persistence.Table

@Entity
@Table(
    indexes = [
        Index(columnList = "accessToken, refreshToken")
    ]
)
class Token(
    userId: Long,
    accessToken: String,
    refreshToken: String,
    expiration: Long,
) : BaseEntity() {
    @Column(nullable = false)
    var userId: Long = userId
        private set

    @Column(nullable = false)
    var accessToken: String = accessToken
        private set

    @Column(nullable = false)
    var refreshToken: String = refreshToken
        private set

    @Column(nullable = false)
    var expiration: Long = expiration
        private set
}