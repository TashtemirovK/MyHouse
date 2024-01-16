package com.example.myhouse.utils.cameras

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.my.camera_monitoring_7month.data.utils.Constants.NOT_FOUND
import com.example.my.camera_monitoring_7month.databinding.FragmentCamerasBinding
import com.example.my.camera_monitoring_7month.presentation.utils.UiState
import com.example.myhouse.R
import com.example.myhouse.presentation.bases.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CamerasFragment : BaseFragment<FragmentCamerasBinding, CamerasViewModel>(R.layout.fragment_cameras) {

    override val viewModel: CamerasViewModel by viewModels()
    private val adapter = CamerasAdapter()

    override fun inflateBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentCamerasBinding.inflate(inflater, container, false)

    override fun initView() {
        super.initView()
        binding.recyclerView.adapter = adapter
        viewModel.getAllCameras()
    }

    override fun initLiveData() {
        super.initLiveData()
        lifecycleScope.launch {
            viewModel.camerasList.collect { result ->
                when (result) {
                    is UiState.Loading -> binding.progressBar.visibility = View.VISIBLE

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
            viewModel.refreshCameras()
            binding.swipeRefreshLayout.isRefreshing = false
        }
    }

}