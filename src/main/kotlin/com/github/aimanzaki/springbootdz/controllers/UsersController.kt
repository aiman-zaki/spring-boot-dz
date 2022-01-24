package com.github.aimanzaki.springbootdz.controllers

import com.github.aimanzaki.springbootdz.services.UserService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/users")
class UsersController(var userService: UserService) {

    companion object {
        val log: Logger = LoggerFactory.getLogger(UsersController::class.java)
    }

    @GetMapping()
    fun getUsers() {
        log.info("Get Lists of Users")
    }

    @PostMapping
    fun createUser() {
        log.info(":: Create User ::")
    }
}
