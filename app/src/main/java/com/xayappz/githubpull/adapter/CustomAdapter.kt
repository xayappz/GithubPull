package com.xayappz.githubpull.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.xayappz.githubpull.BR
import com.xayappz.githubpull.R
import com.xayappz.githubpull.databinding.RepoListLayoutBinding
import com.xayappz.githubpull.helper.RepoPull

class CustomAdapter(var context: Context, val userList: List<RepoPull>) :
    RecyclerView.Adapter<CustomAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding: RepoListLayoutBinding = DataBindingUtil.inflate(
            LayoutInflater.from(context),
            R.layout.repo_list_layout,
            parent,
            false
        )
        return ViewHolder(binding)

    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind( userList[position])
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class ViewHolder(val binding: RepoListLayoutBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Any) {
            binding.setVariable(BR.repo, data)
        }
    }

}