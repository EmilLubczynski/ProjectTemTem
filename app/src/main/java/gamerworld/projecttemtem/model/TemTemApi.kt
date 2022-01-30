package gamerworld.projecttemtem.model

import io.reactivex.Single
import retrofit2.http.*

interface TemTemApi {
    @GET("api/temtems")
    fun getTemTems(): Single<List<TemTem>>

    @GET("api/temtems/{id}")
    fun getSingleTemTem(
        @Path("id") id: String): Single<TemTem>
}