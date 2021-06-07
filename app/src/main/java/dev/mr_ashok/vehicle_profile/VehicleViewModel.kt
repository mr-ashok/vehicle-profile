package dev.mr_ashok.vehicle_profile

import android.app.Application
import android.database.sqlite.SQLiteConstraintException
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dev.mr_ashok.vehicle_profile.db.AppDatabase
import dev.mr_ashok.vehicle_profile.db.Vehicle
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class VehicleViewModel(application: Application) : AndroidViewModel(application) {

    private val vehicleDao = AppDatabase.getDatabase(application).vehicleDao()

    fun fetchVehicle(number: String): LiveData<Resource<Vehicle>> {
        val liveData = MutableLiveData<Resource<Vehicle>>(Resource(Status.LOADING))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = vehicleDao.findVehicle(number)
                liveData.postValue(Resource(Status.SUCCESS, data))
            } catch (err: Exception) {
                liveData.postValue(Resource(Status.ERROR, null, err))
            }
        }
        return liveData
    }

    fun fetchVehicleList(): LiveData<Resource<List<Vehicle>>> {
        val liveData = MutableLiveData<Resource<List<Vehicle>>>(Resource(Status.LOADING))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = vehicleDao.fetchVehicleList()
                liveData.postValue(Resource(Status.SUCCESS, data))
            } catch (err: Exception) {
                liveData.postValue(Resource(Status.ERROR, null, err))
            }
        }
        return liveData
    }

    fun insertVehicle(vehicle: Vehicle): LiveData<Resource<Boolean>> {
        val liveData = MutableLiveData<Resource<Boolean>>(Resource(Status.LOADING))
        viewModelScope.launch(Dispatchers.IO) {
            try {
                vehicleDao.insert(vehicle)
                liveData.postValue(Resource(Status.SUCCESS, true))
            } catch (err: Exception) {
                liveData.postValue(Resource(Status.ERROR, null, err))
            }
        }
        return liveData
    }
}