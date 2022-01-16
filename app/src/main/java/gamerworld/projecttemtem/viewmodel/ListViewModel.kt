package gamerworld.projecttemtem.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gamerworld.projecttemtem.DependencyInjection.DaggerApiComponent
import gamerworld.projecttemtem.model.TemTem
import gamerworld.projecttemtem.model.TemTemService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class ListViewModel : ViewModel() {

    @Inject
    lateinit var temtemService: TemTemService

    init {
        DaggerApiComponent.create().inject(this)
    }

    private val disposable = CompositeDisposable() //clears connections

    val temtem = MutableLiveData<List<TemTem>>()
    val temtemLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchTemTems()
    }

    private fun fetchTemTems() {
        loading.value = true
        disposable.add(
            temtemService.getTemTems()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<TemTem>>() {
                    override fun onSuccess(value: List<TemTem>?) {
                        temtem.value = value
                        temtemLoadError.value = false
                        loading.value = false
                    }

                    override fun onError(e: Throwable?) {
                        temtemLoadError.value = true
                        loading.value = false
                    }

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}