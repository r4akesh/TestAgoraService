package com.example.testmyapplication.flow

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.testmyapplication.R
import com.example.testmyapplication.databinding.HomeflowActivityBinding
import com.example.testmyapplication.flow.network.Status
import com.example.testmyapplication.flow.viewmodel.CommentsViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*


//https://blog.mindorks.com/what-is-flow-in-kotlin-and-how-to-use-it-in-android-project/
//https://www.geeksforgeeks.org/kotlin-flow-in-android-with-example/
class HomeFlowActivity : AppCompatActivity() {
//    lateinit var myFlow: Flow<Int>
//    var TAG= "HomeFlowTAG"
//    lateinit var  button: Button
    val pi : Double by lazy{3.14}
    private lateinit var viewModel: CommentsViewModel
    lateinit var binding : HomeflowActivityBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
       // setContentView(R.layout.homeflow_activity)
        // instantiate view binding
        binding = HomeflowActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // initialize viewModel
        viewModel = ViewModelProvider(this)[CommentsViewModel::class.java]
        binding.button.setOnClickListener {
            // check to prevent api call with no parameters
//            if (binding.searchEditText.text.isNullOrEmpty()) {
//                Toast.makeText(this, "Query Can't be empty", Toast.LENGTH_SHORT).show()
//            } else {
//                // if Query isn't empty, make the api call
//                viewModel.getNewComment(binding.searchEditText.text.toString().toInt())
//            }

            viewModel.getLogin()
            GlobalScope.launch {

            }

//            launch{
//                Log.d("TAG", "onCreate: ")
//            }
        }


        lifecycleScope.launch{
            viewModel.loginState.collect{
                when(it.status){
                    Status.LOADING->{
                        binding.progressBar.isVisible = true
                    }
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false
                        it.data?.let { logindata ->
                            binding.commentIdTextview.text = logindata.message

                        }
                    }
                    Status.ERROR->{
                        binding.progressBar.isVisible = false
                        it.data?.let { errData->
                            Log.d("TAG", "onCreate: "+errData)
                        }
                    }
                    else -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@HomeFlowActivity, "${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }

                }
            }
        }


       /* lifecycleScope.launch {
            viewModel.commentState.collect {
                // When state to check the
                // state of received data
                when (it.status) {
                    // If its loading state then
                    // show the progress bar
                    Status.LOADING -> {
                        binding.progressBar.isVisible = true
                    }
                    // If api call was a success , Update the Ui with
                    // data and make progress bar invisible
                    Status.SUCCESS -> {
                        binding.progressBar.isVisible = false

                        // Received data can be null, put a check to prevent
                        // null pointer exception
                        it.data?.let { comment ->
                            binding.commentIdTextview.text = comment.id.toString()
                            binding.nameTextview.text = comment.name
                            binding.emailTextview.text = comment.email
                            binding.commentTextview.text = comment.body
                        }
                    }
                    // In case of error, show some data to user
                    else -> {
                        binding.progressBar.isVisible = false
                        Toast.makeText(this@HomeFlowActivity, "${it.message}", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        }*/
        Abc.sum()
    }
    object Abc{
        var xyz=10
        fun sum():Int {
            return 2
        }
    }


}