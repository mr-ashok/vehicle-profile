package dev.mr_ashok.vehicle_profile.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import dev.mr_ashok.vehicle_profile.R
import dev.mr_ashok.vehicle_profile.Status
import dev.mr_ashok.vehicle_profile.base.BasicListFragment
import dev.mr_ashok.vehicle_profile.base.ErrorData

class VehicleMakeSelectionListFragment : BasicListFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val vehicleClass = VehicleProfileBundleBuilder(arguments).getVehicleClass()
            ?: throw RuntimeException("Vehicle class is not provided")
        fetchData(vehicleClass)
    }

    override fun onDataSelected(data: String) {
        findNavController().navigate(
            R.id.action_next,
            VehicleProfileBundleBuilder(arguments).setVehicleMake(data).build()
        )
    }

    private fun fetchData(vehicleClass: String) {
        ViewModelProvider(this)
            .get(VehicleProfileViewModel::class.java)
            .fetchVehicleMake(vehicleClass)
            .observe(viewLifecycleOwner, Observer {
                if (it == null) {
                    return@Observer
                }
                if (it.status == Status.LOADING) {
                    setupLoadingView()
                } else if (it.status == Status.SUCCESS && it.data != null) {
                    setListData(it.data)
                } else {
                    setupErrorView(
                        ErrorData(
                            requireContext().resources.getString(R.string.error_loading_data),
                            { fetchData(vehicleClass) }
                        )
                    )
                }
            })
    }
}