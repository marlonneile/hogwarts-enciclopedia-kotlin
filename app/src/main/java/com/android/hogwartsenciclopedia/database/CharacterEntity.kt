package com.android.hogwartsenciclopedia.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.android.hogwartsenciclopedia.domain.DomainModel
import com.android.hogwartsenciclopedia.repository.DataTransferObjects
import com.android.hogwartsenciclopedia.repository.entityAsDomainModel

@Entity
data class CharacterEntity(
    @PrimaryKey val name: String,
    @ColumnInfo(defaultValue = "") val species: String,
    @ColumnInfo(defaultValue = "") val gender: String,
    val house: String?,
    @ColumnInfo(name = "date_of_birth") val dateOfBirth: String?,
    @ColumnInfo(name = "year_of_birth") val yearOfBirth: Int?,
    val ancestry: String?,
    @ColumnInfo(name = "eye_color") val eyeColour: String?,
    @ColumnInfo(name = "hair_color", defaultValue = "") val hairColour: String,
    val patronus: String?,
    @ColumnInfo(name = "hogwarts_student", defaultValue = "0") val hogwartsStudent: Boolean,
    @ColumnInfo(name = "hogwarts_staff", defaultValue = "0") val hogwartsStaff: Boolean,
    @ColumnInfo(defaultValue = "") val actor: String,
    @ColumnInfo(defaultValue = "0") val alive: Boolean,
    val url: String
) : DatabaseModel {
    override fun asDomainModel(type: DataTransferObjects): DomainModel {
        return entityAsDomainModel(this, type)
    }
}
