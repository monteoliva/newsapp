package br.com.newsapp.repository.core.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

import android.content.Context
import android.view.View
import android.widget.Toast
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.content.ContextCompat

import br.com.newsapp.repository.model.WsResult
import br.com.newsapp.repository.model.topHeadlines.TopHeadlines
import br.com.newsapp.ui.features.BaseActivity

fun View.visible()   { this.visibility = View.VISIBLE   }
fun View.invisible() { this.visibility = View.INVISIBLE }
fun View.gone()      { this.visibility = View.GONE      }

fun View.visibility(hasVisible: Boolean) {
    if (hasVisible) { this.visible() } else { this.gone() }
}

fun View.isVisible():   Boolean = this.visibility == View.VISIBLE
fun View.isInvisible(): Boolean = this.visibility == View.INVISIBLE
fun View.isGone():      Boolean = this.visibility == View.GONE

fun random() : Long = (Math.random() * 9999999).toLong()

fun Context?.showToast(@StringRes message: Int) { this?.getString(message)?.let { this.showToast(it) }}
fun Context?.showToast(message: String) {
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

fun ImageView.setTintColor(context: Context, @ColorRes color: Int) {
    this.setColorFilter(ContextCompat.getColor(context, color))
}

fun View.hideKeyboard(inputMethodManager: InputMethodManager) {
    inputMethodManager.hideSoftInputFromWindow(this.windowToken, 0)
}

fun View.showKeyboard(inputMethodManager: InputMethodManager) {
    inputMethodManager.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}

fun String.getDate(ff: String) : String {
    ff.let {
        val dataO = if (this.trim().length > 10) "yyyy-MM-dd HH:mm:ss" else "yyyy-MM-dd"
        val dataH = when(ff) {
            "ext"   -> "MMMM dd',' yyyy 'às' HH:mm"
            "dma"   -> "dd/MM/yyyy"
            "amd"   -> "yyyyMMdd"
            "dmah"  -> "dd/MM/yyyy HH:mm"
            "dmahs" -> "dd/MM/yyyy HH:mm:ss"
            "hms"   -> "HH:mm:ss"
            "hm"    -> "HH:mm"
            else    -> "dd/MM/yyyy"
        }

        val f0 = SimpleDateFormat(dataO, Locale.getDefault())
        val f1 = SimpleDateFormat(dataH, Locale.getDefault())

        try {
            val c = GregorianCalendar(Locale.getDefault())
            f0.parse(this)?.let { c.time = it }
            return f1.format(c.time)
        }
        catch (pe: ParseException) {}
    }
    return ""
}

fun WsResult<TopHeadlines>.hasResponse(base: BaseActivity, callback: () -> Unit) {
    when(codeError) {
        0 -> {
            if (result?.status == "error") {
                base.boxMsg(result?.message.toString()){}
            }
            else { callback.invoke() }
        }
        9 -> base.boxMsg(message.toString()) {}
    }
}
