package com.mercadolivro.security

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.model.CustomerModel
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails

class UserCustomDetails(val customerModel: CustomerModel) : UserDetails {
    val id: Int = customerModel.id!!

    override fun getAuthorities(): Collection<GrantedAuthority> =
        customerModel.roles.map { SimpleGrantedAuthority(it.description) }

    override fun getPassword(): String = customerModel.password

    override fun getUsername(): String = customerModel.email   // ← CORRIGIDO

    override fun isAccountNonExpired(): Boolean = true
    override fun isAccountNonLocked(): Boolean = true
    override fun isCredentialsNonExpired(): Boolean = true
    override fun isEnabled(): Boolean = customerModel.status == CustomerStatus.ATIVO
}
