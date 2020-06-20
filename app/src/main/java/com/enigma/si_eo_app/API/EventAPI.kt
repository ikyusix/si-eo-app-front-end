package com.enigma.si_eo_app.API

import com.enigma.si_eo_app.model.EventInput
import okhttp3.MultipartBody
import retrofit2.Call
import retrofit2.http.*

interface EventAPI {
    @Multipart
    @POST("/event")
    fun SaveEvent(
        @Part("EoID") EoId: Int,
        @Part("Name")Name :String,
        @Part("Date")Date :String,
        @Part("Location")Location: String,
        @Part("Prince")Prince: String,
        @Part("Capacity")Capacity: String,
        @Part("Description")Description: String,
        @Part Image : MultipartBody.Part?
    ): Call<EventInput>
}
