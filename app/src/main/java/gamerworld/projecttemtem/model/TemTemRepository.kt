package gamerworld.projecttemtem.model

import io.reactivex.Single

class TemTemRepository(private val api: TemTemApi) {

    fun getTemTems(): Single<List<TemTem>> {
        return api.getTemTems()
    }

    fun getSingleTemTem(id: String): Single<TemTem> {
        return api.getSingleTemTem(id)
    }

}