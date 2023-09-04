package gbard.webview.com.loaders

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.view.Gravity
import android.view.animation.Animation
import android.view.animation.LinearInterpolator
import android.view.animation.RotateAnimation
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import gbard.webview.com.R
import android.widget.LinearLayout.LayoutParams


class TransparentProgressDialog(context: Context?, dialogText: String?) :
    Dialog(context!!, R.style.TransparentProgressDialog) {
    private val iv: AppCompatImageView

    init {
        val wlmp = window!!.attributes
        wlmp.gravity = Gravity.CENTER_HORIZONTAL
        window!!.attributes = wlmp
        setTitle(null)
        setCancelable(false)
        setOnCancelListener(null)
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        val txtInst = AppCompatTextView(context!!)
        txtInst.text = dialogText
        txtInst.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        txtInst.setTextColor(Color.WHITE)
        txtInst.textSize = 16f
        txtInst.gravity = Gravity.CENTER_HORIZONTAL
        txtInst.setBackgroundColor(Color.TRANSPARENT)
        val params = LayoutParams(150, 150)
        iv = AppCompatImageView(context)
        iv.setImageResource(R.drawable.loading)
        //reduce image size
        layout.addView(iv, params)
        layout.addView(txtInst, params)
        addContentView(layout, params)
    }

    override fun show() {
        super.show()
        val anim = RotateAnimation(
            0.0f,
            360.0f,
            Animation.RELATIVE_TO_SELF,
            .5f,
            Animation.RELATIVE_TO_SELF,
            .5f
        )
        anim.interpolator = LinearInterpolator()
        anim.repeatCount = Animation.INFINITE
        anim.duration = 3000
        iv.animation = anim
        iv.startAnimation(anim)
    }
}