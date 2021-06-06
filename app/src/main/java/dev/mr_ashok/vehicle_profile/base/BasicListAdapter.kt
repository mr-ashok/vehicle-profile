package dev.mr_ashok.vehicle_profile.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import dev.mr_ashok.vehicle_profile.databinding.BasicListItemBinding

typealias DataSelectionCallback = (data: String) -> Unit

object DiffCallback : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String) = oldItem == newItem

    override fun areContentsTheSame(oldItem: String, newItem: String) = oldItem == newItem
}

class BasicListViewHolder(
    private val binding: BasicListItemBinding,
    private val dataSelectionCallback: DataSelectionCallback
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(data: String) {
        binding.text1.run {
            text = data
            setOnClickListener { dataSelectionCallback(data) }
        }
    }
}

class BasicListAdapter(private val dataSelectionCallback: DataSelectionCallback) :
    ListAdapter<String, BasicListViewHolder>(DiffCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BasicListViewHolder {
        val binding =
            BasicListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BasicListViewHolder(binding, dataSelectionCallback)
    }

    override fun onBindViewHolder(holder: BasicListViewHolder, position: Int) =
        holder.bind(getItem(position))
}