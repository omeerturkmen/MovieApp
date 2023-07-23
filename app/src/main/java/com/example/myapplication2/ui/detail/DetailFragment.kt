package com.example.myapplication2.ui.detail


import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.navigation.fragment.navArgs
import com.example.myapplication2.R
import com.example.myapplication2.core.base.BaseFragment
import com.example.myapplication2.core.extensions.collectLatestLifecycleFlow
import com.example.myapplication2.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : BaseFragment<DetailViewModel, FragmentDetailBinding>(
    DetailViewModel::class.java,
    FragmentDetailBinding::inflate
) {
    private val args: DetailFragmentArgs by navArgs()
    private val slowBreath: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.slow_breath
        )
    }

    override fun onCreateViewInvoke() {
        val myArgs = args.movieId.toString()
        viewModel.getDetails(myArgs)
        collectLatestLifecycleFlow(viewModel.state, ::handleViewState)
        with(fragmentBinding) {
            ivPoster.startAnimation(slowBreath)
            ivBackground.setOnClickListener {
                requireActivity().onBackPressedDispatcher.onBackPressed()
            }
        }
    }

    private fun handleViewState(uiState: DetailState) {
        setProgressStatus(uiState.isLoading)
        with(fragmentBinding) {
            data = uiState.myData
            runtime = "${uiState.myData?.runtime.toString()} MIN"
            rating = uiState.myData?.vote_average.toString().substring(0, 3)
        }
    }
}