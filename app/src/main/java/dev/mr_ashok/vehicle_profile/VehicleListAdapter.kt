package dev.mr_ashok.vehicle_profile

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.mr_ashok.vehicle_profile.databinding.VehicleListItemBinding
import dev.mr_ashok.vehicle_profile.db.Vehicle

typealias DataSelectionCallback = (vehicleNumber: String) -> Unit

object DiffCallback : DiffUtil.ItemCallback<Vehicle>() {
    override fun areItemsTheSame(oldItem: Vehicle, newItem: Vehicle) = oldItem == newItem

    override fun areContentsTheSame(oldItem: Vehicle, newItem: Vehicle) = oldItem == newItem
}

class VehicleListViewHolder(
    private val binding: VehicleListItemBinding,
    private val dataSelectionCallback: DataSelectionCallback
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: Vehicle) {
        binding.vehicleNumber.text = data.number
        binding.vehicleModel.text = data.model
        binding.root.setOnClickListener { dataSelectionCallback(data.number) }
    }
}

class VehicleListAdapter(private val dataSelectionCallback: DataSelectionCallback) :
    ListAdapter<Vehicle, VehicleListViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VehicleListViewHolder {
        val binding =
            VehicleListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VehicleListViewHolder(binding, dataSelectionCallback)
    }

    override fun onBindViewHolder(holder: VehicleListViewHolder, position: Int) =
        holder.bind(getItem(position))
}