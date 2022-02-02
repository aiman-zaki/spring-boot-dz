package com.github.aimanzaki.springbootdz

import com.github.aimanzaki.springbootdz.configs.AppProperties
import com.github.aimanzaki.springbootdz.configs.KeycloakProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication(exclude = [SecurityAutoConfiguration::class, UserDetailsServiceAutoConfiguration::class])
@EnableCaching
@EnableConfigurationProperties(AppProperties::class, KeycloakProperties::class)
class SpringBootDzApplication

fun main(args: Array<String>) {
    runApplication<SpringBootDzApplication>(*args)
}
