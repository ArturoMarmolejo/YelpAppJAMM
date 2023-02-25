package com.arturomarmolejo.yelpappjamm.views.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arturomarmolejo.yelpappjamm.databinding.RvRestaurantItemBinding
import com.arturomarmolejo.yelpappjamm.model.domain.ModelBusinesses
import com.arturomarmolejo.yelpappjamm.model.items.Businesses

class BusinessesListAdapter(
    private val itemSet : MutableList<ModelBusinesses> = mutableListOf(),
    private val onClickBusinessDetails : (item: ModelBusinesses) -> Unit
): RecyclerView.Adapter<BusinessesListViewHolder>() {

    fun updateBusinesses(newItems: List<ModelBusinesses>) {
        if(itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)

            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BusinessesListViewHolder {
        return BusinessesListViewHolder(
            RvRestaurantItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = itemSet.size
    override fun onBindViewHolder(holder: BusinessesListViewHolder, position: Int) {
        holder.bind(itemSet[position], onClickBusinessDetails)
    }

}

class BusinessesListViewHolder(
    private val binding: RvRestaurantItemBinding
): RecyclerView.ViewHolder(binding.root) {
    private fun Double.format(digits: Int) = "%.${digits}f".format(2)
    private fun Double.toMiles(): String = (this * 0.000189394).format(2)

    fun bind(item: ModelBusinesses, onClickBusinessDetails: (item: ModelBusinesses) -> Unit ) {
        binding.restaurantName.text = item.name

        itemView.setOnClickListener {
            item.let { onClickBusinessDetails(it) }
        }

    }



}