package emil.lubczynski.projecttemtem.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import emil.lubczynski.projecttemtem.R
import emil.lubczynski.projecttemtem.model.TemTem
import emil.lubczynski.projecttemtem.util.getProgressDrawable
import emil.lubczynski.projecttemtem.util.loadImage


class TemTemListAdapter(var temtems: ArrayList<TemTem>) :
    RecyclerView.Adapter<TemTemListAdapter.TemTemViewHolder>() {

    fun updateTemTems(newCountries: List<TemTem>) {
        temtems.clear()
        temtems.addAll(newCountries)
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

        private lateinit var imageView: ImageView
        private lateinit var temtemName: TextView
        private lateinit var temtemNumber: TextView

        private val progressDrawable = getProgressDrawable(view.context)

        fun bind(temtem: TemTem) {
            temtemNumber.text = temtem.TemTemNumber
            temtemName.text = temtem.TemTemName
            imageView.loadImage(
                temtem.TemTemImage,
                progressDrawable
            )
            //progress drawable will be a spinner that shows up while image is loading for user

        }

    }
}