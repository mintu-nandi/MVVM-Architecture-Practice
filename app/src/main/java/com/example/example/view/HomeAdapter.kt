package com.example.example.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.example.databinding.ItemLayoutBinding
import com.example.example.model.Todo

class HomeAdapter(private var list: List<Todo>) : RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {
    class HomeViewHolder(private val binding: ItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bindView(data: Todo) {
            binding.textViewName.text = data.title
            binding.textViewEmail.text = data.userId.toString()

            Glide.with(binding.root)
                .load("https://i.picsum.photos/id/145/200/300.jpg?grayscale&hmac=Aw1QzWPtzUCHhbtYuwxcDSdwm2r39Ovyxm0UVut74E4")
                .into(binding.profile)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        return HomeViewHolder(ItemLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        holder.bindView(list[position])
    }

    fun addProperties(todos: List<Todo>) {
        list = todos
    }
}
