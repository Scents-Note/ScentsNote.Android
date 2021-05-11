package com.afume.afume_android.data.repository

import com.afume.afume_android.data.remote.RemoteDataSource
import com.afume.afume_android.data.remote.RemoteDataSourceImpl

class SearchRepository {
    val remoteDataSource : RemoteDataSource = RemoteDataSourceImpl()

    suspend fun getKeyword()=remoteDataSource.getKeyword()
}