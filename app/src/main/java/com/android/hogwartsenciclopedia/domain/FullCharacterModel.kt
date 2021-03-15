package com.android.hogwartsenciclopedia.domain

data class FullCharacterModel(
    val name: String,
    val species: String,
    val gender: String,
    var house: String?,
    val dateOfBirth: String?,
    val yearOfBirth: Any?,
    val ancestry: String?,
    val eyeColour: String?,
    val hairColour: String,
    val patronus: String?,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String,
    val alive: Boolean,
    val url: String
) : DomainModel