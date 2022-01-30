package gamerworld.projecttemtem.features.temtem_information

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import gamerworld.projecttemtem.databinding.TemtemDataBinding
import gamerworld.projecttemtem.model.TemTem
import gamerworld.projecttemtem.util.BASE_URL
import gamerworld.projecttemtem.util.getProgressDrawable
import gamerworld.projecttemtem.util.loadImage
import gamerworld.projecttemtem.viewmodel.TemTemDataViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class TemTemDataActivity : AppCompatActivity() {

    companion object {
        const val TEM_TEM_KEY = "tem_tem_key"
    }

    private lateinit var binding: TemtemDataBinding
    val viewModel by viewModel<TemTemDataViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = TemtemDataBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViewModel()
        setupView()
        setupTemTemDataView(viewModel.temtem)
    }

    private fun setupView() {
//        setupTemTemDataView(viewModel.temtem)
        viewModel.fetchSingleTemTem()   //implement swipe refresh for fun()
    }

    private fun setupViewModel() {
        viewModel.temtem = intent.getParcelableExtra(TEM_TEM_KEY)
        viewModel.temtemLiveData.observe(this, { temtem ->
            setupTemTemDataView(temtem)
        })
    }

    private fun setupTemTemDataView(temtem: TemTem?) {
        val progressDrawable = getProgressDrawable(this)
        with(binding) {
            temtemName.text = temtem?.name
            imageView.loadImage(
                BASE_URL + temtem?.icon,
                progressDrawable
            )
        }
    }


}