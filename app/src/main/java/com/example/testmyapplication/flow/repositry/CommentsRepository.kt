package com.example.testmyapplication.flow.repositry

import com.example.testmyapplication.flow.model.LoginModel
import com.example.testmyapplication.flow.model.CommentModel
import com.example.testmyapplication.flow.network.ApiService
import com.example.testmyapplication.flow.network.CommentApiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class CommentsRepository(private val apiService: ApiService) {
    suspend fun getComment(id: Int): Flow<CommentApiState<CommentModel>> {
        return flow {
             
            // get the comment Data from the api
            val comment=apiService.getComments(id)
             
            // Emit this data wrapped in
            // the helper class [CommentApiState]
            emit(CommentApiState.success(comment))
        }.flowOn(Dispatchers.IO)
    }

    suspend fun getLogin(): Flow<CommentApiState<LoginModel>> {
        return flow {
            val comment=apiService.getLogin("7845412135","+91","IN","India")
            emit(CommentApiState.success(comment))
        }.flowOn(Dispatchers.IO)
    }
}