package com.mercadolivro.model

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.JoinTable
import jakarta.persistence.ManyToMany
import jakarta.persistence.ManyToOne
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(name = "purchase")
data class PurchaseModel(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,


    @ManyToOne
    @JoinColumn(name = "customer_id")
    var customer: CustomerModel?,

    @ManyToMany
    @JoinTable(name = "purchase_book",
        joinColumns = [JoinColumn(name = "purchase_id")],
        inverseJoinColumns = [JoinColumn( name ="book_id")])
    var books: MutableList<BookModel> = mutableListOf(),

    @Column
    var nfe: String? = null,

    @Column
    var price: BigDecimal?,

    @Column(name = "created_at")
    var createAt: LocalDateTime = LocalDateTime.now()

){
    // Construtor vazio exigido pelo JPA
    constructor() : this(
        id = null,
        customer = null,
        books = mutableListOf(),
        nfe = null,
        price = null,
        createAt = LocalDateTime.now()
    )
}