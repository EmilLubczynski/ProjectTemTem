package gamerworld.projecttemtem.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import gamerworld.projecttemtem.databinding.ActivityMainBinding
import gamerworld.projecttemtem.model.TemTem
import gamerworld.projecttemtem.viewmodel.ListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class DashboardActivity : AppCompatActivity() {

    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    val viewModel by viewModel<ListViewModel>()
    private var temtemAdapter = TemTemListAdapter(arrayListOf())

    //   activity_Main is set to be a swipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupView()
        setupViewModel()
        setContentView(binding.root)
    }

    private fun setupView() {
        viewModel.refresh()
        setupTemTemListView()
    }

    private fun setupTemTemListView() {
        //"with" allows me to get rid of binding. in multiple occurrences
        with(binding) {
            temtemList.adapter = temtemAdapter
            temtemList.apply {
                this.layoutManager = LinearLayoutManager(context)
                adapter = temtemAdapter
            }
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false
                viewModel.refresh()
            }

        }
    }

    private fun setupViewModel() // observer connects to val TemTem = MutableLiveData<List<TemTem>>() variable in ListViewModel which when updated will notify anyone connected to the variable.
    {
        viewModel.temtems.observe(this, Observer { temtems: List<TemTem> ->
            binding.temtemList.visibility = View.VISIBLE
            temtems.let { temtemAdapter.updateTemTems(it) } // if temTems is not null then temtem will be passed as a variable "it" and we can access it
        })

        viewModel.temtemLoadError.observe(this, Observer { isError: Boolean? ->
            isError?.let { binding.listError.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading: Boolean? ->
            isLoading?.let {
                binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) { //displaying only the loader and nothing else while it loads
                    binding.listError.visibility = View.GONE
                    binding.temtemList.visibility = View.GONE
                }
            }
        })
    }
}


