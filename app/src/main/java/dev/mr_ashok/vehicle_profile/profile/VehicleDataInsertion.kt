package dev.mr_ashok.vehicle_profile.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import dev.mr_ashok.vehicle_profile.Resource
import dev.mr_ashok.vehicle_profile.Status
import dev.mr_ashok.vehicle_profile.VehicleViewModel
import dev.mr_ashok.vehicle_profile.db.Vehicle

fun insertVehicleData(
    builder: VehicleProfileBundleBuilder,
    viewModel: VehicleViewModel
): LiveData<Resource<Boolean>> {
    val number = builder.getVehicleNumber()
    val vehicleClass = builder.getVehicleClass()
    val make = builder.getVehicleMake()
    val model = builder.getVehicleModel()
    val fuel = builder.getVehicleFuel()
    val transmission = builder.getVehicleTransmission()
    if (number == null || vehicleClass == null || make == null || model == null || fuel == null || transmission == null) {
        val exception = RuntimeException("Require data is not present")
        return MutableLiveData(Resource(Status.ERROR, null, exception))
    }
    val vehicle = Vehicle(number, vehicleClass, make, model, fuel, transmission)
    return viewModel.insertVehicle(vehicle)
}
