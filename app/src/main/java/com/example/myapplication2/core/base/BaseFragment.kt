package com.example.myapplication2.core.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication2.R

abstract class BaseFragment<VM : ViewModel, DB : ViewDataBinding>(
private val viewModelClass: Class<VM>,
private val inflateLayout: (LayoutInflater, ViewGroup?, Boolean) -> DB,
) : Fragment() {
    private var _fragmentBinding: DB? = null
    protected val fragmentBinding get() = _fragmentBinding!!

    protected val progressBar: View? by lazy {
        activity?.findViewById(R.id.progressBar)
    }

    protected val viewModel: VM by lazy {
        ViewModelProvider(this)[viewModelClass]
    }

    protected fun setProgressStatus(isLoading: Boolean) {
        progressBar?.isVisible = isLoading
    }

    open fun onCreateViewInvoke() {}

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _fragmentBinding = inflateLayout(layoutInflater, container, false)
        onCreateViewInvoke()
        return fragmentBinding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _fragmentBinding = null
    }
}