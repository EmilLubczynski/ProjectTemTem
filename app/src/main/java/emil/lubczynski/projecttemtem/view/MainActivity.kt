package emil.lubczynski.projecttemtem.view

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import emil.lubczynski.projecttemtem.R
import emil.lubczynski.projecttemtem.model.TemTem
import emil.lubczynski.projecttemtem.viewmodel.ListViewModel


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ListViewModel
    private val temtemAdapter = TemTemListAdapter(arrayListOf())
    lateinit var temtemList: RecyclerView
    lateinit var listError : TextView
    lateinit var loading_view : ProgressBar
    lateinit var swipeRefreshLayout : SwipeRefreshLayout

 //   activity_Main is set to be a swipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//        temtemList = this.findViewById(R.id.temtemList)
//        temtemList.setAdapter(temtemAdapter)
        viewModel = ViewModelProvider(this).get(ListViewModel::class.java) //instantiating viewmodel
        viewModel.refresh()

     temtemList.apply {
         this.layoutManager = LinearLayoutManager(context)
         adapter = temtemAdapter
     }
     swipeRefreshLayout.setOnRefreshListener {
         swipeRefreshLayout.isRefreshing = false
         viewModel.refresh()
     }

     observeViewModel()
    }

    private fun observeViewModel() // observer connects to val countries = MutableLiveData<List<Country>>() variable in ListViewModel which when updated will notify anyone connected to the variable.
    {
        viewModel.temtem.observe(this, Observer { temtems: List<TemTem> ->
            temtemList.visibility = View.VISIBLE
            temtems?.let { temtemAdapter.updateTemTems(it) } // if countries is not null then countries will be passed as a variable "it" and we can access it
        })

        viewModel.temtemLoadError.observe(this, Observer { isError: Boolean? ->
            isError?.let { listError.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading: Boolean? ->
            isLoading?.let {
                loading_view.visibility = if (it) View.VISIBLE else View.GONE
                if (it) { //displaying only the loader and nothing else while it loads
                    listError.visibility = View.GONE
                    temtemList.visibility = View.GONE
                }
            }
        })
    }
}