package emil.lubczynski.projecttemtem.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import androidx.recyclerview.widget.RecyclerView
import emil.lubczynski.projecttemtem.model.TemTem
import emil.lubczynski.projecttemtem.R
import emil.lubczynski.projecttemtem.util.getProgressDrawable
import emil.lubczynski.projecttemtem.util.loadImage
import kotlinx.android.synthetic.main.item_temtem.view.*

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
         private val BASE_URL_ICON = "https://temtem-api.mael.tech/"
         private val temtemNumber = view.temtemNumber
         private val temtemName = view.temtemName
         private val imageView = view.imageView

        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(temtem: TemTem) {
            temtemNumber.text = temtem.TemTemNumber
            temtemName.text = temtem.TemTemName
            imageView.loadImage(
                BASE_URL_ICON + temtem.TemTemImage,
                progressDrawable
            )

            //progress drawable will be a spinner that shows up while image is loading for user

        }

    }
}