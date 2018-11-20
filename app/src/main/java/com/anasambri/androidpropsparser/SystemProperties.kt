package com.anasambri.androidpropsparser

import java.io.BufferedReader
import java.io.InputStreamReader

object SystemProperties {

    private const val GETPROP_EXECUTABLE_PATH = "/system/bin/getprop"

    fun read(): String {
        var process: Process? = null
        var reader: BufferedReader? = null
        try {
            process = ProcessBuilder()
                .command(GETPROP_EXECUTABLE_PATH)
                .redirectErrorStream(true)
                .start()
            reader = BufferedReader(InputStreamReader(process!!.inputStream))
            return reader.readText()
        } finally {
            process?.destroy()
            reader?.close()
        }
    }
}