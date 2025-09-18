package com.mercadolivro.security

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.mercadolivro.controller.request.LoginRequest
import com.mercadolivro.repository.CustomerRepository
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class AuthenticationFilter(
    private val authenticationManager: AuthenticationManager,
    customerRepository: CustomerRepository,
    private val jwtUtil: JwtUtil
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        if (request.servletPath.equals("/login", ignoreCase = true) &&
            request.method.equals("POST", ignoreCase = true)) {

            try {
                // Lê email e senha do corpo da requisição
                val loginRequest = jacksonObjectMapper().readValue(request.inputStream, LoginRequest::class.java)

                // Cria token de autenticação (email como principal, senha como credencial)
                val authToken = UsernamePasswordAuthenticationToken(loginRequest.email, loginRequest.password)

                // Autentica no AuthenticationManager
                val authResult: Authentication = authenticationManager.authenticate(authToken)

                // Recupera UserCustomDetails
                val userDetails = authResult.principal as UserCustomDetails

                // Gera JWT usando o ID do usuário
                val token = jwtUtil.generateToken(userDetails.id)

                // Configura contexto de segurança
                SecurityContextHolder.getContext().authentication = authResult

                // Retorna token no corpo da resposta
                response.contentType = "application/json"
                response.writer.write(
                    jacksonObjectMapper().writeValueAsString(mapOf("token" to token))
                )
                return

            } catch (ex: Exception) {
                response.status = HttpServletResponse.SC_UNAUTHORIZED
                response.contentType = "application/json"
                response.writer.write(
                    jacksonObjectMapper().writeValueAsString(
                        mapOf("error" to "Falha ao autenticar", "message" to (ex.message ?: "Credenciais inválidas"))
                    )
                )
                return
            }
        }

        // Continua o fluxo para as demais requisições
        filterChain.doFilter(request, response)
    }
}
