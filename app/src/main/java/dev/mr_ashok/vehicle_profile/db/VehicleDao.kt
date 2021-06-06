package dev.mr_ashok.vehicle_profile.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VehicleDao {

    @Query("Select * from vehicle")
    suspend fun fetchVehicleList(): List<Vehicle>

    @Query("Select * from vehicle where number = (:number)")
    suspend fun findVehicle(number: String): Vehicle

    @Insert
    suspend fun insert(vehicle: Vehicle)
}