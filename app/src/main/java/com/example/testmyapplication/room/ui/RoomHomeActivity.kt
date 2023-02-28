package com.example.testmyapplication.room.ui

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testmyapplication.R
import com.example.testmyapplication.room.GroceryItems
import com.example.testmyapplication.room.adapter.GroceryAdapter
import com.example.testmyapplication.room.db.GroceryDatabase
import kotlinx.android.synthetic.main.roomhomeactivity.*


class RoomHomeActivity  : AppCompatActivity(){
    lateinit var ViewModel: GroceryViewModel
    lateinit var list: List<GroceryItems>

    lateinit var st :String
    override fun onCreate(savedInstanceState: Bundle? ) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.roomhomeactivity)
        val groceryRepository = GroceryRepository(GroceryDatabase(this))
        val factory = GroceryViewModelFactory(groceryRepository)

        // Initialised View Model
        ViewModel = ViewModelProvider(this, factory).get(GroceryViewModel::class.java)
        val groceryAdapter = GroceryAdapter(listOf(), ViewModel)
        rvList.layoutManager = LinearLayoutManager(this)
        rvList.adapter = groceryAdapter

        // To display all items in recycler view
        ViewModel.allGroceryItems().observe(this, Observer {
            groceryAdapter.list = it
            groceryAdapter.notifyDataSetChanged()
            Log.d("TAG", "notifyDataSetChanged call: ")
        })

        // on ClickListener on button to open dialog box
        btnAdd.setOnClickListener {
//            GroceryItemDialog(this, object : DialogListener {
//                override fun onAddButtonClicked(item: GroceryItems) {
//                    ViewModel.insert(item)
//                }
//            }).show()

            //kill background  other app and also give permission menifest
            /*val am = getSystemService(ACTIVITY_SERVICE) as ActivityManager
            am.killBackgroundProcesses("com.emizen.stattrack.stattrack")*/
        }


    }

}