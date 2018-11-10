package com.anasambri.androidpropsparser

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.anasambri.lib.SystemPropsParser
import com.fasterxml.jackson.databind.ObjectMapper

class MainActivity : AppCompatActivity() {

    private lateinit var textView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textView = findViewById(R.id.textView)
        val input = SystemProperties.read()
        val parsed = SystemPropsParser.parse(input)
        textView.text = ObjectMapper().writeValueAsString(parsed)
    }
}
