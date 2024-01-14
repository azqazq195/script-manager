package com.moseoh.deploymanager.domain.user.application.entity

import com.moseoh.deploymanager.common.entity.BaseTimeEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity

@Entity
class Group(
    name: String,
) : BaseTimeEntity() {
    @Column(nullable = false)
    var name: String = name
        private set

    fun update(updateValue: Group) {
        this.name = updateValue.name
    }
}