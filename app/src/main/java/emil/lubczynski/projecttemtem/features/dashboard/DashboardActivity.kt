package emil.lubczynski.projecttemtem.features.dashboard

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import emil.lubczynski.projecttemtem.model.TemTem
import emil.lubczynski.projecttemtem.viewmodel.ListViewModel
import emil.lubczynski.projecttemtem.databinding.ActivityMainBinding




class DashboardActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    lateinit var viewModel: ListViewModel
    private val binding get() = _binding!!
    //example of KOIN injection
   //private val viewModel: ListViewModel by Inject()


    private val temtemAdapter = TemTemListAdapter(arrayListOf())

    //   activity_Main is set to be a swipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(_binding.root)

        _binding.temtemList.adapter = temtemAdapter
        viewModel = ViewModelProvider(this)[ListViewModel::class.java] //instantiating viewmodel
        viewModel.refresh()

        //"with" allows me to get rid of binding. in multiple occurrences
        with(_binding) {
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
            _binding.temtemList.visibility = View.VISIBLE
            temtems?.let { temtemAdapter.updateTemTems(it) } // if countries is not null then countries will be passed as a variable "it" and we can access it
        })

        viewModel.temtemLoadError.observe(this, Observer { isError: Boolean? ->
            isError?.let { _binding.listError.visibility = if (it) View.VISIBLE else View.GONE }
        })

        viewModel.loading.observe(this, Observer { isLoading: Boolean? ->
            isLoading?.let {
                _binding.loadingView.visibility = if (it) View.VISIBLE else View.GONE
                if (it) { //displaying only the loader and nothing else while it loads
                    _binding.listError.visibility = View.GONE
                    _binding.temtemList.visibility = View.GONE
                }
            }
        })
    }



}