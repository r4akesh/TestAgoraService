package com.example.testmyapplication.flow.network

import com.example.testmyapplication.flow.model.LoginModel
import com.example.testmyapplication.flow.model.CommentModel
import retrofit2.http.*

interface ApiService {
    // Get method to call the api ,passing id as a path
    @GET("comments/{id}")
    suspend fun getComments(@Path("id") id: Int): CommentModel

    @FormUrlEncoded
    @POST("user-login")
    suspend fun getLogin(
        @Field("phone_number") phno : String,
        @Field("std_code") std_code : String,
        @Field("country_code") country_code : String,
        @Field("country") country : String,

        ): LoginModel
}