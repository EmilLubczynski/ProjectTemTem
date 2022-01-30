package gamerworld.projecttemtem.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gamerworld.projecttemtem.model.TemTem
import gamerworld.projecttemtem.model.TemTemRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class ListViewModel(private val repository: TemTemRepository) : ViewModel() {

    private val disposable = CompositeDisposable() //clears connections

    val temtems = MutableLiveData<List<TemTem>>()
    val temtemLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

    fun refresh() {
        fetchTemTems()
    }

    private fun fetchTemTems() {
        loading.value = true
        disposable.add(
            repository.getTemTems()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<List<TemTem>>() {
                    override fun onSuccess(value: List<TemTem>?) {
                        temtems.value = value
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