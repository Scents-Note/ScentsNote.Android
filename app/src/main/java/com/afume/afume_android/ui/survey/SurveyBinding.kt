package com.afume.afume_android.ui.survey

import android.util.Log
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.afume.afume_android.data.vo.response.PerfumeInfo
import com.afume.afume_android.data.vo.response.ResponseKeyword
import com.afume.afume_android.data.vo.response.SeriesInfo
import com.afume.afume_android.ui.filter.FlexboxRecyclerViewAdapter

object SurveyBinding {
    @JvmStatic
    @BindingAdapter("setPerfumeList")
    fun setPerfumeList(recyclerView: RecyclerView, list : MutableList<PerfumeInfo>?){
        if(recyclerView.adapter!=null) with(recyclerView.adapter as CircleRecyclerViewAdapter){
            list?.let { setPerfumeData(list)
            Log.e("setperfumeList",perfumeData.toString())}
        }
    }
    @JvmStatic
    @BindingAdapter("setSeriesList")
    fun setSeriesList(recyclerView: RecyclerView, list : MutableList<SeriesInfo>?){
        if(recyclerView.adapter!=null) with(recyclerView.adapter as CircleRecyclerViewAdapter){
            list?.let { setSeriesData(list)
                Log.e("setseriesList",perfumeData.toString())}
        }
    }

    @JvmStatic
    @BindingAdapter("setKeywordList")
    fun setKeywordList(recyclerView: RecyclerView, list : MutableList<ResponseKeyword>?){
        if(recyclerView.adapter!=null) with(recyclerView.adapter as FlexboxRecyclerViewAdapter){
            list?.let {
                setData(it)
//                submitList(list)
                Log.e("setkeywordList",data.toString())}
        }
    }

}