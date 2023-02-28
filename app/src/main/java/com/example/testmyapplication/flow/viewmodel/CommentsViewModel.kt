package com.example.testmyapplication.flow.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testmyapplication.flow.model.LoginModel
import com.example.testmyapplication.flow.model.CommentModel
import com.example.testmyapplication.flow.network.CommentApiState
import com.example.testmyapplication.flow.network.Status
import com.example.testmyapplication.flow.repositry.CommentsRepository
import com.example.testmyapplication.flow.utills.AppConfig
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CommentsViewModel : ViewModel() {

    // Create a Repository and pass the api
    // service we created in AppConfig file
    private val repository = CommentsRepository(
        AppConfig.ApiService()
    )

//    val commentState = MutableStateFlow(
//        CommentApiState(
//            Status.LOADING,
//            CommentModel(), ""
//        )
//    )
//

    val loginState = MutableStateFlow(
        CommentApiState(
            Status.ERROR,
            LoginModel(), ""
        )
    )

    init {
        // Initiate a starting
        // search with comment Id 1
     //   getNewComment(1)
    }


    // Function to get new Comments
   /* fun getNewComment(id: Int) {

        // Since Network Calls takes time,Set the
        // initial value to loading state
        commentState.value = CommentApiState.loading()

        // ApiCalls takes some time, So it has to be
        // run and background thread. Using viewModelScope
        // to call the api
        viewModelScope.launch {

            // Collecting the data emitted
            // by the function in repository
            repository.getComment(id)
                // If any errors occurs like 404 not found
                // or invalid query, set the state to error
                // State to show some info
                // on screen
                .catch {
                    commentState.value = CommentApiState.error(it.message.toString())
                }
                // If Api call is succeeded, set the State to Success
                // and set the response data to data received from api
                .collect {
                    commentState.value = CommentApiState.success(it.data)
                }
        }
    }*/

    fun getLogin() {
        loginState.value = CommentApiState.loading()
        viewModelScope.launch {
            repository.getLogin()
                .catch {
                    loginState.value = CommentApiState.error(it.message.toString())
                }
                .collect {
                    loginState.value = CommentApiState.success(it.data)
                }
        }
    }
}