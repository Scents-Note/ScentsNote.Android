package com.afume.afume_android.ui.my

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afume.afume_android.AfumeApplication
import com.afume.afume_android.data.repository.MyRepository
import com.afume.afume_android.data.vo.response.PerfumeInfo
import com.afume.afume_android.data.vo.response.ResponseMyPerfume
import kotlinx.coroutines.launch
import retrofit2.HttpException

class MyViewModel : ViewModel() {
    private val myRepository = MyRepository()

    private val _wishList: MutableLiveData<MutableList<PerfumeInfo>> = MutableLiveData()
    val wishList: LiveData<MutableList<PerfumeInfo>> get() = _wishList

    private val _myPerfumeList: MutableLiveData<MutableList<ResponseMyPerfume>> = MutableLiveData()
    val myPerfumeList: LiveData<MutableList<ResponseMyPerfume>> get() = _myPerfumeList

    init {
        viewModelScope.launch {
            getMyPerfume()
            getLikedPerfume()
        }
    }

    suspend fun getLikedPerfume() {
        try {
            _wishList.value = myRepository.getLikedPerfume(
                AfumeApplication.prefManager.accessToken, AfumeApplication.prefManager.userIdx
            )
            Log.d("명",AfumeApplication.prefManager.accessToken+" "+AfumeApplication.prefManager.userIdx.toString()+" "+_wishList.value.toString())

        } catch (e: HttpException) {
//                when(e.response()?.code()){}
        }
    }

    suspend  fun getMyPerfume() {
        try {
            _myPerfumeList.value = myRepository.getMyPerfume(
                AfumeApplication.prefManager.accessToken
            )

        } catch (e: HttpException) {
//                when(e.response()?.code()){}
        }
    }
}
