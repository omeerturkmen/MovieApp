package com.example.myapplication2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.data.model.MovieBriefInfoModel
import com.example.myapplication2.databinding.ItemTopRatedBinding

class TopRatedAdapter(
    private val clickControl: (Long) -> Unit

) : RecyclerView.Adapter<TopRatedAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: ItemTopRatedBinding) : ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<MovieBriefInfoModel>() {
        override fun areItemsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var topRatedList: List<MovieBriefInfoModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemTopRatedBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.data = topRatedList[position]
        holder.binding.image = topRatedList[position].poster_path
        holder.binding.date = topRatedList[position].release_date?.substring(0, 4)
        holder.binding.rating = topRatedList[position].vote_average.toString()
        holder.binding.cardImage.setOnClickListener {
            topRatedList[position].id?.let { selectedMovie -> clickControl(selectedMovie) }
        }
    }

    override fun getItemCount(): Int {
        return topRatedList.size
    }
}