package com.example.staffsphere.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader

fun readJsonFromAssets(fileName: String,context:Context): String {
    val inputStream: InputStream = context.assets.open(fileName)
    val bufferedReader = BufferedReader(InputStreamReader(inputStream))
    val stringBuilder = StringBuilder()
    var line: String?
    while (bufferedReader.readLine().also { line = it } != null) {
        stringBuilder.append(line)
    }
    bufferedReader.close()
    return stringBuilder.toString()
}
