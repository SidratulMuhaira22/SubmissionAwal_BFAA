package com.sidratul.submission1bfaa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sidratul.submission1bfaa.data.GithubResponse
import com.sidratul.submission1bfaa.databinding.ItemUserBinding

class UserAdapter(private val listUser: List<GithubResponse>, private val onClick: (GithubResponse) -> Unit = {}) : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    class ViewHolder(var binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binding.ItemUsername.text = listUser[holder.adapterPosition].login
        Glide.with(holder.itemView)
            .load(listUser[holder.adapterPosition].avatar_url)
            .into(holder.binding.ItemAvatar)

        holder.itemView.setOnClickListener{
            onClick(listUser[holder.adapterPosition])
        }
    }

    override fun getItemCount(): Int = listUser.size

}