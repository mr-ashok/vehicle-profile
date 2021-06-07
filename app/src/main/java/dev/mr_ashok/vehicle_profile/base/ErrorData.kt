package dev.mr_ashok.vehicle_profile.base

import android.view.View

data class ErrorData(val title: String, val retryClickListener: View.OnClickListener?, val description: String? = null)