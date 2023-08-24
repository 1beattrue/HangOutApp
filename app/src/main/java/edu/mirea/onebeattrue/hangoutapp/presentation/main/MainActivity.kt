package edu.mirea.onebeattrue.hangoutapp.presentation.main

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.mirea.onebeattrue.hangoutapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
    }
}