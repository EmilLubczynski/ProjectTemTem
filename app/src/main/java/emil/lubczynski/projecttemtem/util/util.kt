package emil.lubczynski.projecttemtem.util

import android.content.Context
import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import emil.lubczynski.projecttemtem.R

fun getProgressDrawable(context: Context): CircularProgressDrawable {
//creates a spinner
    return CircularProgressDrawable(context).apply {
        strokeWidth = 10f
        centerRadius = 50f
        start()
    }
}

//extending ImageVIew
fun ImageView.loadImage(uri: String?, progressDrawable: CircularProgressDrawable) {
    val options = RequestOptions()
        .placeholder(progressDrawable)
        .error(R.mipmap.ic_launcher_round)
///create glide, use options and load the uri into image
    Glide.with(this.context)
        .setDefaultRequestOptions(options)
        .load(uri)
        .into(this)

}