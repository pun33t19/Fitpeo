package com.example.fitpeodemoapp.adapters

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fitpeodemoapp.databinding.AvatarItemsBinding



class AvatarsAdapter(val imagesList: List<Drawable?>) : RecyclerView.Adapter<AvatarsAdapter.AvatarsViewHolder>() {


    class AvatarsViewHolder(val binding:AvatarItemsBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(image:Drawable){
            Glide.with(binding.root).load(image).into(binding.profileImageSingle)
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AvatarsViewHolder {
        return AvatarsViewHolder(AvatarItemsBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: AvatarsViewHolder, position: Int) {
        val items=imagesList[position]
        holder.bind(items!!)
    }

    override fun getItemCount(): Int {
        return imagesList.size
    }


}