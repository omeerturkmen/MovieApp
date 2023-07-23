package com.example.myapplication2.ui.main


import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.core.base.BaseFragment
import com.example.myapplication2.core.extensions.collectLatestLifecycleFlow
import com.example.myapplication2.data.model.MovieBriefInfoModel
import com.example.myapplication2.databinding.FragmentMainBinding
import com.example.myapplication2.ui.adapter.PopularAdapter
import com.example.myapplication2.ui.adapter.TopRatedAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.ArrayList

@AndroidEntryPoint
class MainFragment : BaseFragment<MainViewModel, FragmentMainBinding>(
    MainViewModel::class.java,
    FragmentMainBinding::inflate
) {
    private var topRatedAdapter: TopRatedAdapter? = null
    private var popularAdapter: PopularAdapter? = null
    private var topRatedList: ArrayList<MovieBriefInfoModel> = arrayListOf()
    private var popularList: ArrayList<MovieBriefInfoModel> = arrayListOf()
    private val handler = Handler(Looper.getMainLooper())
    private var runnable: Runnable = Runnable { }
    private val delay = 4000L

    override fun onCreateViewInvoke() {
        collectLatestLifecycleFlow(viewModel.state, ::handleViewState)
        adapterTopRated()
        adapterPopular()
        handlerRunnable()
    }

    private fun handleViewState(uiState: MainState) {
        setProgressStatus(uiState.isLoading)
        uiState.popularList?.let {
            popularList.clear()
            popularAdapter?.popularList = it
        }
        uiState.topRatedList?.let {
            topRatedList.clear()
            topRatedAdapter?.topRatedList = it
        }
    }

    private fun adapterTopRated() = with(fragmentBinding) {
        topRatedAdapter = TopRatedAdapter(::navigateToDetail)
        recyclerTopRated.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recyclerTopRated.adapter = topRatedAdapter
        val snapHelper = LinearSnapHelper()
        snapHelper.attachToRecyclerView(recyclerTopRated)
        topRatedAdapter?.topRatedList = topRatedList
    }

    private fun adapterPopular() = with(fragmentBinding) {
        popularAdapter = PopularAdapter(::navigateToDetail)
        recyclerPopular.layoutManager = LinearLayoutManager(requireContext())
        recyclerPopular.adapter = popularAdapter
        recyclerPopular.smoothScrollToPosition(0)
        popularAdapter?.popularList = popularList
    }

    private fun handlerRunnable() {
        runnable = object : Runnable {
            override fun run() {
                val currentPosition =
                    (fragmentBinding.recyclerTopRated.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                val newPosition = currentPosition + 1
                val resetPosition = 0
                if (newPosition > 19) {
                    fragmentBinding.recyclerTopRated.smoothScrollToPosition(resetPosition)
                } else {
                    fragmentBinding.recyclerTopRated.smoothScrollToPosition(newPosition)
                    handler.postDelayed(this, delay)
                }
            }
        }
        handler.post(runnable)
    }

    private fun navigateToDetail(movieId: Long) {
        val action = MainFragmentDirections.actionMainFragmentToDetailFragment(movieId)
        findNavController().navigate(action)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }
}