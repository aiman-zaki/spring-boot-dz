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
) :
    WebSecurityConfigurerAdapter() {

    companion object {
        val log: Logger = LoggerFactory.getLogger(ApplicationSecurityConfig::class.java)
        private const val ROLE_CLAIM = "resource_access"

        // TODO : move to application.properties
        private val CLIENTS_ID = listOf("react")
    }

    fun getJwtAuthenticationConverter(): Converter<Jwt?, AbstractAuthenticationToken?>? {
        val converter = JwtAuthenticationConverter()
        converter.setJwtGrantedAuthoritiesConverter { jwt ->
            log.debug(jwt.claims.entries.joinToString(","))
            val resourceAccess = jwt.claims.entries.find { entry -> entry.key == ROLE_CLAIM }
            val resource = resourceAccess?.value as JSONObject
            val clients = resource.entries.filter { client -> CLIENTS_ID.contains(client.key) }
            val roles = clients.map { client ->
                val rolesObject = client.value as JSONObject
                val roles = rolesObject["roles"] as JSONArray
                roles.map { r -> SimpleGrantedAuthority("ROLE_$r".uppercase()) }
            }

            // FIXME : Just to make it works
            val role = roles[0]

            listOf<GrantedAuthority>().plus(role)
        }

        return converter
    }

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.sessionManagement()
            .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            .and().cors()
            // .and().csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).and()
            .and().csrf().disable()
            .headers().frameOptions().sameOrigin()
            .and().authorizeRequests().anyRequest().permitAll()
            .and().addFilterAfter(userSyncFilter, OAuth2AuthorizationCodeGrantFilter::class.java)
            .oauth2ResourceServer().jwt()
            .jwtAuthenticationConverter(getJwtAuthenticationConverter())
        // .and().oauth2ResourceServer { o -> o.jwt().jwtAuthenticationConverter(jwtAuthenticationConverter()) }
        // .and().oauth2Login().userInfoEndpoint().oidcUserService(keycloakOauth2UserService)
        // .and().addFilterBefore(authenticationRequestFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}
