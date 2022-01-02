package com.github.aimanzaki.springbootdz.configs

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.oauth2.core.oidc.user.OidcUser

interface ApplicationAuthoritiesProvider {
    fun <T : OidcUser?> findAuthorities(user: T): Collection<GrantedAuthority?>?
}
