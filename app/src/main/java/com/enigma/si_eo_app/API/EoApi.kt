package com.enigma.si_eo_app.API

import com.enigma.si_eo_app.model.Eo
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part


interface EoApi {
    @Multipart
    @POST("/eo")
    fun saveEo(
        @Part("UserID") UserID:Int,
        @Part("EoName") EoName: String,
        @Part("Identity") Identity: String,
        @Part IdentityImg: MultipartBody.Part?,
        @Part("License") License: String,
        @Part LicenseImg: MultipartBody.Part?,
        @Part("Address") Address: String,
        @Part("Phone") Phone: String,
        @Part("Website") Website: String,
        @Part("Instagram") Instagram: String,
        @Part("Facebook") Facebook: String,
        @Part("Twitter") Twitter: String
    ): Call<Eo>?
}