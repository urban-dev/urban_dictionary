package com.app.urbandictionary.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import androidx.annotation.NonNull
import androidx.core.content.res.ResourcesCompat
import com.app.urbandictionary.R
import kotlinx.android.synthetic.main.progress_bar.view.*

class UrbanDictionaryProgressDialog {

    companion object {
        lateinit var dialog: Dialog
        fun show(context: Context): Dialog {
            return show(context, null)
        }

        fun show(context: Context, title: CharSequence?): Dialog {
            val inflator =
                context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val view = inflator.inflate(R.layout.progress_bar, null)
            if (title != null) {
                view.cp_title.text = title
            }

            setColorFilter(
                view.cp_pbar.indeterminateDrawable,
                ResourcesCompat.getColor(context.resources, R.color.colorPrimary, null)
            )
            view.cp_title.setTextColor(Color.WHITE)

            dialog = Dialog(context, R.style.CustomProgressBarTheme)
            dialog.setContentView(view)
            dialog.show()

            return dialog
        }


        fun setColorFilter(@NonNull drawable: Drawable, color: Int) {
            drawable.setColorFilter(color, PorterDuff.Mode.SRC_ATOP)

        }

        fun dismissDialog (){
            dialog.dismiss()
        }
    }
}
