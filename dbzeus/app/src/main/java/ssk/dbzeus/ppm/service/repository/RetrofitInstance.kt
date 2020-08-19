package ssk.dbzeus.ppm.service.repository

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class RetrofitInstance {
    companion object {
        //private const val BASE_URL_VMS: String = "http://vmsapi.duceapps.com:1213/api/"
        private const val BASE_URL_VMS: String = "http://apitest.dbzapps.com/api/"
        //private const val BASE_URL: String = "https://apii.dbzapps.com/api/"
        private const val BASE_URL: String = "http://apitest.dbzapps.com/api/"

        private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            this.level = HttpLoggingInterceptor.Level.BODY
        }

        private val client: OkHttpClient = OkHttpClient.Builder().apply {
            this.addInterceptor(interceptor)
        }.build()
        fun getRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
        fun getRetrofitInstance_VMS(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL_VMS)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }
    }
}