package com.android.hogwartsenciclopedia.database

import com.android.hogwartsenciclopedia.domain.DomainModel
import com.android.hogwartsenciclopedia.repository.DataTransferObjects

interface DatabaseModel {
    fun asDomainModel(type: DataTransferObjects): DomainModel
}