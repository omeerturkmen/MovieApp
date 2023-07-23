package com.example.myapplication2.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.myapplication2.data.model.MovieBriefInfoModel
import com.example.myapplication2.databinding.ItemSearchBinding

class SearchAdapter(
    private val clickControl: (Long) -> Unit

) : RecyclerView.Adapter<SearchAdapter.MyViewHolder>() {
    class MyViewHolder(var binding: ItemSearchBinding) : ViewHolder(binding.root)
    private val diffCallBack = object : DiffUtil.ItemCallback<MovieBriefInfoModel>() {
        override fun areItemsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem

        override fun areContentsTheSame(oldItem: MovieBriefInfoModel, newItem: MovieBriefInfoModel) =
            oldItem == newItem
    }

    private val listDiffer = AsyncListDiffer(this, diffCallBack)

    var searchList: List<MovieBriefInfoModel>
        get() = listDiffer.currentList
        set(value) = listDiffer.submitList(value)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ItemSearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.binding.data = searchList[position]
        if (searchList[position].release_date?.isNotEmpty() == true) {
            holder.binding.date = searchList[position].release_date?.substring(0, 4)
        }
        holder.binding.rating = searchList[position].vote_average?.toString()?.substring(0, 3)
        holder.binding.cardSearch.setOnClickListener {
            searchList[position].id?.let { selectedMovie -> clickControl(selectedMovie) }
        }
    }

    override fun getItemCount(): Int {
        return searchList.size
    }
}