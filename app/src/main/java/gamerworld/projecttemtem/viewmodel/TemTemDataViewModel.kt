package gamerworld.projecttemtem.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import gamerworld.projecttemtem.model.TemTem
import gamerworld.projecttemtem.model.TemTemRepository
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class TemTemDataViewModel(private val repository: TemTemRepository) : ViewModel() {

    private val disposable = CompositeDisposable() //clears connections
    val temtemLiveData = MutableLiveData<TemTem>()
    var temtem : TemTem? = null
    val temtemLoadError = MutableLiveData<Boolean>()
    val loading = MutableLiveData<Boolean>()

     fun fetchSingleTemTem(){
        temtem?.number?.let { number ->
            loading.value = true
            disposable.add(
                repository.getSingleTemTem(number)
                    .subscribeOn(Schedulers.newThread())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeWith(object : DisposableSingleObserver<TemTem>() {
                        override fun onSuccess(value: TemTem?) {
                            temtemLiveData.value = value
                            temtemLoadError.value = false
                            loading.value = false
                        }

                        override fun onError(e: Throwable?) {
                            temtemLoadError.value = true
                            loading.value = false
                        }
                    })
            )
        } ?: run {
            Log.d("Make a error message", "client side error so make a navigation back with error ")
        }
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}