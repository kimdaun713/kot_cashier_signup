package com.example.kot_cashier
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import com.example.kot_cashier.Model.OrderInfo
import retrofit2.http.Query

interface RetrofitService{
    @POST("/channels/mychannel/chaincodes/fabcar")
    fun SavePaymentInfo(@Query("user_id")user_id :String,
                        @Query("type")type: String,
                        @Query("model")model: String,
                        @Query("price")price: String,
                        @Query("user")user_uid: String): Call<Int>

    @GET("/channels/mychannel/chaincodes/fabcar")
    fun getPaymentInfo(@Query ("args")args: String, @Query("fcn")fcn:String, @Query("peer")peer:String):Call<List<OrderInfo>>


}