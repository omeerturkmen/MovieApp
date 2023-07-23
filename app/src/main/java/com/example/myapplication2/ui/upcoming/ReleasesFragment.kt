package com.example.myapplication2.ui.upcoming

import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.core.base.BaseFragment
import com.example.myapplication2.core.extensions.collectLatestLifecycleFlow
import com.example.myapplication2.data.model.MovieBriefInfoModel
import com.example.myapplication2.databinding.FragmentReleasesBinding
import com.example.myapplication2.ui.adapter.NowPlayingAdapter
import com.example.myapplication2.ui.adapter.UpcomingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ReleasesFragment : BaseFragment<ReleasesViewModel, FragmentReleasesBinding>(
    ReleasesViewModel::class.java,
    FragmentReleasesBinding::inflate
) {
    private var upcomingList: ArrayList<MovieBriefInfoModel> = arrayListOf()
    private var nowPlayingList: ArrayList<MovieBriefInfoModel> = arrayListOf()
    private var upcomingAdapter: UpcomingAdapter? = null
    private var nowPlayingAdapter: NowPlayingAdapter? = null

    override fun onCreateViewInvoke() {
        collectLatestLifecycleFlow(viewModel.state, ::handleViewState)
        upcomingAdapter()
        nowPlayingAdapter()
    }

    private fun handleViewState(uiState: ReleasesState) {
        setProgressStatus(uiState.isLoading)
        uiState.upcomingList?.let {
            upcomingList.clear()
            upcomingAdapter?.upcomingList = it
        }
        uiState.nowPlayingList?.let {
            nowPlayingList.clear()
            nowPlayingAdapter?.nowPlayingList = it
        }
    }

    private fun upcomingAdapter() = with(fragmentBinding) {
        upcomingAdapter = UpcomingAdapter(::navigateToDetail)
        recyclerUpcoming.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recyclerUpcoming.adapter = upcomingAdapter
        recyclerUpcoming.smoothScrollToPosition(0)
    }

    private fun nowPlayingAdapter() = with(fragmentBinding) {
        nowPlayingAdapter = NowPlayingAdapter(::navigateToDetail)
        recyclerNowPlaying.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recyclerNowPlaying.adapter = nowPlayingAdapter
        recyclerNowPlaying.smoothScrollToPosition(0)
    }

    private fun navigateToDetail(movieId: Long) {
        val action = ReleasesFragmentDirections.actionReleasesFragmentToDetailFragment(movieId)
        findNavController().navigate(action)
    }
}