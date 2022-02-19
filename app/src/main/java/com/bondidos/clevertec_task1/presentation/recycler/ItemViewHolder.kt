package com.bondidos.clevertec_task1.presentation.recycler

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bondidos.clevertec_task1.databinding.RecyclerItemBinding
import com.bondidos.clevertec_task1.domain.constants.Const.DISPLAY_NAME
import com.bondidos.clevertec_task1.domain.model.ItemModel

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
            model.image?.let{
                image.setImageURI(Uri.parse(model.image))
            }
            model.name?.let{
                firstName.text = model.name[DISPLAY_NAME]
            }
            number.text = model.number ?: ""
        }
    }
}