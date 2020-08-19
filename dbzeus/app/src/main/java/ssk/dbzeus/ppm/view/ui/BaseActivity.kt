package ssk.dbzeus.ppm.view.ui


import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.nav_header.*
import java.io.File

open class BaseActivity : AppCompatActivity() {
    private val permissionCODE = 1000
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    open fun initToolbar() {
        setSupportActionBar(toolbar)
        assert(toolbar != null)
        textAppname.visibility = View.VISIBLE
        textActivityTitle.visibility = View.GONE
        logoutIcon.setOnClickListener {
            startActivity(Intent(this, LoginActivity::class.java))
            finish()
        }
    }

    open fun setTitle(titleText: String){
        textActivityTitle.visibility = View.VISIBLE
        textActivityTitle.text = titleText
        textAppname.visibility = View.GONE
    }

    open fun getPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED ||
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                == PackageManager.PERMISSION_DENIED
            ) {
                val permission = arrayOf(
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                requestPermissions(permission, permissionCODE)
            } else {
                val folder = getExternalFilesDir("/sdcard/")
                val f = File(folder, "AssetPPM")
                f.mkdir()
            }
        } else {
            val folder = getExternalFilesDir("/sdcard/")
            val f = File(folder, "AssetPPM")
            f.mkdir()
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            permissionCODE -> {
                if (grantResults.isNotEmpty() && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED
                ) {
                    val folder = getExternalFilesDir("/sdcard/")
                    val f = File(folder, "AssetPPM")
                    f.mkdir()
                } else {
                    Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}