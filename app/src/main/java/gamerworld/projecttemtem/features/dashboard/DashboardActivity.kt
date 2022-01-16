package gamerworld.projecttemtem.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import gamerworld.projecttemtem.databinding.ActivityMainBinding
import gamerworld.projecttemtem.model.TemTem
import gamerworld.projecttemtem.viewmodel.ListViewModel


class DashboardActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    lateinit var viewModel: ListViewModel

    private val temtemAdapter = TemTemListAdapter(arrayListOf())

    //   activity_Main is set to be a swipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.temtemList.adapter = temtemAdapter
        viewModel = ViewModelProvider(this)[ListViewModel::class.java] //instantiating viewmodel
        viewModel.refresh()

        //"with" allows me to get rid of binding. in multiple occurrences
        with(binding) {
            temtemList.apply {
                this.layoutManager = LinearLayoutManager(context)
                adapter = temtemAdapter
            }
            swipeRefreshLayout.setOnRefreshListener {
                swipeRefreshLayout.isRefreshing = false
                viewModel.refresh()
            }
        }
        observeViewModel()
    }

    private fun observeViewModel() // observer connects to val countries = MutableLiveData<List<Country>>() variable in ListViewModel which when updated will notify anyone connected to the variable.
    {

        viewModel.temtem.observe(this, Observer { temtems: List<TemTem> ->
            binding.temtemList.visibility = View.VISIBLE
            temtems.let { temtemAdapter.updateTemTems(it) } // if countries is not null then countries will be passed as a variable "it" and we can access it
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