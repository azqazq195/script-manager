package com.moseoh.deploymanager

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DeployManagerApplication

fun main(args: Array<String>) {
    runApplication<DeployManagerApplication>(*args)
}
