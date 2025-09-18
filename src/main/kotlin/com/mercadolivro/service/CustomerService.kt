package com.mercadolivro.service

import com.mercadolivro.enums.CustomerStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.enums.Role
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Service

@Service
class CustomerService(
    private val customerRepository: CustomerRepository,
    private val bookService: BookService,
    private val bCrypt: BCryptPasswordEncoder

) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return customerRepository.findByNameContaining(it)
        }
        return customerRepository.findAll().toList()
    }

    fun findById(id: Int): CustomerModel {
        return customerRepository.findById(id)
            .orElseThrow { NotFoundException(Errors.ML201.message.format(id), Errors.ML201.code) }
    }

    fun create(customer: CustomerModel): CustomerModel {
        // senha sempre salva criptografada
        customer.password = bCrypt.encode(customer.password)
        if (customer.roles.isEmpty()) {
            customer.roles = mutableSetOf(Role.CUSTOMER)
        }
        return customerRepository.save(customer)
    }

    fun update(customer: CustomerModel): CustomerModel {
        if (customer.id == null || !customerRepository.existsById(customer.id!!)) {
            throw NotFoundException("Customer [${customer.id}] not exists", "ML-201")
        }
        return customerRepository.save(customer)
    }

    fun delete(id: Int) {
        val customer = findById(id)
        bookService.deleteByCustomer(customer)
        customer.status = CustomerStatus.INATIVO
        customerRepository.save(customer)
    }

    fun emailAvailable(email: String): Boolean {
        return !customerRepository.existsByEmail(email)
    }

    fun encodePassword(rawPassword: String): String {
        return bCrypt.encode(rawPassword)
    }

    fun encodeAllExistingPasswords() {
        val customers = customerRepository.findAll()
        customers.forEach { customer ->
            if (!customer.password.startsWith("\$2a\$")) { // verifica se já não está no formato BCrypt
                customer.password = encodePassword(customer.password)
                customerRepository.save(customer)
            }
        }
    }
}
