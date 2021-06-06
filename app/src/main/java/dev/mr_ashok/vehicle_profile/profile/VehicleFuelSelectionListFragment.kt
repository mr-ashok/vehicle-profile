package dev.mr_ashok.vehicle_profile.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dev.mr_ashok.vehicle_profile.R
import dev.mr_ashok.vehicle_profile.base.BasicListFragment

public enum class VehicleFuel(val label: String) {
    PETROL("Petrol"),
    DIESEL("Diesel"),
    CNG("CNG"),
    PETROL_CNG("Petrol + CNG"),
    ELECTRIC("Electric"),
    HYBRID("Hybrid")
}

class VehicleFuelSelectionListFragment : BasicListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListData(VehicleFuel.values().map { it.label })
    }

    override fun onDataSelected(data: String) {
        val vehicleFuel = VehicleFuel.values().find { it.label == data }
            ?: throw RuntimeException("Invalid vehicle fuel. Expected to be in [${
                VehicleFuel.values().joinToString(", ") { "\"${it.label}\"" }
            }], but got \"$data\"")
        val bundle =
            VehicleProfileBundleBuilder(arguments).setVehicleFuel(vehicleFuel.toString()).build()
        findNavController().navigate(R.id.action_next, bundle)
    }
}