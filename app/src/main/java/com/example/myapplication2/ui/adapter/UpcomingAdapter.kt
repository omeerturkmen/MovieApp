package com.example.myapplication2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.data.model.MovieBriefInfoModel
import com.example.myapplication2.databinding.ItemUpcomingBinding

class UpcomingAdapter(
    private val clickControl: (Long) -> Unit

) : RecyclerView.Adapter<UpcomingAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: ItemUpcomingBinding) : ViewHolder(binding.root)

    private val diffCallBack = object : DiffUtil.ItemCallback<MovieBriefInfoModel>() {
        override fun areItemsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var upcomingList: List<MovieBriefInfoModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemUpcomingBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.data = upcomingList[position]
        holder.binding.cardUpcoming.setOnClickListener {
            upcomingList[position].id?.let { it1 -> clickControl(it1) }
        }
    }

    override fun getItemCount(): Int {
        return upcomingList.size
    }
}