package com.android.hogwartsenciclopedia.database

import androidx.lifecycle.LiveData

interface LocalDataSource<T: DatabaseModel> {
    suspend fun insertAll(items: Collection<T>)
    fun getAll(): LiveData<out Collection<T>>
    suspend fun getById(id: Any): T
}