package com.github.aimanzaki.springbootdz.configs

import com.github.aimanzaki.springbootdz.models.User
import com.github.aimanzaki.springbootdz.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.GenericFilterBean
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.ServletRequest
import javax.servlet.ServletResponse

// Sync keycloak user with application db
// FIXME : theres better impl than this
@Component
class UserSyncFilter(private val userRepository: UserRepository) : GenericFilterBean() {
    override fun doFilter(request: ServletRequest, response: ServletResponse, chain: FilterChain) {

        val securityContextHolder = SecurityContextHolder.getContext()

        if (securityContextHolder.authentication.isAuthenticated and !securityContextHolder.authentication.name.isNullOrBlank()) {
            val user = userRepository.findByIdOrNull(UUID.fromString(securityContextHolder.authentication.name))
            if (user == null) {
                val newUser = User(email = "", name = "", password = "", authorities = listOf())
                newUser.id = UUID.fromString(securityContextHolder.authentication.name)
                userRepository.save(newUser)
            }
        }

        chain.doFilter(request, response)
    }
}
