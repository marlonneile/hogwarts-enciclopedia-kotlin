package com.android.hogwartsenciclopedia.network

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.Moshi

interface CharactersResponse : List<CharacterNetworkModel>

class ParserResponseAsListAdapter(moshi: Moshi) {
    private val characterJsonAdapter = CharacterNetworkModelJsonAdapter(moshi)

    @FromJson
    fun parse(reader: JsonReader): CharactersResponse {
        return reader.readArrayToList {
            characterJsonAdapter.fromJson(reader)
        } as CharactersResponse
    }
}

inline fun <T : Any> JsonReader.readArrayToList(body: () -> T?): List<T> {
    val result = mutableListOf<T>()
    beginArray()
    while (hasNext()) {
        body()?.let {
            result.add(it)
        }
    }
    endArray()
    return result
}