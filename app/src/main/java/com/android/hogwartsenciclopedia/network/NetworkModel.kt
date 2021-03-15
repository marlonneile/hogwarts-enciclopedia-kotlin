package com.android.hogwartsenciclopedia.network

import com.android.hogwartsenciclopedia.database.DatabaseModel

interface NetworkModel {
    fun asDatabaseModel(): DatabaseModel
}