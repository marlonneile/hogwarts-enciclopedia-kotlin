package com.android.hogwartsenciclopedia.repository

import com.android.hogwartsenciclopedia.database.CharacterEntity
import com.android.hogwartsenciclopedia.database.DatabaseModel
import com.android.hogwartsenciclopedia.domain.CharacterModel
import com.android.hogwartsenciclopedia.domain.DomainModel
import com.android.hogwartsenciclopedia.domain.FullCharacterModel
import com.android.hogwartsenciclopedia.network.CharacterNetworkModel
import com.android.hogwartsenciclopedia.network.NetworkModel
import com.android.hogwartsenciclopedia.utils.toInt

fun networkAsDatabaseModel(response: CharacterNetworkModel): DatabaseModel {
    val uri = response.image.replace("http", "https")
    return CharacterEntity(
        name = response.name,
        species = response.species,
        gender = response.gender,
        house = response.house,
        dateOfBirth = response.dateOfBirth,
        yearOfBirth = response.yearOfBirth.toInt() ,
        ancestry = response.ancestry,
        eyeColour = response.eyeColour,
        hairColour = response.hairColour,
        patronus = response.patronus,
        hogwartsStudent = response.hogwartsStudent,
        hogwartsStaff = response.hogwartsStaff,
        actor = response.actor,
        alive = response.alive,
        url = uri
    )
}

fun Collection<NetworkModel>.asDatabaseModel(): List<DatabaseModel> {
    return map {
        it.asDatabaseModel()
    }
}

fun entityAsDomainModel(entity: CharacterEntity, type: DataTransferObjects): DomainModel {
    when (type) {
        DataTransferObjects.SIMPLE ->  return CharacterModel(entity.name, entity.url)
        DataTransferObjects.FULL -> return FullCharacterModel(
            name = entity.name,
            species = entity.species,
            gender = entity.gender,
            house = entity.house,
            dateOfBirth = entity.dateOfBirth,
            yearOfBirth = entity.yearOfBirth,
            ancestry = entity.ancestry,
            eyeColour = entity.eyeColour,
            hairColour = entity.hairColour,
            patronus = entity.patronus,
            hogwartsStudent = entity.hogwartsStudent,
            hogwartsStaff = entity.hogwartsStaff,
            actor = entity.actor,
            alive = entity.alive,
            url = entity.url
        )
    }

}

fun Collection<DatabaseModel>.asDomainModel(): List<DomainModel> {
    return map {
        it.asDomainModel(DataTransferObjects.SIMPLE)
    }
}

enum class DataTransferObjects {
    SIMPLE, FULL
}