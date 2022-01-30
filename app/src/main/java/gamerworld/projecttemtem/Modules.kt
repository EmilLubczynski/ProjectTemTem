package gamerworld.projecttemtem

import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import gamerworld.projecttemtem.model.TemTemApi
import gamerworld.projecttemtem.model.TemTemRepository
import gamerworld.projecttemtem.util.BASE_URL
import gamerworld.projecttemtem.viewmodel.ListViewModel
import gamerworld.projecttemtem.viewmodel.TemTemDataViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {
    //this is where I declare my view models
    viewModel { ListViewModel(get()) }
    viewModel { TemTemDataViewModel(get()) }
    single { TemTemRepository(get()) }
}

val apiModule = module {
    fun provideUseApi(retrofit: Retrofit): TemTemApi {
        return retrofit.create(TemTemApi::class.java)
    }
    single { provideUseApi(get()) }
}

val retrofitModule = module {
    fun provideGson(): Gson {
        return GsonBuilder().setFieldNamingPolicy(FieldNamingPolicy.IDENTITY).create()
    }

    fun provideHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        return okHttpClientBuilder.build()
    }

    fun provideRetrofit(factory: Gson, client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(factory))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .build()
    }

    single { provideGson() }
    single { provideHttpClient() }
    single { provideRetrofit(get(), get()) }
}
