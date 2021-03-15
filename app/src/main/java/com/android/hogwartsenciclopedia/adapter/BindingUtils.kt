package com.android.hogwartsenciclopedia.adapter

import android.net.Uri
import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.android.hogwartsenciclopedia.domain.CharacterModel
import com.android.hogwartsenciclopedia.domain.FullCharacterModel
import com.bumptech.glide.Glide
import java.util.*

@BindingAdapter("characterName")
fun TextView.setCharacterName(item: CharacterModel?) {
    item?.let {
        text = it.name
    }
}

@BindingAdapter("house")
fun TextView.setHouse(value: String?) {
    text = if (value == "") {
        "Casa desconhecida"
    } else {
        value
    }
}

@BindingAdapter("patronus")
fun TextView.setPatronus(value: String?) {
    text = if (value == "") {
        "Patrono desconhecido"
    } else {
        value?.capitalize(Locale.ROOT)
    }
}

@BindingAdapter("characterImage")
fun ImageView.setCharacterImage(item: CharacterModel?) {
    item?.let {
        Glide
            .with(this.context)
            .load(Uri.parse(it.url))
            .centerCrop()
            .into(this)
    }
}

@BindingAdapter("characterImage")
fun ImageView.setCharacterImage(item: FullCharacterModel?) {
    item?.let {
        Glide
            .with(this.context)
            .load(Uri.parse(it.url))
            .centerCrop()
            .into(this)
    }
}