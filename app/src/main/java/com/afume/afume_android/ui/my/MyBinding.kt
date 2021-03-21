package com.afume.afume_android.ui.my

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afume.afume_android.data.vo.response.PerfumeInfo
import com.afume.afume_android.data.vo.response.ResponseMyPerfume
import com.afume.afume_android.ui.my.myperfume.MyPerfumeRecyclerViewAdapter
import com.afume.afume_android.ui.my.wishlist.WishListAdapter

object MyBinding {
    @JvmStatic
    @BindingAdapter("setWishList")
    fun setPerfumeList(recyclerView: RecyclerView, list : MutableList<PerfumeInfo>?){
        if(recyclerView.adapter!=null) with(recyclerView.adapter as WishListAdapter){
            list?.let { setWishListData(list)
                Log.e("setWishListData",data.toString())}
        }
    }
    @JvmStatic
    @BindingAdapter("setMyPerfumeList")
    fun setMyPerfumeList(recyclerView : RecyclerView, list: MutableList<ResponseMyPerfume>?){
        if(recyclerView.adapter!=null) with(recyclerView.adapter as MyPerfumeRecyclerViewAdapter){
            list.let{
                setMyPerfumeListData(list)
                Log.e("setWishListData",data.toString())
            }
        }
    }
}