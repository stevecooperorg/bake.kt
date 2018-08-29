package org.stevecooper.util

import java.io.InputStream
import java.io.InputStreamReader

fun InputStream.readToString(): String {
    val bufferSize = 1024
    val buffer = CharArray(bufferSize)
    val out = StringBuilder()
    val `in` = InputStreamReader(this, "UTF-8")
    while (true) {
        val rsz = `in`.read(buffer, 0, buffer.size)
        if (rsz < 0)
            break
        out.append(buffer, 0, rsz)
    }
    return out.toString()
}