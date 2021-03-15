package com.android.hogwartsenciclopedia.network

import com.android.hogwartsenciclopedia.database.DatabaseModel
import com.android.hogwartsenciclopedia.repository.networkAsDatabaseModel
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CharacterNetworkModel(
    val name: String,
    val species: String,
    val gender: String,
    val house: String?,
    val dateOfBirth: String?,
    val yearOfBirth: Any?,
    val ancestry: String?,
    val eyeColour: String?,
    val hairColour: String,
    val wand: Wand?,
    val patronus: String?,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,
    val alive: Boolean,
    val image: String
) : NetworkModel {
    override fun asDatabaseModel(): DatabaseModel {
        return networkAsDatabaseModel(this)
    }
}

@JsonClass(generateAdapter = true)
data class Wand(
    val wood: String?,
    val core: String?,
    val length: Any?
)