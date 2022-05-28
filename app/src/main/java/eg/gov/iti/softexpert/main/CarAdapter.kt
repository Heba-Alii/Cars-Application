package eg.gov.iti.softexpert.main

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import eg.gov.iti.softexpert.business.entities.Car

class CarAdapter() :
    ListAdapter<Car, CarHolder>(CarsDiffCallback()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarHolder {
        return CarHolder.from(parent)
    }

    override fun onBindViewHolder(holder: CarHolder, position: Int) {
        currentList.elementAtOrNull(position)?.let { holder.bind(it) }
    }

    class CarsDiffCallback : DiffUtil.ItemCallback<Car>() {
        override fun areItemsTheSame(oldItem: Car, newItem: Car) =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Car, newItem: Car) =
            oldItem == newItem
    }

}