package com.bondidos.clevertec_task1.presentation.ui.recycler

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.clevertec_task1.databinding.RecyclerItemBinding
import com.bondidos.clevertec_task1.domain.model.ItemModel

class Adapter(private val onClick: (item: ItemModel) -> Unit) :
    RecyclerView.Adapter<ItemViewHolder>() {

    private val list: MutableList<ItemModel> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.onBind(list[position])

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<ItemModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}
