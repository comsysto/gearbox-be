package de.comsystoreply.gearbox.application.security.service

import de.comsystoreply.gearbox.application.user.model.UserEntity
import de.comsystoreply.gearbox.domain.user.port.api.UserApiFacade
import org.springframework.security.core.userdetails.User
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.stereotype.Service

@Service
class CustomUserDetailsService(private val userApi: UserApiFacade) : UserDetailsService {

    override fun loadUserByUsername(username: String): UserDetails {
        val user = userApi.findByEmail(username)
        return UserEntity
            .fromDomain(user)
            .mapToUserDetails()
    }

    private fun UserEntity.mapToUserDetails(): UserDetails =
        User.builder()
            .username(this.email)
            .password(this.password)
            .build()
}