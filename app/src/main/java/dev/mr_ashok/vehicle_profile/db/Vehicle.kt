package dev.mr_ashok.vehicle_profile.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Vehicle(
    @PrimaryKey val number: String,
    @ColumnInfo(name = "class") val vehicleClass: String,   // As "class" is reserved work, added the vehicle prefix to variable name. Also, we can have a variable name as `class`, but it makes code looks weird.
    val make: String,
    val model: String,
    val fuel: String,
    val transmission: String
)