package eg.gov.iti.softexpert.main.utils

import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter(value = ["imageUrl", "placeholder"], requireAll = false)
fun ImageView.loadImage(url: String?, placeholder: Drawable?) {
    Glide.with(this)
        .load(url)
        .skipMemoryCache(true)
        .placeholder(placeholder)
        .into(this)
}
