package emil.lubczynski.projecttemtem.model

import emil.lubczynski.projecttemtem.DependencyInjection.DaggerApiComponent
import javax.inject.Inject
import io.reactivex.Single
class TemTemService {
    @Inject
    lateinit var api: TemTemApi
    init {
      DaggerApiComponent.create().inject(this)
    }

    fun getTemTems(): Single<List<TemTem>>
    {
        return api.getTemTems()
    }
}