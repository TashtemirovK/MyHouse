package com.example.myhouse.presentation.bases

import android.databinding.tool.writer.ViewBinding
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

abstract class BaseFragment<VB : ViewBinding, VM : ViewModel>(@LayoutRes idLayout: Int, viewModelClass: Class<VM>) :
    Fragment(idLayout) {

    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding ?: throw IllegalStateException("Binding is null. Fragment is destroyed or not yet created.")

    protected val viewModel: VM by viewModels { ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application) }

    abstract fun createBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = createBinding(inflater, container)
        return binding.root
    }

}
override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
    super.onViewCreated(view, savedInstanceState)
    initClick()
    initLiveData()
    initView()
}

override fun onDestroyView() {
    super.onDestroyView()
    _binding = null
}

open fun initLiveData() {}
open fun initClick() {}
open fun initView() {}
}