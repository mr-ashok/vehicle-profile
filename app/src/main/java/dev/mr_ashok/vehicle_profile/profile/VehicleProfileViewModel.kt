package dev.mr_ashok.vehicle_profile.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.mr_ashok.vehicle_profile.Resource
import dev.mr_ashok.vehicle_profile.Status
import dev.mr_ashok.vehicle_profile.repository.VehicleRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://test.turbocare.app/"

fun <T> createNetworkCallback(livedata: MutableLiveData<Resource<T>>): Callback<T> {
    livedata.value = Resource(Status.LOADING)
    return object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            livedata.value = Resource(Status.SUCCESS, response.body())
        }

        override fun onFailure(call: Call<T>, throwable: Throwable) {
            livedata.value = Resource(Status.ERROR, null, throwable);
        }
    }
}

class VehicleProfileViewModel : ViewModel() {

    private val vehicleRepository: VehicleRepository
    private val vehicleMakeLiveData = MutableLiveData<Resource<List<String>>>()
    private val vehicleModelLiveData = MutableLiveData<Resource<List<String>>>()

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();
        vehicleRepository = retrofit.create(VehicleRepository::class.java)
    }

    fun fetchVehicleMake(vehicleClass: String): LiveData<Resource<List<String>>> {
        vehicleRepository.getMakeList(vehicleClass)
            .enqueue(createNetworkCallback(vehicleMakeLiveData))
        return vehicleMakeLiveData
    }

    fun fetchVehicleModel(vehicleClass: String, make: String): LiveData<Resource<List<String>>> {
        vehicleRepository.getModelList(vehicleClass, make)
            .enqueue(createNetworkCallback(vehicleModelLiveData))
        return vehicleModelLiveData
    }
}
