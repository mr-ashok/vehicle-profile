package dev.mr_ashok.vehicle_profile.profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import dev.mr_ashok.vehicle_profile.R
import dev.mr_ashok.vehicle_profile.base.BasicListFragment

public enum class VehicleTransmission {
    MANUAL,
    AUTOMATIC
}

class VehicleTransmissionSelectionListFragment : BasicListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListData(VehicleTransmission.values().map { it.toString() })
    }

    override fun onDataSelected(data: String) {
        val bundleBuilder =
            VehicleProfileBundleBuilder(arguments).setVehicleTransmission(data).build()
        // TODO: Use this bundleBuilder to store the data in DB.
        findNavController().popBackStack(R.id.vehicle_list_fragment, false)
    }
}