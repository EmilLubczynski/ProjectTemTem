package gamerworld.projecttemtem.model

import io.reactivex.Single
import retrofit2.http.GET

interface TemTemApi {
    @GET("api/temtems")
    fun getTemTems(): Single<List<TemTem>>

}