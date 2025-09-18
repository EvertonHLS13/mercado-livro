package com.mercadolivro.exception

import org.springframework.security.core.AuthenticationException as SpringAuthException

class AuthenticationException(
    override val message: String,
    val errorCode: String
) : SpringAuthException(message)
