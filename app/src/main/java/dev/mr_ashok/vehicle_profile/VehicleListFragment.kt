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
            vehicleDetailList.addItemDecoration(
                DividerItemDecoration(
                    context,
                    DividerItemDecoration.VERTICAL
                )
            )
            vehicleDetailList.layoutManager = LinearLayoutManager(context)
        }
        viewModel = ViewModelProvider(requireActivity()).get(VehicleViewModel::class.java)
        fetchData()
    }

    private fun setupErrorView(data: ErrorData) {
        binding?.run {
            vehicleDetailList.visibility = View.GONE
            emptySection.root.visibility = View.GONE
            fab.visibility = View.GONE
            errorSection.root.visibility = View.GONE
            loadingSection.root.visibility = View.VISIBLE
            bindErrorViews(errorSection, data)
        }
    }

    private fun setupLoadingView() {
        binding?.run {
            vehicleDetailList.visibility = View.GONE
            emptySection.root.visibility = View.GONE
            fab.visibility = View.GONE
            errorSection.root.visibility = View.GONE
            loadingSection.root.visibility = View.VISIBLE
        }
    }

    private fun fetchData() {
        viewModel.fetchVehicleList().observe(viewLifecycleOwner, Observer {
            if (it == null) {
                return@Observer
            }
            if (it.status == Status.LOADING) {
                setupLoadingView()
            } else if (it.status == Status.SUCCESS && it.data != null) {
                binding?.run {
                    if (it.data.isEmpty()) {
                        vehicleDetailList.visibility = View.GONE
                        emptySection.root.visibility = View.VISIBLE
                    } else {
                        vehicleDetailList.visibility = View.VISIBLE
                        emptySection.root.visibility = View.GONE
                    }

                    fab.visibility = View.VISIBLE
                    errorSection.root.visibility = View.GONE
                    loadingSection.root.visibility = View.GONE
                }
                adapter.submitList(it.data)
            } else {
                setupErrorView(
                    ErrorData(
                        requireContext().resources.getString(R.string.error_loading_data),
                        { fetchData() }
                    )
                )
            }
        })
    }
}