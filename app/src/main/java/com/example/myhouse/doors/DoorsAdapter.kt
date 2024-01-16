package com.example.myhouse.doors

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.myhouse.domain.DoorModel

class DoorsAdapter(
) : RecyclerView.Adapter<DoorsAdapter.DoorsViewHolder>() {

    private var list: List<DoorModel> = listOf()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(newList: List<DoorModel>) {
        list = newList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DoorsViewHolder {
        return DoorsViewHolder(
            ItemDoorBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DoorsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size

    inner class DoorsViewHolder(private val binding: ItemDoorBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(door: DoorModel) {
            with(binding) {
                tvTitle.text = door.name

                if (door.image != EMPTY_STRING) {
                    ivImage.load(door.image)
                    ivImage.visibility = View.VISIBLE
                    if (door.favorites) {
                        icStar.visibility = View.VISIBLE
                        icStarInTv.visibility = View.GONE
                    } else {
                        icStar.visibility = View.GONE
                    }
                } else if (door.favorites) {
                    icStar.visibility = View.GONE
                    icStarInTv.visibility = View.VISIBLE
                } else {
                    icStar.visibility = View.GONE
                    icStarInTv.visibility = View.GONE
                }
            }
        }
    }

}