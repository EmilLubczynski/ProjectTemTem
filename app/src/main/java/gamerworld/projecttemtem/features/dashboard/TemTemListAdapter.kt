package gamerworld.projecttemtem.features.dashboard

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gamerworld.projecttemtem.R
import gamerworld.projecttemtem.databinding.ItemTemtemBinding
import gamerworld.projecttemtem.model.TemTem
import gamerworld.projecttemtem.util.BASE_URL
import gamerworld.projecttemtem.util.getProgressDrawable
import gamerworld.projecttemtem.util.loadImage
import gamerworld.projecttemtem.features.temtem_information.TemTemDataActivity
import gamerworld.projecttemtem.features.temtem_information.TemTemDataActivity.Companion.TEM_TEM_KEY

class TemTemListAdapter(private val temtems: ArrayList<TemTem>) :
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
        private val itemBind = ItemTemtemBinding.bind(view)
        private var temtemTypeList: List<Int> = ArrayList()
        private val progressDrawable = getProgressDrawable(view.context)


        fun bind(temtem: TemTem) {
            with(itemBind) {

                temtemNumber.text = temtem.number
                temtemName.text = temtem.name
                imageView.loadImage(
                    //progress drawable will be a spinner that shows up while image is loading for user
                    BASE_URL + temtem.icon,
                    progressDrawable
                )
                temtemTypeList = temtemTypeImage(temtem.types)

                if (temtemTypeList.size > 1) {
                    temtemType1.setImageResource(temtemTypeList[0])
                    temtemType2.setImageResource(temtemTypeList[1])
                } else {
                    temtemType1.setImageResource(temtemTypeList[0])
                    temtemType2.setImageDrawable(null)
                }

                itemTemtem.setOnClickListener { view ->
                    val intent = Intent(view.context, TemTemDataActivity::class.java)
                    intent.putExtra(TEM_TEM_KEY, temtem)
                    view.context.startActivity(intent)
                }
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