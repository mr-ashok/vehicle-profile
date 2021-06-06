package dev.mr_ashok.vehicle_profile

import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

open class BaseFragment<T: ViewBinding>: Fragment() {
    protected var binding: T? = null;

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}