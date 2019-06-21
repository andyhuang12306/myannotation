package com.example.java_app

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.apt_annotation.BindView
import com.example.apt_lib.BindViewTools

class MainActivity : AppCompatActivity() {

    @BindView(R.id.tv)
    lateinit var tv: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        BindViewTools.bind(this)
        tv.text="yeah"
    }
}
