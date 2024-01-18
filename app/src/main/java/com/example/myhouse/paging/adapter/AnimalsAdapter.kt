package com.example.myhouse.paging.adapter

import android.annotation.SuppressLint
import android.icu.number.Scale
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.myhouse.R
import javax.inject.Inject

class AnimalsAdapter @Inject constructor() :
    PagingDataAdapter<AnimalsListResponse.Result, AnimalsAdapter.ViewHolder>(differCallback) {

    inner class ViewHolder(private val binding: ItemAnimalsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(item: AnimalsListResponse.Result) {
            binding.apply {
                tvAnimalName.text = item.title
                tvAnimalDateRelease.text = item.releaseDate
                tvRate.text = item.voteAverage.toString()
                val animalPosterURL = POSTER_BASE_URL + item.posterPath
                imgAnimal.load(animalPosterURL) {
                    crossfade(true)
                    placeholder(R.drawable.poster_placeholder)
                    scale(Scale.FILL)
                }
                tvLang.text = item.originalLanguage

                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener: ((AnimalsListResponse.Result) -> Unit)? = null

    fun setOnItemClickListener(listener: (AnimalsListResponse.Result) -> Unit) {
        onItemClickListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemAnimalsBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getItem(position)?.let { holder.bind(it) }
        holder.setIsRecyclable(false)
    }

    companion object {
        val differCallback = object : DiffUtil.ItemCallback<AnimalsListResponse.Result>() {
            override fun areItemsTheSame(
                oldItem: AnimalsListResponse.Result,
                newItem: AnimalsListResponse.Result
            ): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(
                oldItem: AnimalsListResponse.Result,
                newItem: AnimalsListResponse.Result
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}
