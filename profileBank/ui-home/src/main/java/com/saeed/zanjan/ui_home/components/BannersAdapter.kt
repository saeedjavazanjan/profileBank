package com.saeed.zanjan.ui_home.components

import android.os.Binder
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.saeed.zanjan.domain.FirstBanners
import com.saeed.zanjan.ui_home.R
import com.saeed.zanjan.ui_home.databinding.BannersItemBinding

class BannersAdapter:RecyclerView.Adapter<BannersAdapter.ViewHolder>() {
   private lateinit var recyclerViewData:List<FirstBanners>

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val viewBinder=BannersItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(viewBinder)
    }

    fun setRecyclerData(recyclerViewData:List<FirstBanners>){
        this.recyclerViewData=recyclerViewData
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.binder(recyclerViewData[position])
    }

    override fun getItemCount(): Int {
        return recyclerViewData.size
    }
    class ViewHolder(val  viewBinder:BannersItemBinding):RecyclerView.ViewHolder(viewBinder.root){

        fun binder(banner:FirstBanners){

            viewBinder.bannerImage .load(banner.img) {
                crossfade(true)
                placeholder(com.google.android.material.R.drawable.ic_mtrl_checked_circle)
                transformations(CircleCropTransformation())
            }
            viewBinder.title.text=banner.title


        }

    }
}