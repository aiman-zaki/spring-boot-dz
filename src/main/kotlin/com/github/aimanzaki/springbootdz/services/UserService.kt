package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.dto.UserDto
import com.github.aimanzaki.springbootdz.models.User
import com.github.aimanzaki.springbootdz.repositories.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(val userRepository: UserRepository) {

    fun createUser(userDto: UserDto) {
        val user = User(
            name = userDto.name,
            email = userDto.email,
            password = "",
            authorities = userDto.authorities
        )
        userRepository.save(user)
    }
}
