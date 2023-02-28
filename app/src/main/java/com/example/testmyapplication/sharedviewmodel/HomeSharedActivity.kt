package com.example.testmyapplication.sharedviewmodel

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentTransaction
import com.example.testmyapplication.R

class HomeSharedActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_shared_actvity)
        val fragmentTransaction: FragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.apply {
            add(R.id.fragment_1_holder, Fragment1())
            add(R.id.fragment_2_holder, Fragment2())
            commit()
        }
    }
}