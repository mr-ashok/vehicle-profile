package dev.mr_ashok.vehicle_profile.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dev.mr_ashok.vehicle_profile.R
import dev.mr_ashok.vehicle_profile.base.BasicListFragment

public enum class VehicleClass(val label: String, val value: String) {
    BIKE("Bike (2 wheeler)", "2w"),
    CAR("Car (4 wheeler)", "4w")
}

class VehicleClassSelectionListFragment : BasicListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListData(VehicleClass.values().map { it.label })
    }

    override fun onDataSelected(data: String) {
        val vehicleClass = VehicleClass.values().find { it.label == data }
            ?: throw RuntimeException("Invalid vehicle class. Expected to be in [${
                VehicleClass.values().joinToString(", ") { "\"${it.label}\"" }
            }], but got \"$data\"")
        val bundle =
            VehicleProfileBundleBuilder(arguments).setVehicleClass(vehicleClass.value).build()
        findNavController().navigate(R.id.action_next, bundle)
    }
}