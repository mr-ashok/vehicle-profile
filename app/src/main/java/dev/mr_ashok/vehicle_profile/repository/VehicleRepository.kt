package dev.mr_ashok.vehicle_profile.repository

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface VehicleRepository {
    @GET("/turbo/care/v1/makes")
    fun getMakeList(@Query("class") vehicleClass: String): Call<List<String>>

    @GET("/turbo/care/v1/models")
    fun getModelList(
        @Query("class") vehicleClass: String,
        @Query("make") make: String
    ): Call<List<String>>

}