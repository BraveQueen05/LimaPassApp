package flavia.figueroa.repository.network.di

import android.content.Context
import flavia.figueroa.repository.R
import flavia.figueroa.repository.local.preferences.manager.PreferencesManager
import flavia.figueroa.repository.local.preferences.utils.PREFERENCE_TOKEN
import flavia.figueroa.repository.network.AppApi
import flavia.figueroa.repository.network.exception.NetworkException
import flavia.figueroa.repository.network.utils.*
import okhttp3.Cache
import okhttp3.Interceptor
import flavia.figueroa.repository.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Response
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val retrofitModule = module {
    single { providerHttpLoggingInterceptor() }
    single { providerCache(get()) }
    single { ApiInterceptor(get(), get()) }
    single { providerOkHttpClient(get(), get()) }
    single { providerRetrofit(getProperty(BASE_URL), get()) }
    single { providerApi(get()) }
}


fun providerApi(retrofit: Retrofit): AppApi {
    return retrofit.create(AppApi::class.java)
}

fun providerRetrofit(baseUrl: String, client: OkHttpClient): Retrofit {
    return Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .baseUrl(baseUrl)
        .build()
}

fun providerOkHttpClient(
    httpLoggingInterceptor: HttpLoggingInterceptor,
    apiInterceptor: ApiInterceptor
): OkHttpClient {
    return OkHttpClient.Builder()
        .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
        .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
        .readTimeout(TIMEOUT, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .addInterceptor(apiInterceptor)
        .build()
}

fun providerCache(context: Context): Cache {
    val cacheSize: Long = 10485760
    return Cache(context.cacheDir, cacheSize)
}

fun providerHttpLoggingInterceptor(): HttpLoggingInterceptor {
    val logging = HttpLoggingInterceptor()
    logging.level =
        if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    return logging
}

class ApiInterceptor(
    private val context: Context,
    private val sharedPreferences: PreferencesManager
) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        if (!isConnected(context)) {
            throw NetworkException(context.getString(R.string.network_error))
        }
        var request = chain.request()
        println("token: ${sharedPreferences.getString(PREFERENCE_TOKEN)}")
        request = request.newBuilder()
            .header("Authorization", AUTHORIZATION + sharedPreferences.getString(PREFERENCE_TOKEN))
            .header("x-os", PLATFORM)
            .build()
        return chain.proceed(request)
    }
}