package dev.mr_ashok.vehicle_profile

enum class Status {
    LOADING,
    SUCCESS,
    ERROR
}

data class Resource<T>(val status: Status, val data: T? = null, val error: Throwable? = null)