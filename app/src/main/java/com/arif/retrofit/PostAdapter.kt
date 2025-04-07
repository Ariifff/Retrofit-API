package com.arif.retrofit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.arif.retrofit.databinding.PostItemBinding

class PostAdapter(var postList: ArrayList<Posts>) : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    inner class PostViewHolder(val adapterbinding : PostItemBinding)
        : RecyclerView.ViewHolder(adapterbinding.root){}

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val binding= PostItemBinding.inflate(
            LayoutInflater.from(parent.context),parent,false)
        return PostViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return postList.size
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        holder.adapterbinding.textViewuserid.text = postList[position].userId.toString()
        holder.adapterbinding.textViewid.text = postList[position].id.toString()
        holder.adapterbinding.textViewtitle.text = postList[position].title
        holder.adapterbinding.textViewbody.text = postList[position].subtitle

    }


}