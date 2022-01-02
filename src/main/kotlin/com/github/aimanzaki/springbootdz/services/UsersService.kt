package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.api.response.UserDto
import com.github.aimanzaki.springbootdz.models.User
import com.github.aimanzaki.springbootdz.repositories.UsersRepository
import org.springframework.stereotype.Service

@Service
class UsersService(val usersRepository: UsersRepository) {

    fun createUser(userDto: UserDto) {
        val user = User(
            name = userDto.name,
            email = userDto.email,
            password = "",
            authorities = userDto.authorities
        )
        usersRepository.save(user)
    }
}
