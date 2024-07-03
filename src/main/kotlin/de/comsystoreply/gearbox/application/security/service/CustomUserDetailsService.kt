package de.comsystoreply.gearbox.application.security.service

import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

typealias ApplicationUser = de.comsystoreply.gearbox.application.user.model.UserEntity

@Service
class CustomUserDetailsService(private val userApi: UserApiFacade) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userApi.findByEmail(username) ?: throw UsernameNotFoundException("Not found!")
        return ApplicationUser
            .fromDomain(user)
            .mapToUserDetails()
    }

    private fun ApplicationUser.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .build()
}