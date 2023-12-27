package com.example.exam6.presentation.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.exam6.databinding.ItemButtonBinding


class ButtonsRecyclerAdapter :
    ListAdapter<ButtonType, ButtonsRecyclerAdapter.ButtonsViewHolder>(
        DiffUtilCallback()
    ) {

    private var onItemClickListener: ((id: Int)-> Unit)? = null

    fun setOnItemClickListener(listener: (id: Int)-> Unit) {
        onItemClickListener = listener
    }

    inner class ButtonsViewHolder(private val binding: ItemButtonBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind() {
            val item = currentList[adapterPosition]
            with(binding) {
                btn.text = item.value.toString()
                btn.setOnClickListener {
                    onItemClickListener?.invoke(item.value)
                }
            }
        }
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<ButtonType>() {
        override fun areItemsTheSame(
            oldItem: ButtonType,
            newItem: ButtonType
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: ButtonType,
            newItem: ButtonType
        ): Boolean {
            return oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ButtonsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding = ItemButtonBinding.inflate(layoutInflater, parent, false)
        return ButtonsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ButtonsViewHolder, position: Int) {
        holder.bind()
    }
}