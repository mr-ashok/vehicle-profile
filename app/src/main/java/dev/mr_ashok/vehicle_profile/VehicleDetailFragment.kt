package dev.mr_ashok.vehicle_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import dev.mr_ashok.vehicle_profile.base.BaseFragment
import dev.mr_ashok.vehicle_profile.base.ErrorData
import dev.mr_ashok.vehicle_profile.databinding.VehicleDetailFragmentBinding
import java.util.*

class VehicleDetailFragment : BaseFragment<VehicleDetailFragmentBinding>() {

    private lateinit var viewModel: VehicleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleDetailFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val navController = findNavController()
        val appBarConfiguration = AppBarConfiguration(navController.graph)
        binding?.let {
            it.collapsingToolbar.setupWithNavController(
                it.toolbar,
                navController,
                appBarConfiguration
            )
        }

        val vehicleNumberArgument = VehicleDetailBundleBuilder.getVehicleNumber(arguments)
        if (vehicleNumberArgument == null) {
            this.setupErrorView(ErrorData(view.resources.getString(R.string.error_vehicle_number_not_provided)))
            return
        }

        viewModel = ViewModelProvider(requireActivity()).get(VehicleViewModel::class.java)
        viewModel.fetchVehicle(vehicleNumberArgument).observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            if (it.status == Status.LOADING) {
                setupLoadingView()
            } else if (it.status == Status.SUCCESS && it.data != null) {
                val data = it.data
                binding?.run {
                    toolbar.title = "${data.model} ${data.fuel}".uppercase(Locale.getDefault())
                    vehicleNumber.text = vehicleNumberArgument
                    makeValue.text = it.data.make
                    modelValue.text = it.data.model
                    fuelTypeValue.text = it.data.fuel
                    transmissionValue.text = it.data.transmission
                }
            } else {
                setupErrorView(ErrorData(requireContext().resources.getString(R.string.error_loading_data)))
            }
        })
    }

    private fun setupErrorView(data: ErrorData) {
        // TODO: Implement this method
    }

    private fun setupLoadingView() {
        // TODO: Implement this method
    }
}