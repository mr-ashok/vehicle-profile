package dev.mr_ashok.vehicle_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.mr_ashok.vehicle_profile.base.BaseFragment
import dev.mr_ashok.vehicle_profile.databinding.VehicleListFragmentBinding

class VehicleListFragment : BaseFragment<VehicleListFragmentBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleListFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            it.fab.setOnClickListener {
                findNavController().navigate(R.id.action_add_vehicle)
            }
        }
    }
}