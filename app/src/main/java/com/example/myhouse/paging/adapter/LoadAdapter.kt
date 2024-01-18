package com.example.myhouse.paging.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView

class LoadAdapter(private val retry: () -> Unit) :
    LoadStateAdapter<LoadAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = LoadBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding, retry)
    }

    inner class ViewHolder(private val binding: LoadBinding, retry: () -> Unit) :
        RecyclerView.ViewHolder(binding.root) {

        init {
            binding.btnLoadRetry.setOnClickListener { retry() }
        }

        fun setData(state: LoadState) {
            binding.apply {
                prgBarLoad.isVisible = state is LoadState.Loading
                tvLoad.isVisible = state is LoadState.Error
                btnLoadRetry.isVisible = state is LoadState.Error
            }
        }
    }
}
