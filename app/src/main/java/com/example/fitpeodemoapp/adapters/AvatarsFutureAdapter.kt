package com.example.fitpeodemoapp.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitpeodemoapp.databinding.AvatarItemsBinding

class AvatarsFutureAdapter(val avatarsList: List<Drawable?>) : RecyclerView.Adapter<AvatarsFutureAdapter.AvatarsFutureViewHolder>() {

    class AvatarsFutureViewHolder(val binding: AvatarItemsBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(image: Drawable){
            Glide.with(binding.root).load(image).into(binding.profileImageSingle)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarsFutureViewHolder {
        return AvatarsFutureViewHolder(AvatarItemsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AvatarsFutureViewHolder, position: Int) {
        val image=avatarsList[position]
        holder.bind(image!!)
    }

    override fun getItemCount(): Int {
       return avatarsList.size
    }
}