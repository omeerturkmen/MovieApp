package com.example.myapplication2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.data.model.MovieBriefInfoModel
import com.example.myapplication2.databinding.ItemNowPlayingBinding

class NowPlayingAdapter(
    private val clickControl: (Long) -> Unit

) : RecyclerView.Adapter<NowPlayingAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: ItemNowPlayingBinding) : ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<MovieBriefInfoModel>() {
        override fun areItemsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var nowPlayingList: List<MovieBriefInfoModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemNowPlayingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.data = nowPlayingList[position]
        holder.binding.rating = nowPlayingList[position].vote_average.toString()
        holder.binding.cardNowPlaying.setOnClickListener {
            nowPlayingList[position].id?.let { selectedMovie -> clickControl(selectedMovie) }
        }
    }

    override fun getItemCount(): Int {
        return nowPlayingList.size
    }
}