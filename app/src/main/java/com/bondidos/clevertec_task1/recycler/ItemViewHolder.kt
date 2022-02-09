package com.bondidos.clevertec_task1.recycler

import androidx.recyclerview.widget.RecyclerView
import com.bondidos.clevertec_task1.databinding.RecyclerItemBinding
import com.bondidos.clevertec_task1.model.ItemModel

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
            // here will be load image into imageView :)
            title.text = model.title
            description.text = model.description
        }
    }
}