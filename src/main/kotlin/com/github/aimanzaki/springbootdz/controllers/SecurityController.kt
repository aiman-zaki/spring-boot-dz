package com.github.aimanzaki.springbootdz.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

// This is just temporary , will use keycloak , or similar tech for login
@RestController
@RequestMapping("/api/security")
class SecurityController {

    @GetMapping("/csrf")
    fun getCsrf() {
    }

    @PostMapping("/login")
    fun login() {
    }

    @PostMapping("/refresh-token")
    fun refreshToken() {
    }
}
