package com.enigma.si_eo_app.config

<<<<<<< HEAD
import android.util.Base64
import com.enigma.si_eo_app.API.EoApi
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
//    val BASE_URL = "http://10.0.2.2:8586"
    val BASE_URL = "http://10.10.17.122:8586"
    val instance: EoApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        retrofit.create(EoApi::class.java)
    }
//}
//    companion object {
//        val BASE_URL = "http://10.0.2.2:8586"
////        val BASE_URL = "http://10.10.17.122:8586"
//
//        fun createRetrofit(): Retrofit {
//            val retrofit = Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//            return retrofit
//        }
//    }
}
=======
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitBuilder {
    companion object{
        val BASE_URL = "http://10.0.2.2:8585/"
        fun createRetrofit(): Retrofit {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create()).build()
            return retrofit
        }
    }
}
>>>>>>> 44e1eb00ce6d42f681c440398c1d832442c29e0a
