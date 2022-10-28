package com.saeed.zanjan.ui_home.components

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.compose.runtime.MutableState
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.saeed.zanjan.domain.FirstBanners
import com.saeed.zanjan.ui_home.ListState
import com.saeed.zanjan.ui_home.databinding.BannersItemBinding

class Adapter (val banners:List<FirstBanners>):RecyclerView.Adapter<Adapter.ViewHolder>() {
  //  private lateinit  var recyclerViewData: List<FirstBanners>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding=BannersItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.binder(banners[position])
    }

    override fun getItemCount(): Int {

        return banners.size
    }
   /* fun setRecyclerViewData(recyclerViewData: List<FirstBanners>) {

        this.recyclerViewData=recyclerViewData
    }*/
    class ViewHolder(val binding: BannersItemBinding):RecyclerView.ViewHolder(binding.root){


        fun binder(banners: FirstBanners){

            binding.title.text=banners.title
              binding.bannerImage.load(banners.img){
                       error(com.google.android.material.R.drawable.ic_clock_black_24dp)
                       placeholder(com.google.android.material.R.drawable.ic_mtrl_checked_circle)
                       crossfade(true)

                   }


        }

    }
}