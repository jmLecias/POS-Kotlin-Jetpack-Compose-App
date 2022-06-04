package com.bapp.pointofsalesapp.feature_item.domain.util

sealed class ItemOrder(val orderType: OrderType) {
    class Name(orderType: OrderType): ItemOrder(orderType)
    class Brand(orderType: OrderType): ItemOrder(orderType)
    class Category(orderType: OrderType): ItemOrder(orderType)

    fun copy(orderType: OrderType): ItemOrder {
       return when(this) {
           is Name -> Name(orderType)
           is Brand -> Brand(orderType)
           is Category -> Category(orderType)
        }
    }
}
