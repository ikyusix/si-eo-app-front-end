package com.enigma.si_eo_app.service

import com.enigma.si_eo_app.model.*
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface ServiceAPI {

    @POST("user/login")
    fun signInPost(@Body userLogin: SignIn): Call<ResponseSignIn>

    @POST("user/signup")
    fun signUpPost(@Body userSignUp: SignUp): Call<ResponseSignUP>

    @GET("user/{id}")
    fun getUserById(
        @Path("id") id:Int): Call<User>

    @Multipart
    @PUT("upload_image/{id}")
    fun uploadData(
        @Path("id") id:Int,
        @Part image: MultipartBody.Part):
            Call<ResponseBody>

}