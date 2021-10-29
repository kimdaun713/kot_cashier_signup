package com.example.kot_cashier.Model


data class Orderlist(val results:List<OrderInfo>)
data class OrderInfo(

    val TxIdval : String,
    val Values: Value,
    val Timestamp: String,
    val IsDelete: String,

){
    data class Value(
        val make:String,
        val model:String,
        val colour:String,
        val owner:String
    )

}