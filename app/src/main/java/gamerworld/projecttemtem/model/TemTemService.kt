package gamerworld.projecttemtem.model

import gamerworld.projecttemtem.DependencyInjection.DaggerApiComponent
import io.reactivex.Single
import javax.inject.Inject

class TemTemService {
    @Inject
    lateinit var api: TemTemApi

    init {
        DaggerApiComponent.create().inject(this)
    }

    fun getTemTems(): Single<List<TemTem>> {
        return api.getTemTems()
    }

}