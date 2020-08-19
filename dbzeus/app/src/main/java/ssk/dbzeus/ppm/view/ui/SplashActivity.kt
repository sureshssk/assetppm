package ssk.dbzeus.ppm.view.ui

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.annotation.RequiresApi
import ssk.dbzeus.ppm.R
import java.io.File

class SplashActivity : BaseActivity() {
    private lateinit var mHandler: Handler
    private lateinit var mRunnable: Runnable

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        supportActionBar?.hide()
        hideSystemUI()
        mRunnable = Runnable {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
        mHandler = Handler()
        mHandler.postDelayed(mRunnable, 3000)
    }

    override fun onStop() {
        super.onStop()
        mHandler.removeCallbacks(mRunnable)
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    private fun hideSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_IMMERSIVE
                or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_FULLSCREEN)
    }
}