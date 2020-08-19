package ssk.dbzeus.ppm.view.ui


import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.nav_header.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ssk.dbzeus.ppm.AppDb
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.asset.AssetData
import ssk.dbzeus.ppm.service.model.entity.userdata.Client
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility
import ssk.dbzeus.ppm.service.model.entity.userdata.UserInfo
import ssk.dbzeus.ppm.service.repository.APIService
import ssk.dbzeus.ppm.service.repository.RetrofitInstance
import ssk.dbzeus.ppm.utils.Utils

open class MainActivity_Backup : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        setSupportActionBar(toolbar)
        val bundle: Bundle? = intent.extras
        val userInfo = bundle!!.getSerializable("UserInfo") as? UserInfo
        if (userInfo != null) {

            AsyncTask.execute {
                val clientData = AppDb.getInstance(applicationContext).clientDao()
                    .getClientById(userInfo.clientId) as Client
                val facilityData = AppDb.getInstance(applicationContext).facilityDao()
                    .getFacilityById(userInfo.facilityId) as Facility
                textUser.text = userInfo.userName
                textCompany.text = clientData.multiLanguages?.get(0)?.itemName
                textLocation.text = facilityData.multiLanguages?.get(0)?.itemName
            }

            if (Utils.checkInternet(applicationContext)) {
                assetAPI(userInfo.userId)
            } else {
                Toast.makeText(applicationContext, "Asset Data Offline", Toast.LENGTH_LONG).show()
            }
        }

        textManual.setOnClickListener {
            startActivity(Intent(this, SelectClient::class.java))
        }
    }

    private fun assetAPI(userId: Int) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(APIService::class.java)
        loading.visibility = View.VISIBLE
        retIn.getAssets(userId).enqueue(object : Callback<AssetData> {
            override fun onFailure(call: Call<AssetData>, t: Throwable) {
                loading.visibility = View.GONE
                Toast.makeText(this@MainActivity_Backup, "Asset Fetch Error:$t", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<AssetData>,
                response: Response<AssetData>
            ) {
                if (response.code() == 200) {
                    Toast.makeText(this@MainActivity_Backup, "Asset Fetch success!", Toast.LENGTH_LONG)
                        .show()
                } else {
                    Toast.makeText(this@MainActivity_Backup, "Asset Fetch failed!", Toast.LENGTH_LONG)
                        .show()
                }
                loading.visibility = View.GONE
            }
        })
    }
}