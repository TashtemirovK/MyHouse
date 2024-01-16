package com.example.myhouse.utils.cameras

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.my.camera_monitoring_7month.databinding.ItemCameraBinding
import com.example.myhouse.domain.CameraModel


class CamerasAdapter : RecyclerView.Adapter<CamerasAdapter.CamerasViewHolder>() {

    private var list: List<CameraModel> = listOf()

    fun setList(newList: List<CameraModel>) {
        val diffResult = DiffUtil.calculateDiff(CameraDiffCallback(list, newList))
        list = newList
        diffResult.dispatchUpdatesTo(this)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CamerasViewHolder {
        return CamerasViewHolder(
            ItemCameraBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

}

class CameraDiffCallback(
    private val oldList: List<CameraModel>,
    private val newList: List<CameraModel>
) : DiffUtil.Callback() {
    override fun getOldListSize(): Int = oldList.size
    override fun getNewListSize(): Int = newList.size
    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
