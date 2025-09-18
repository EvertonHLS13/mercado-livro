package com.mercadolivro.controller

import com.mercadolivro.service.CustomerService
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.concurrent.atomic.AtomicBoolean

@RestController
@RequestMapping("/util")
class PasswordMigrationController(
    private val customerService: CustomerService
) {

    // Flag para controlar execução única
    private val alreadyExecuted = AtomicBoolean(false)

    @PutMapping("/migrate-passwords")
    fun migratePasswords(): String {
        // Verifica se já foi executado
        if (alreadyExecuted.get()) {
            return "Este endpoint já foi executado. Atualização não realizada."
        }

        // Atualiza todas as senhas para BCrypt
        customerService.encodeAllExistingPasswords()

        // Marca como executado
        alreadyExecuted.set(true)

        return "Todas as senhas antigas foram atualizadas para BCrypt com sucesso!"
    }
}
