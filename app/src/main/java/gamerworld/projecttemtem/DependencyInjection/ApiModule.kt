package gamerworld.projecttemtem.DependencyInjection


import dagger.Module
import dagger.Provides
import gamerworld.projecttemtem.model.TemTemApi
import gamerworld.projecttemtem.model.TemTemService
import gamerworld.projecttemtem.util.BASE_URL
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


@Module
class ApiModule {
    @Provides
    fun provideTemTemApi(): TemTemApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(TemTemApi::class.java)
    }

    @Provides
    fun provideTemTemService(): TemTemService {
        return TemTemService()
    }

}