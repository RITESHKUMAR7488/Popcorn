package com.example.imdb



import android.app.Application
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
// for initialization of retrofit

class MyApplication : Application() {
    lateinit var retrofit: Retrofit

    override fun onCreate() {
        super.onCreate()

        val headerInterceptor = Interceptor { chain ->
            val originalRequest: Request = chain.request()
            val newRequest: Request = originalRequest.newBuilder()
                .header("x-rapidapi-key", "91b8c61787mshbd2d9597c9a9ba7p1ee43bjsndf6c9871521d")
                .build()
            chain.proceed(newRequest)
        }

        val client = OkHttpClient.Builder()
            .addInterceptor(headerInterceptor)
            .build()

        retrofit = Retrofit.Builder()
            .baseUrl("https://movies-api14.p.rapidapi.com/")
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
