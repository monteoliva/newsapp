package br.com.newsapp.ui.features

import android.content.Intent
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.appcompat.app.AlertDialog
import androidx.annotation.StringRes
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.content.ContextCompat

import br.com.newsapp.R
import br.com.newsapp.repository.core.extensions.bottomToTop
import br.com.newsapp.repository.core.extensions.leftToRight
import br.com.newsapp.repository.core.extensions.rightToLeft
import br.com.newsapp.repository.core.extensions.topToBottom

abstract class BaseActivity : AppCompatActivity() {
    private var actionBar: ActionBar? = null

    fun startInitViews() {
        Handler(Looper.getMainLooper()).postDelayed({ initViews() }, 60)
    }

    fun startInitViewModel() {
        Handler(Looper.getMainLooper()).postDelayed({ initViewModel() }, 60)
    }

    fun setupToolBar(toolbar: Toolbar) {
        toolbar.apply {
            this@BaseActivity.setSupportActionBar(this)
            setTitleTextColor(ContextCompat.getColor(baseContext, R.color.white))
        }
        actionBar = supportActionBar
    }

    fun setActionBarHome()       { actionBar?.setHomeButtonEnabled(true) }
    fun setActionBarHomeButton() { actionBar?.setDisplayHomeAsUpEnabled(true) }

    fun setActionBarNotHome()       { actionBar?.setHomeButtonEnabled(false) }
    fun setActionBarNotHomeButton() { actionBar?.setDisplayHomeAsUpEnabled(false) }

    fun setActionBarTitle(title: String)         { actionBar?.title = title }
    fun setActionBarTitle(@StringRes title: Int) { actionBar?.title = getString(title) }

    fun setActionBarSubTitle(title: String)         { actionBar?.subtitle = title }
    fun setActionBarSubTitle(@StringRes title: Int) { actionBar?.subtitle = getString(title) }

    fun Intent.goActivityWithFinish() {
        startActivity(this)
        finish()
        animRightToLeft()
    }

    fun Intent.goActivityBackWithFinish() {
        startActivity(this)
        finish()
        animLeftToRight()
    }

    fun Intent.goActivityNoFinish() {
        startActivity(this)
        animRightToLeft()
    }

    fun errorMsg(@StringRes msg: Int) { errorMsg(getString(msg)) }
    private fun errorMsg(message: String) {
        setLoading(false)
        AlertDialog.Builder(this, R.style.AlertDialogTheme).apply {
            setCancelable(false)
            setMessage(message)
            setPositiveButton(getString(R.string.btn_ok)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            create().show()
        }
    }

    fun boxMsg(@StringRes msg: Int, callback: () -> Unit) { boxMsg(getString(msg)) { callback.invoke() }  }
    private fun boxMsg(message: String, callback: () -> Unit) {
        setLoading(false)
        AlertDialog.Builder(this, R.style.AlertDialogTheme).apply {
            setCancelable(false)
            setMessage(message)
            setPositiveButton(getString(R.string.btn_ok)) { dialogInterface, _ ->
                dialogInterface.dismiss()
                callback.invoke()
            }
            setNegativeButton(getString(R.string.btn_cancel)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }
            create().show()
        }
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_BACK) {
            back()
            true
        }
        else {
            super.onKeyDown(keyCode, event)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        back()
        return true
    }

    fun animLeftToRight() { this.leftToRight() }
    private fun animRightToLeft() { this.rightToLeft() }
    fun animTopToBottom() { this.topToBottom() }
    fun animBottomToTop() { this.bottomToTop() }

    abstract fun initViews()
    abstract fun initViewModel()
    abstract fun back()
    abstract fun setLoading(isLoading: Boolean)

    companion object {
        private const val TAG = "BaseActivity"
    }
}