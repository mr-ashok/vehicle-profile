package dev.mr_ashok.vehicle_profile

import android.view.View
import dev.mr_ashok.vehicle_profile.base.ErrorData
import dev.mr_ashok.vehicle_profile.databinding.ErrorSectionBinding

fun bindErrorViews(binding: ErrorSectionBinding, data: ErrorData) {
    binding.run {
        errorTitle.text = data.title
        errorRetryButton.visibility =
            if (data.retryClickListener != null) View.VISIBLE else View.GONE
        errorRetryButton.setOnClickListener(data.retryClickListener)
        errorDescription.visibility = if (data.description != null) View.VISIBLE else View.GONE
        if (data.description != null) {
            errorDescription.text = data.description
        }
    }
}