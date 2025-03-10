package com.book.manager.usecase.security

import com.book.manager.domain.model.entity.Operator
import com.book.manager.domain.model.id.OperatorId
import com.book.manager.domain.model.value.RoleType
import com.book.manager.usecase.AuthenticationService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.AuthorityUtils
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService

class BookManagerOperatorService(
    private val authenticationService: AuthenticationService,
) : UserDetailsService {
    override fun loadUserByUsername(username: String): UserDetails? {
        val operator = authenticationService.findOperator(username)
        return operator?.let { BookManagerOperator(it) }
    }
}

data class BookManagerOperator(
    val id: OperatorId,
    val email: String,
    val pass: String,
    val roleType: RoleType,
) : UserDetails {
    constructor(operator: Operator) : this(operator.id, operator.email, operator.password, operator.roleType)

    override fun getAuthorities(): Collection<GrantedAuthority> =
        AuthorityUtils.createAuthorityList(this.roleType.value.toString())

    override fun isEnabled(): Boolean = true

    override fun getUsername(): String = this.email

    override fun isCredentialsNonExpired(): Boolean = true

    override fun getPassword(): String = this.pass

    override fun isAccountNonExpired(): Boolean = true

    override fun isAccountNonLocked(): Boolean = true
}
