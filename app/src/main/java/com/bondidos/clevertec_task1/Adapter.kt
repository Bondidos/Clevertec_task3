package com.bondidos.clevertec_task1

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.clevertec_task1.databinding.RecyclerItemBinding

class Adapter(private val onClick: (id: Int) -> Unit) : RecyclerView.Adapter<ItemViewHolder>() {

    private var list: MutableList<ItemModel> = mutableListOf()
    var adapterPosition: Int = 0
        private set

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding =
            RecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        adapterPosition = holder.adapterPosition
        holder.onBind(list[position], position)
    }

    override fun getItemCount() = list.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<ItemModel>) {
        list.clear()
        list.addAll(newList)
        notifyDataSetChanged()
    }
}

class ItemViewHolder(
    private val binding: RecyclerItemBinding,
    private val onClick: (id: Int) -> Unit
) :
    RecyclerView.ViewHolder(binding.root) {

    fun onBind(model: ItemModel, position: Int) {

        itemView.setOnClickListener {
            onClick.invoke(position)
        }
        with(binding) {
            title.text = model.title
            description.text = model.description
        }
    }
}