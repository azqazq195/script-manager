package com.moseoh.deploymanager.domain.user.application.entity

import com.moseoh.deploymanager.common.entity.BaseTimeEntity
import com.moseoh.deploymanager.domain.auth.utils.AuthUtils
import jakarta.persistence.*

@Entity
class User(
    name: String,
    password: String,
    role: Role,
    group: Group?,
) : BaseTimeEntity() {
    @Column(nullable = false)
    var name: String = name
        private set

    @Column(nullable = false)
    var password: String = password
        private set

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var role: Role = role
        private set

    @ManyToOne
    var group: Group? = group
        private set

    fun update(updateValue: User) {
        val role = AuthUtils.loginUser.authorities.toList()[0].authority
        if (role.lowercase() == Role.SUPER.name.lowercase()) {
            this.name = updateValue.name
            this.role = updateValue.role
            this.group = updateValue.group
        }

        this.password = updateValue.password
    }
}