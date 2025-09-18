package com.mercadolivro.events

import com.mercadolivro.model.PurchaseModel
import org.springframework.context.ApplicationEvent
import kotlin.time.TimeSource

class PurchaseEvent  (
   source: Any,
   val purchaseModel: PurchaseModel

): ApplicationEvent(source)