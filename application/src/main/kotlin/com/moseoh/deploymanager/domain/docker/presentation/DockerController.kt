package com.moseoh.deploymanager.domain.docker.presentation

import com.moseoh.deploymanager.config.DockerConfig
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/docker")
class DockerController(
    val dockerConfig: DockerConfig
) {


    @GetMapping("/containers")
    fun containers(): String {
        val client = dockerConfig.dockerClient()
        val containers = client.listContainersCmd().exec()
        for (container in containers) {
            println("Container ID: ${container.id}, State: ${container.state}")
        }
        return ""
    }
}