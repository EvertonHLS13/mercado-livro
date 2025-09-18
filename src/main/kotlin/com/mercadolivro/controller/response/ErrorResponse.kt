package com.mercadolivro.controller.response

import org.aspectj.bridge.Message

data class ErrorResponse (
    var httpCode: Int,
    var message: String,
    var internalCode: String,
    var errors: List<FieldErrorResponse>?
)