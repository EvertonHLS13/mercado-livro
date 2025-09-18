package com.mercadolivro.controller.response

import org.aspectj.bridge.Message

data class FieldErrorResponse (
    var message: String,
    var field: String
)