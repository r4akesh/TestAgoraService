package com.example.testmyapplication.extratask

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.testmyapplication.BioMattricActivity
import com.example.testmyapplication.R
import kotlin.concurrent.thread

class HomeExtraActivity : AppCompatActivity() {
    lateinit var userInfo : UserInfo
    var TAG = "HomeExtraActivity11111111"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home_extra)

        var list = ArrayList<UserInfo>();
        list.add(UserInfo("rakesh1"))
        list.add(UserInfo("rakesh2"))
        list.add(UserInfo("rakesh3"))
        OPERTION()

   //     Log.d(TAG, "onCreate: "+list.toString())
//        var bndl = Bundle()
//        bndl.putParcelableArrayList("USER_KEY",list)
    //  startActivity(Intent(this@HomeExtraActivity,BioMattricActivity::class.java)
       //     .putExtra("DATASEND",list))

    }
    private fun OPERTION() {
        // inner thread
        // lazy

        thread {

        }


    }
}