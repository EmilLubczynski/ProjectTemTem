package emil.lubczynski.projecttemtem.features.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import emil.lubczynski.projecttemtem.model.TemTem
import emil.lubczynski.projecttemtem.R
import emil.lubczynski.projecttemtem.databinding.ItemTemtemBinding
import emil.lubczynski.projecttemtem.util.getProgressDrawable
import emil.lubczynski.projecttemtem.util.loadImage
import kotlinx.android.synthetic.*

import kotlin.with as with

class TemTemListAdapter(var temtems: ArrayList<TemTem>) :
    RecyclerView.Adapter<TemTemListAdapter.TemTemViewHolder>() {

    fun updateTemTems(newTemTems: List<TemTem>) {
        temtems.clear()
        temtems.addAll(newTemTems)
        notifyDataSetChanged()  // this is responsible for rebuilding the list adapter to get all new countries
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = TemTemViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.item_temtem, parent, false
        )

    )

    override fun onBindViewHolder(holder: TemTemViewHolder, position: Int) {
        holder.bind(temtems[position])
    }

    override fun getItemCount() = temtems.size

    class TemTemViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        //need to tidy this BASE_URL up
        private val itemBind = ItemTemtemBinding.bind(view)

        private val BASE_URL_ICON = "https://temtem-api.mael.tech/"
        private var temtemTypeList: List<Int> = ArrayList()
        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(temtem: TemTem) {
            with(itemBind) {
                temtemNumber.text = temtem.TemTemNumber
                temtemName.text = temtem.TemTemName
                imageView.loadImage(
                    BASE_URL_ICON + temtem.TemTemImage,
                    progressDrawable
                )
                temtemTypeList = temtemTypeImage(temtem.TemTemType)

                if (temtemTypeList.size > 1) {
                    temtemType1.setImageResource(temtemTypeList[0])
                    temtemType2.setImageResource(temtemTypeList[1])
                } else {
                    temtemType1.setImageResource(temtemTypeList[0])
                    temtemType2.setImageDrawable(null)
                }
                //progress drawable will be a spinner that shows up while image is loading for user
            }
            temtemTypeList = ArrayList()
        }

        private fun temtemTypeImage(temtemTypeList: List<String?>?): ArrayList<Int> {
            val imageList: ArrayList<Int> = ArrayList()

            if (temtemTypeList != null) {
                for (temtemType in temtemTypeList) {
                    when (temtemType) {
                        "Digital" -> {
                            imageList.add(R.drawable.digital)
                        }
                        "Melee" -> {
                            imageList.add(R.drawable.melee)
                        }
                        "Toxic" -> {
                            imageList.add(R.drawable.toxic)
                        }
                        "Fire" -> {
                            imageList.add(R.drawable.fire)
                        }
                        "Neutral" -> {
                            imageList.add(R.drawable.neutral)
                        }
                        "Water" -> {
                            imageList.add(R.drawable.water)
                        }
                        "Electric" -> {
                            imageList.add(R.drawable.electric)
                        }
                        "Mental" -> {
                            imageList.add(R.drawable.mental)
                        }
                        "Earth" -> {
                            imageList.add(R.drawable.earth)
                        }
                        "Wind" -> {
                            imageList.add(R.drawable.wind)
                        }
                        "Crystal" -> {
                            imageList.add(R.drawable.crystal)
                        }
                        "Nature" -> {
                            imageList.add(R.drawable.nature)
                        }
                    }

                }
            }

            return imageList;

        }


    }

}