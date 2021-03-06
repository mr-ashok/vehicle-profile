package dev.mr_ashok.vehicle_profile.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import dev.mr_ashok.vehicle_profile.R
import dev.mr_ashok.vehicle_profile.Status
import dev.mr_ashok.vehicle_profile.VehicleViewModel
import dev.mr_ashok.vehicle_profile.base.BasicListFragment
import dev.mr_ashok.vehicle_profile.base.ErrorData

private enum class VehicleTransmission {
    MANUAL,
    AUTOMATIC
}

class VehicleTransmissionSelectionListFragment : BasicListFragment() {

    lateinit var viewModel: VehicleViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setListData(VehicleTransmission.values().map { it.toString() })
        viewModel = ViewModelProvider(requireActivity()).get(VehicleViewModel::class.java)
    }

    override fun onDataSelected(data: String) {
        val bundleBuilder =
            VehicleProfileBundleBuilder(arguments).setVehicleTransmission(data)
        insertVehicleData(bundleBuilder, viewModel)
            .observe(viewLifecycleOwner, Observer {
                if (it == null) {
                    return@Observer
                }
                if (it.status == Status.SUCCESS && it.data == true) {
                    val activityView =
                        requireView().rootView.findViewById<View>(R.id.activity_root_view)
                    if (activityView != null) {
                        Snackbar.make(
                            activityView,
                            R.string.insert_vehicle_confirmation,
                            Snackbar.LENGTH_LONG
                        ).show()
                    }
                    findNavController().popBackStack(R.id.vehicle_list_fragment, false)
                } else if (it.status == Status.LOADING) {
                    setupLoadingView(R.string.saving)
                } else {
                    setupErrorView(
                        ErrorData(
                            requireContext().resources.getString(R.string.error_saving_data),
                            null,
                            it.error?.localizedMessage
                        )
                    )
                }
            })
    }
}