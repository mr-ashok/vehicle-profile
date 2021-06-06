package dev.mr_ashok.vehicle_profile

import android.os.Bundle
import dev.mr_ashok.vehicle_profile.db.Vehicle

private const val VEHICLE_NUMBER_KEY = "vehicleNumber"

class VehicleDetailBundleBuilder {
    private val bundle = Bundle()

    fun build() = bundle

    fun setVehicleData(vehicleNumber: String): VehicleDetailBundleBuilder {
        bundle.putString(VEHICLE_NUMBER_KEY, vehicleNumber)
        return this
    }

    companion object {
        fun getVehicleNumber(bundle: Bundle?): String? = bundle?.getString(VEHICLE_NUMBER_KEY)
    }
}