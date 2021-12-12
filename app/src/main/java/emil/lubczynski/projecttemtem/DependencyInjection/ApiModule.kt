package emil.lubczynski.projecttemtem.DependencyInjection

import dagger.Module
import dagger.Provides
import emil.lubczynski.projecttemtem.model.TemTemApi
import emil.lubczynski.projecttemtem.model.TemTemService
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

@Module
class ApiModule {
    private val BASE_URL = "https://temtem-api.mael.tech/"

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