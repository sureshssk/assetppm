package ssk.dbzeus.ppm.view.ui


import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import ssk.dbzeus.ppm.AppDb
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.asset.AssetData
import ssk.dbzeus.ppm.service.model.entity.userdata.Client
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility
import ssk.dbzeus.ppm.service.model.entity.userdata.UserInfo
import ssk.dbzeus.ppm.service.model.entity.weekassets.WeekAssets
import ssk.dbzeus.ppm.service.repository.APIService
import ssk.dbzeus.ppm.service.repository.RetrofitInstance
import ssk.dbzeus.ppm.utils.Utils

open class MainActivity : BaseActivity() {
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initToolbar()
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
            startActivity(Intent(this, SelectMaintenance::class.java))
        }
        rlQrcode.setOnClickListener(View.OnClickListener {
            getPermissionCamera()
        })
    }

    private fun assetAPI(userId: Int) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(APIService::class.java)
        loading.visibility = View.VISIBLE
        retIn.getAssets(userId).enqueue(object : Callback<AssetData> {
            override fun onFailure(call: Call<AssetData>, t: Throwable) {
                loading.visibility = View.GONE
                Toast.makeText(applicationContext, "Asset Fetch Error:$t", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(
                call: Call<AssetData>,
                response: Response<AssetData>
            ) {
                if (response.code() == 200) {
                    response.body()?.building?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).buildingDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.buildinglang?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).buildingLangDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.floor?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).floorDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.wing?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).wingDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.space?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).spaceDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.assets?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).assetDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.assetsmap?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).assetMapDao().insertAll(
                                it
                            )
                        }
                    }

                    response.body()?.category?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).categoryDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.categorylang?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).categoryDao().insertAllLang(
                                it
                            )
                        }
                    }
                    response.body()?.subcategory?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).subCategoryDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.subcategorylang?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).subCategoryDao().insertAllLang(
                                it
                            )
                        }
                    }
                    response.body()?.frequency?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).frequencyDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.frequencylang?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).frequencyDao().insertAllfreqLang(
                                it
                            )
                        }
                    }
                    response.body()?.workingstatus?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).workingStatusDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.assetworkorder?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).workOrderDao().insertAll(
                                it
                            )
                        }
                    }
                    weekAssetAPI(userId)
                    //Toast.makeText(applicationContext, "Asset Fetch success!", Toast.LENGTH_LONG).show()
                } else {
                    //Toast.makeText(applicationContext, "Asset Fetch failed!", Toast.LENGTH_LONG).show()
                }
                loading.visibility = View.GONE
            }
        })
    }

    private fun weekAssetAPI(userId: Int) {
        val retIn = RetrofitInstance.getRetrofitInstance_VMS().create(APIService::class.java)
        loading.visibility = View.VISIBLE
        retIn.getWeekAssets(userId).enqueue(object : Callback<WeekAssets> {
            override fun onFailure(call: Call<WeekAssets>, t: Throwable) {
                loading.visibility = View.GONE
                Toast.makeText(applicationContext, "Week Asset Fetch Error:$t", Toast.LENGTH_LONG)
                    .show()
            }

            override fun onResponse(
                call: Call<WeekAssets>,
                response: Response<WeekAssets>
            ) {
                if (response.code() == 200) {
                    response.body()?.details?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(applicationContext).detailDao().insertAll(
                                it
                            )
                        }
                    }

                    Toast.makeText(
                        applicationContext,
                        "Week Details Fetch success!",
                        Toast.LENGTH_LONG
                    )
                        .show()
                } else {
                    Toast.makeText(applicationContext, "Week Details failed!", Toast.LENGTH_LONG)
                        .show()
                }
                loading.visibility = View.GONE
            }
        })
    }
}