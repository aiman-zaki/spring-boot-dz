package com.github.aimanzaki.springbootdz.configs

import com.github.aimanzaki.springbootdz.models.User
import com.github.aimanzaki.springbootdz.repositories.UserRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.UUID
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

// Sync keycloak user with application db
// FIXME : theres better impl than this
@Component
class UserSyncFilter(private val userRepository: UserRepository) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {

        val securityContextHolder = SecurityContextHolder.getContext()

        if (securityContextHolder.authentication.isAuthenticated and !securityContextHolder.authentication.name.isNullOrBlank()) {
            val user = userRepository.findByIdOrNull(UUID.fromString(securityContextHolder.authentication.name))
            if (user == null) {
                val newUser = User(email = "", name = "", password = "", authorities = listOf())
                newUser.id = UUID.fromString(securityContextHolder.authentication.name)
                userRepository.save(newUser)
            }
        }

        filterChain.doFilter(request, response)
    }
}
