package com.android.hogwartsenciclopedia.utils

fun Any?.toInt(): Int? {
    return if (this is Int) this else null
}