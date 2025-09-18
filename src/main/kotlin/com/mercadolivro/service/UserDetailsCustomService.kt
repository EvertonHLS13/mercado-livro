package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import com.mercadolivro.security.UserCustomDetails
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Service

@Service
class UserDetailsCustomService(
    private val customerRepository: CustomerRepository
) : UserDetailsService {

    /**
     * Busca usuário pelo email (usado no login padrão do Spring Security)
     */
    override fun loadUserByUsername(username: String): UserDetails {
        val customer: CustomerModel = customerRepository.findByEmail(username)
            ?: throw UsernameNotFoundException("Usuário não encontrado: $username")

        return UserCustomDetails(customer)
    }

    /**
     * Busca usuário pelo ID (usado quando o token JWT armazena o ID)
     */
    fun loadUserById(id: Int): UserDetails {
        val customer: CustomerModel = customerRepository.findById(id)
            .orElseThrow { UsernameNotFoundException("Usuário não encontrado: $id") }

        return UserCustomDetails(customer)
    }
}
