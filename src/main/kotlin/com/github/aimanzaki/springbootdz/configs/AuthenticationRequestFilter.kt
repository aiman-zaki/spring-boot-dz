package com.github.aimanzaki.springbootdz.configs

import com.github.aimanzaki.springbootdz.enums.Authorities
import org.springframework.http.HttpHeaders
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter
import java.io.IOException
import javax.servlet.FilterChain
import javax.servlet.ServletException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

object SecurityHeader {
    var AUTHORIZATION: String = HttpHeaders.AUTHORIZATION
    var BEARER: String = "Bearer"
}

class AuthenticationRequestFilter() : OncePerRequestFilter() {

    fun extractAccessToken(authorizationHeader: String?): String {
        if (authorizationHeader != null) {
            if (authorizationHeader.startsWith(SecurityHeader.BEARER)) {
                return authorizationHeader.substring(SecurityHeader.BEARER.length).trim()
            }
        }
        return ""
    }

    @Throws(ServletException::class, IOException::class)
    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        val authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION)

        SecurityContextHolder.getContext().authentication = UsernamePasswordAuthenticationToken(
            "HELLaaO", "HAI",
            mutableListOf(SimpleGrantedAuthority(Authorities.USER.name))
        )

        filterChain.doFilter(request, response)
    }
}
