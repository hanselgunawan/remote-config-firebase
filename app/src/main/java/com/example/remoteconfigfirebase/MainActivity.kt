package com.example.remoteconfigfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.remoteconfigfirebase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        binding.helloWorldText.apply {
            text = RemoteConfigUtils.getHelloWorldText()
        }

    }
}