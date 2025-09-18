package com.mercadolivro.security

import io.jsonwebtoken.Claims
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import io.jsonwebtoken.security.Keys
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Component
import java.util.*

@Component
class JwtUtil {

    @Value("\${jwt.expiration}")
    private var expiration: Long = 0

    @Value("\${jwt.secret}")
    private lateinit var secret: String

    /**
     * Gera token JWT usando o ID do usuário como subject
     */
    fun generateToken(userId: Int): String {
        val key = Keys.hmacShaKeyFor(secret.toByteArray())
        return Jwts.builder()
            .setSubject(userId.toString())
            .setExpiration(Date(System.currentTimeMillis() + expiration))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact()
    }

    /**
     * Valida token e retorna o ID do usuário
     */
    fun validateToken(token: String): Int? {
        return try {
            val claims = parseClaims(token)
            claims.subject.toInt()
        } catch (ex: Exception) {
            null
        }
    }

    /**
     * Retorna true se o token é válido (não expirado)
     */
    fun isValidToken(token: String): Boolean {
        return try {
            val claims = parseClaims(token)
            val expirationDate = claims.expiration
            expirationDate != null && Date().before(expirationDate)
        } catch (ex: Exception) {
            false
        }
    }

    /**
     * Recupera o subject (ID do usuário) do token
     */
    fun getSubject(token: String): String {
        return parseClaims(token).subject
    }

    /**
     * Parse seguro dos claims do token
     */
    private fun parseClaims(token: String): Claims {
        try {
            val key = Keys.hmacShaKeyFor(secret.toByteArray())
            return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .body
        } catch (ex: Exception) {
            throw com.mercadolivro.exception.AuthenticationException("Token inválido", "999")
        }
    }
}
