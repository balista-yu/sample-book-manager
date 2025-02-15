package com.book.manager.usecase.security

import com.book.manager.usecase.AuthenticationService
import com.book.manager.domain.model.entity.Operator
import com.book.manager.domain.model.id.OperatorId
import com.book.manager.domain.model.value.RoleType
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class BookManagerOperatorDetailsService(
    private val authenticationService: AuthenticationService
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        val operator = authenticationService.findOperator(username)
        return operator?.let { BookManagerOperatorDetails(it) }
    }
}

data class BookManagerOperatorDetails(
    val id: OperatorId,
    val email: String,
    val pass: String,
    val roleType: RoleType
) : UserDetails {

    constructor(operator: Operator) : this(operator.id, operator.email, operator.password, operator.roleType)

    override fun getAuthorities(): Collection<GrantedAuthority> {
        return AuthorityUtils.createAuthorityList(this.roleType.value.toString())
    }

    override fun isEnabled(): Boolean {
        return true
    }

    override fun getUsername(): String {
        return this.email
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun getPassword(): String {
        return this.pass
    }

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }
}
