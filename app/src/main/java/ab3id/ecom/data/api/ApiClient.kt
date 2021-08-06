package ab3id.ecom.data.api

import ab3id.ecom.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


class ApiClient {
    lateinit var retrofit:Retrofit

    private fun okHttpClient(): OkHttpClient.Builder? {
        val okHttpClient = OkHttpClient.Builder()
        okHttpClient.connectTimeout(3, TimeUnit.MINUTES)
        okHttpClient.readTimeout(3, TimeUnit.MINUTES)
        okHttpClient.writeTimeout(3, TimeUnit.MINUTES)
        if (BuildConfig.DEBUG) {
            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY
            okHttpClient.addInterceptor(logging)
        }
        return okHttpClient
    }


    fun getClient(): Retrofit? {
        val gson = GsonBuilder()
            .setLenient()
            .create()
        val builder = Retrofit.Builder()
        builder.baseUrl("http://code255technologies.com/ecommerce/")
        builder.addConverterFactory(GsonConverterFactory.create(gson))
        val okhttpBuilder = okHttpClient()
        builder.client(okhttpBuilder!!.build())
        retrofit = builder.build()
        return retrofit
    }

}