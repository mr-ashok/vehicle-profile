package dev.mr_ashok.vehicle_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dev.mr_ashok.vehicle_profile.base.BaseFragment
import dev.mr_ashok.vehicle_profile.base.ErrorData
import dev.mr_ashok.vehicle_profile.databinding.VehicleListFragmentBinding

class VehicleListFragment : BaseFragment<VehicleListFragmentBinding>() {

    lateinit var viewModel: VehicleViewModel
    private val adapter: VehicleListAdapter = VehicleListAdapter {
        findNavController().navigate(
            R.id.action_view_vehicle_detail,
            VehicleDetailBundleBuilder().setVehicleData(it).build()
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.run {
            fab.setOnClickListener {
                findNavController().navigate(R.id.action_add_vehicle)
            }
            vehicleDetailList.adapter = adapter
            vehicleDetailList.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            vehicleDetailList.layoutManager = LinearLayoutManager(context)
        }
        viewModel = ViewModelProvider(requireActivity()).get(VehicleViewModel::class.java)
        viewModel.fetchVehicleList().observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            if (it.status == Status.LOADING) {
                setupLoadingView()
            } else if (it.status == Status.SUCCESS && it.data != null) {
                adapter.submitList(it.data)
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