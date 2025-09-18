package com.mercadolivro.controller.response

class PageResponse<T>(
    var itens: List<T>,
    var currentPage: Int,
    var totalItems: Long,
    var totalPages: Int,

)