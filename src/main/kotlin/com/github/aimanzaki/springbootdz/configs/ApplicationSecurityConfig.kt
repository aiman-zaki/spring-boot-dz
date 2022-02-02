package com.github.aimanzaki.springbootdz.configs

import com.nimbusds.jose.shaded.json.JSONArray
import com.nimbusds.jose.shaded.json.JSONObject
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.context.annotation.Configuration
import org.springframework.core.convert.converter.Converter
import org.springframework.security.authentication.AbstractAuthenticationToken
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationCodeGrantFilter
import org.springframework.security.oauth2.jwt.Jwt
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class ApplicationSecurityConfig(
    private val userSyncFilter: UserSyncFilter,
    private val keycloakProperties: KeycloakProperties,
) :
    WebSecurityConfigurerAdapter() {

    companion object {
        val log: Logger = LoggerFactory.getLogger(ApplicationSecurityConfig::class.java)
    }

    fun getJwtAuthenticationConverter(): Converter<Jwt?, AbstractAuthenticationToken?>? {
        val converter = JwtAuthenticationConverter()
        converter.setJwtGrantedAuthoritiesConverter { jwt ->
            log.debug(jwt.claims.entries.joinToString(","))
            val grantedRoles = mutableListOf<GrantedAuthority>()
            val resourceAccess = jwt.claims.entries.find { entry -> entry.key == keycloakProperties.roleClaim }
            val resource = resourceAccess?.value as JSONObject
            val clients = resource.entries.filter { client -> keycloakProperties.clientIds.contains(client.key) }

            // Check every client for roles
            clients.forEach { client ->
                val rolesObject = client.value as JSONObject
                val roles = rolesObject["roles"] as JSONArray
                val simpleGrantedAuthority = roles.map { r -> SimpleGrantedAuthority("ROLE_$r".uppercase()) }
                grantedRoles.addAll(simpleGrantedAuthority)
            }

            grantedRoles
        }

        return converter
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().cors()
            .and().csrf().disable()
            .headers().frameOptions().sameOrigin()
            .and().authorizeRequests().anyRequest().permitAll()
            .and().addFilterAfter(userSyncFilter, OAuth2AuthorizationCodeGrantFilter::class.java)
            .oauth2ResourceServer().jwt()
            .jwtAuthenticationConverter(getJwtAuthenticationConverter())
    }
}
