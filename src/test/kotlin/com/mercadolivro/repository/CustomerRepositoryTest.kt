package com.mercadolivro.repository

import com.mercadolivro.helper.buildCustomer
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ActiveProfiles
import kotlin.test.assertNull

@SpringBootTest
@ActiveProfiles("test")
class CustomerRepositoryTest {

    @Autowired
    private lateinit var customerRepository: CustomerRepository

    @BeforeEach
    fun setup() {
        customerRepository.deleteAll()
    }

    @Test
    fun `should return customers with name containing substring`() {
        val marcos = customerRepository.save(buildCustomer(name = "Marcos"))
        val matheus = customerRepository.save(buildCustomer(name = "Matheus"))
        customerRepository.save(buildCustomer(name = "Alex"))

        val customers = customerRepository.findByNameContaining("Ma")

        assertThat(customers)
            .containsExactlyInAnyOrder(marcos, matheus)
    }

    @Nested
    inner class `find by email` {

        @Test
        fun `should return customer when email exists`() {
            val email = "email@teste.com"
            val customer = customerRepository.save(buildCustomer(email = email))

            val result = customerRepository.findByEmail(email)

            assertThat(result).isNotNull
            assertThat(result).isEqualTo(customer)
        }

        @Test
        fun `should return null when email does not exist`() {
            val email = "nonexistingemail@teste.com"

            val result = customerRepository.findByEmail(email)

            assertNull(result)
        }
    }
}
