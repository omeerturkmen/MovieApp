package com.example.myapplication2.ui.search


import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication2.R
import com.example.myapplication2.core.base.BaseFragment
import com.example.myapplication2.core.extensions.collectLatestLifecycleFlow
import com.example.myapplication2.core.extensions.hideKeyboard
import com.example.myapplication2.data.model.MovieBriefInfoModel
import com.example.myapplication2.databinding.FragmentSearchBinding
import com.example.myapplication2.ui.adapter.SearchAdapter
import com.example.myapplication2.ui.adapter.TrendingAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : BaseFragment<SearchViewModel, FragmentSearchBinding>(
    SearchViewModel::class.java,
    FragmentSearchBinding::inflate
), SearchView.OnQueryTextListener {
    private var trendingAdapter: TrendingAdapter? = null
    private var searchAdapter: SearchAdapter? = null
    private var trendingList: ArrayList<MovieBriefInfoModel> = arrayListOf()
    private var searchList: ArrayList<MovieBriefInfoModel> = arrayListOf()
    private val breath: Animation by lazy {
        AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.breath
        )
    }

    override fun onCreateViewInvoke() {
        val searchView = fragmentBinding.searchView
        searchView.setOnQueryTextListener(this)
        collectLatestLifecycleFlow(viewModel.state, ::handleViewState)
        fragmentBinding.ivSearch.startAnimation(breath)
        trendingAdapter()
        searchAdapter()
        inItClickListener()
    }

    private fun handleViewState(uiState: SearchState) {
        setProgressStatus(uiState.isLoading)
        uiState.trendingList?.let {
            trendingList.clear()
            trendingAdapter?.trendingList = it
        }
        uiState.searchList?.let {
            searchList.clear()
            searchAdapter?.searchList = it
        }
    }

    private fun inItClickListener() = with(fragmentBinding) {
        layoutConstraint.setOnClickListener {
            hideKeyboard()
        }
        ivSearch.setOnClickListener {
            if (searchView.query.isNotEmpty()) {
                val textSubmit = searchView.query
                viewModel.getSearch(textSubmit.toString())
                fragmentBinding.searchControl = true
                hideKeyboard()
            }
        }
    }

    override fun onQueryTextSubmit(textSubmit: String?): Boolean {
        if (textSubmit != null) {
            viewModel.getSearch(textSubmit)
            fragmentBinding.searchControl = true
            hideKeyboard()
        }
        return true
    }

    override fun onQueryTextChange(newQuery: String?): Boolean {
        if (newQuery != null && newQuery.length > 3) {
            viewModel.getSearch(newQuery)
            fragmentBinding.searchControl = true
            searchAdapter?.searchList = searchList
        } else {
            fragmentBinding.searchControl = false
        }
        return true
    }

    private fun trendingAdapter() = with(fragmentBinding) {
        trendingAdapter = TrendingAdapter(::navigateToDetail)
        recyclerTrending.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        recyclerTrending.adapter = trendingAdapter
    }

    private fun searchAdapter() = with(fragmentBinding) {
        searchAdapter = SearchAdapter(::navigateToDetail)
        recyclerSearch.layoutManager = LinearLayoutManager(requireContext())
        recyclerSearch.adapter = searchAdapter
    }

    private fun navigateToDetail(movieId: Long) {
        val action = SearchFragmentDirections.actionSearchFragmentToDetailFragment(movieId)
        findNavController().navigate(action)
    }
}

