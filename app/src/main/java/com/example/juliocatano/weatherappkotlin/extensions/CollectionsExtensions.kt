package com.example.juliocatano.weatherappkotlin.extensions

fun <K, V : Any> MutableMap<K, V?>.toVarArgArray(): Array<out Pair<K, V>> = map({ Pair(it.key, it.value!!) }).toTypedArray()