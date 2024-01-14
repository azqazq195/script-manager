package com.moseoh.deploymanager.common.config

import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationPropertiesScan("com.moseoh.deploymanager.common.environment")
class EnvironmentConfig