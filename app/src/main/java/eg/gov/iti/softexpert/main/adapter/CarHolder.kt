package eg.gov.iti.softexpert.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import eg.gov.iti.softexpert.business.entities.Car
import eg.gov.iti.softexpert.databinding.CarItemBinding


class CarHolder(private val binding: CarItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(car: Car) {
        binding.car = car

    }

    companion object {
        fun from(parent: ViewGroup): CarHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = CarItemBinding.inflate(layoutInflater, parent, false)
            return CarHolder(binding)
        }
    }

}

