package com.example.andersen_hw5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.andersen_hw5.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.flFragment,
            ContactListFragment.newInstance()).commit()
    }

    override fun onBackPressed() {
        supportFragmentManager.beginTransaction().replace(R.id.flFragment,
            ContactListFragment.newInstance()).commit()
    }

}