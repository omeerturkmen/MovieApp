package com.example.myapplication2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.data.model.MovieBriefInfoModel
import com.example.myapplication2.databinding.ItemTrendingBinding

class TrendingAdapter(
    private val clickControl: (Long) -> Unit

) : RecyclerView.Adapter<TrendingAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: ItemTrendingBinding) : ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<MovieBriefInfoModel>() {
        override fun areItemsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var trendingList: List<MovieBriefInfoModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTrendingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.data = trendingList[position]
        holder.binding.popularity = (position + 1).toString()
        holder.binding.cardTrending.setOnClickListener {
            trendingList[position].id?.let { selectedMovie -> clickControl(selectedMovie) }
        }
    }

    override fun getItemCount(): Int {
        return trendingList.size
    }
}