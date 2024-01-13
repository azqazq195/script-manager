package com.moseoh.deploymanager.config

import com.github.dockerjava.api.DockerClient
import com.github.dockerjava.core.DefaultDockerClientConfig
import com.github.dockerjava.core.DockerClientConfig
import com.github.dockerjava.core.DockerClientImpl
import com.github.dockerjava.httpclient5.ApacheDockerHttpClient
import com.github.dockerjava.transport.DockerHttpClient
import org.springframework.context.annotation.Configuration
import java.time.Duration

@Configuration
class DockerConfig {

    fun dockerClient(): DockerClient {
        return DockerClientImpl.getInstance(dockerClientConfig(), dockerHttpClient())
    }

    private fun dockerHttpClient(): DockerHttpClient {
        val config = dockerClientConfig()
        return ApacheDockerHttpClient.Builder()
            .dockerHost(config.dockerHost)
            .sslConfig(config.sslConfig)
            .maxConnections(100)
            .connectionTimeout(Duration.ofSeconds(30))
            .responseTimeout(Duration.ofSeconds(45))
            .build();
    }

    private fun dockerClientConfig(): DockerClientConfig {
        return DefaultDockerClientConfig.createDefaultConfigBuilder().build();
//        return DefaultDockerClientConfig.createDefaultConfigBuilder()
//            .withDockerHost(System.getenv("DOCKER_HOST") ?: "unix:///var/run/docker.sock")
//            .build()
    }
}