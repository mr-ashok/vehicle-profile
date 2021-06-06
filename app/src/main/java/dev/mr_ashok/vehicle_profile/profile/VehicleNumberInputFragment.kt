package dev.mr_ashok.vehicle_profile.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import dev.mr_ashok.vehicle_profile.BaseFragment
import dev.mr_ashok.vehicle_profile.R
import dev.mr_ashok.vehicle_profile.databinding.VehicleProfileNumberFragmentBinding

class VehicleNumberInputFragment : BaseFragment<VehicleProfileNumberFragmentBinding>() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = VehicleProfileNumberFragmentBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.let {
            it.fab.setOnClickListener {
                // TODO: Add click listener behavior
            }
        }
    }
}
