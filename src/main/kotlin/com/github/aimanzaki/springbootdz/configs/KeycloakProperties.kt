package com.github.aimanzaki.springbootdz.configs

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding

@ConstructorBinding
@ConfigurationProperties(prefix = "app.keycloak")
data class KeycloakProperties(
    val clientIds: List<String>,
    val roleClaim: String,

)
