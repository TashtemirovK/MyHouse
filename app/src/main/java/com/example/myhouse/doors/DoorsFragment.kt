package com.example.myhouse.doors

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.myhouse.R
import com.example.myhouse.presentation.bases.BaseFragment
import com.example.myhouse.utils.Constants.NOT_FOUND
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import perfetto.protos.UiState

@AndroidEntryPoint
class DoorsFragment : BaseFragment<FragmentDoorsBinding, DoorsViewModel>(R.layout.fragment_doors) {

    override val viewModel: DoorsViewModel by viewModels()
    private val adapter = DoorsAdapter()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentDoorsBinding.inflate(layoutInflater, container, false)

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        viewModel.getAllDoors()
    }

    override fun initLiveData() {
        super.initLiveData()
        lifecycleScope.launch {
            viewModel.doorsList.collect { result ->
                when (result) {
                    is UiState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }

                    is UiState.Success -> {
                        adapter.setList(result.data!!)
                        binding.progressBar.visibility = View.GONE
                    }

                    is UiState.Empty -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), NOT_FOUND, Toast.LENGTH_SHORT).show()
                    }

                    is UiState.Error -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(requireContext(), result.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }
    }

    override fun initClick() {
        super.initClick()
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.refreshDoors()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

}