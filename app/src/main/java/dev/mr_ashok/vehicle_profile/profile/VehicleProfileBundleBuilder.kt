package dev.mr_ashok.vehicle_profile.profile

import android.os.Bundle

private const val VEHICLE_NUMBER_KEY = "number";
private const val VEHICLE_CLASS_KEY = "class";
private const val VEHICLE_MAKE_KEY = "make";
private const val VEHICLE_MODEL_KEY = "model";
private const val VEHICLE_FUEL_KEY = "fuel";
private const val VEHICLE_TRANSMISSION_KEY = "transmission";

class VehicleProfileBundleBuilder(previousBundle: Bundle?=null) {
    private val bundle = Bundle(previousBundle)

    //region Setter

    fun setVehicleNumber(number: String): VehicleProfileBundleBuilder {
        bundle.putString(VEHICLE_NUMBER_KEY, number)
        return this;
    }

    fun setVehicleClass(vehicleClass: String): VehicleProfileBundleBuilder {
        bundle.putString(VEHICLE_CLASS_KEY, vehicleClass)
        return this;
    }

    fun setVehicleMake(make: String): VehicleProfileBundleBuilder {
        bundle.putString(VEHICLE_MAKE_KEY, make)
        return this;
    }

    fun setVehicleModel(model: String): VehicleProfileBundleBuilder {
        bundle.putString(VEHICLE_MODEL_KEY, model)
        return this;
    }

    fun setVehicleFuel(fuel: String): VehicleProfileBundleBuilder {
        bundle.putString(VEHICLE_FUEL_KEY, fuel)
        return this;
    }

    fun setVehicleTransmission(transmission: String): VehicleProfileBundleBuilder {
        bundle.putString(VEHICLE_TRANSMISSION_KEY, transmission)
        return this;
    }

    //endregion

    //region Getters

    fun getVehicleNumber() = bundle.getString(VEHICLE_NUMBER_KEY)

    fun getVehicleClass() = bundle.getString(VEHICLE_CLASS_KEY)

    fun getVehicleMake() = bundle.getString(VEHICLE_MAKE_KEY)

    fun getVehicleModel() = bundle.getString(VEHICLE_MODEL_KEY)

    fun getVehicleFuel() = bundle.getString(VEHICLE_FUEL_KEY)

    fun getVehicleTransmission() = bundle.getString(VEHICLE_TRANSMISSION_KEY)

    //endregion
}