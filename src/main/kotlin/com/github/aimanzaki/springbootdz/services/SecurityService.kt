package com.github.aimanzaki.springbootdz.services

import com.github.aimanzaki.springbootdz.exceptions.UserNotFoundException
import com.github.aimanzaki.springbootdz.models.User
import com.github.aimanzaki.springbootdz.repositories.UsersRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class SecurityService(val usersRepository: UsersRepository) {

    @Throws(UserNotFoundException::class)
    fun generateTokens(userId: UUID) {
        val user: User = requireNotNull(usersRepository.findByIdOrNull(userId)) { throw UserNotFoundException() }
    }
}
