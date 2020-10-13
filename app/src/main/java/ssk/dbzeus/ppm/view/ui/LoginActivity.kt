package ssk.dbzeus.ppm.view.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_login.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import ssk.dbzeus.ppm.AppDb
import ssk.dbzeus.ppm.AssetPPMApp
import ssk.dbzeus.ppm.databinding.ActivityLoginBinding
import ssk.dbzeus.ppm.service.model.entity.userdata.UserData
import ssk.dbzeus.ppm.service.model.entity.userdata.UserInfo
import ssk.dbzeus.ppm.service.repository.APIService
import ssk.dbzeus.ppm.service.repository.RetrofitInstance
import ssk.dbzeus.ppm.service.viewmodel.ObjectViewModel
import ssk.dbzeus.ppm.utils.Utils
import ssk.dbzeus.ppm.view.adapter.AssetListAdapter


class LoginActivity : BaseActivity() {
    private lateinit var objectViewModel: ObjectViewModel
    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        getPermission()
        setContentView(view)

        objectViewModel = ViewModelProvider(this).get(ObjectViewModel::class.java)
        //this.username.setText("prasad")
        this.username.setText("prasad")
        this.password.setText("admin123")
        this.buttonSingIn.setOnClickListener {
            val userName = username.text.toString().trim()
            val passWord = password.text.toString().trim()
            if (userName.isNotEmpty() && passWord.isNotEmpty()) {
                if (Utils.checkInternet(applicationContext)) {
                    loading.visibility = View.VISIBLE
                    loginAPI(userName, passWord)
                } else {
                    Toast.makeText(this@LoginActivity, "No Internet!", Toast.LENGTH_LONG).show()
                    loading.visibility = View.GONE
                    checkOfflineLogin(userName, passWord)
                }
            }
        }


    }

    private fun checkOfflineLogin(username: String, password: String) {
        var userData: UserInfo



        //userInfo
        objectViewModel.getUserInfo(username,password,application).observe(this@LoginActivity, Observer { assetList ->
            assetList?.let {
                if (it != null) {
                    Toast.makeText(
                        applicationContext,
                        "Offline Login Success",
                        Toast.LENGTH_SHORT
                    ).show()
                    AssetPPMApp.setUser(it)
                    val bundleData = Bundle()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    bundleData.putSerializable("UserInfo", it)
                    intent.putExtras(bundleData)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Offline Login Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        })

        /*AsyncTask.execute {
            userData =
                AppDb.getInstance(this@LoginActivity).userDao().checkLogin(username, password)
            runOnUiThread {
                if (userData != null) {
                    Toast.makeText(
                        applicationContext,
                        "Offline Login Success",
                        Toast.LENGTH_SHORT
                    ).show()
                    AssetPPMApp.setUser(userData)
                    val bundleData = Bundle()
                    val intent = Intent(applicationContext, MainActivity::class.java)
                    bundleData.putSerializable("UserInfo", userData)
                    intent.putExtras(bundleData)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        applicationContext,
                        "Offline Login Failed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }*/
    }


    private fun loginAPI(username: String, password: String) {
        val retIn = RetrofitInstance.getRetrofitInstance().create(APIService::class.java)

        Log.e("Login", "Info:$username Password:$password")
        val requestBody: RequestBody = MultipartBody.Builder()
            .setType(MultipartBody.FORM)
            .addFormDataPart("user", username)
            .addFormDataPart("password", password)
            .build()

        retIn.login(requestBody).enqueue(object : retrofit2.Callback<UserData> {
            override fun onFailure(call: retrofit2.Call<UserData>, t: Throwable) {
                Toast.makeText(this@LoginActivity, "Login Error:$t", Toast.LENGTH_LONG).show()
                loading.visibility = View.GONE
            }

            override fun onResponse(
                call: retrofit2.Call<UserData>,
                response: retrofit2.Response<UserData>
            ) {
                if (response.code() == 200) {
                    var intentMainActivity: Intent
                    response.body()?.facilities?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(this@LoginActivity).facilityDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.departments?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(this@LoginActivity).departmentDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.clients?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(this@LoginActivity).clientDao().insertAll(
                                it
                            )
                        }
                    }
                    response.body()?.userinfo?.let {
                        AsyncTask.execute {
                            AppDb.getInstance(this@LoginActivity).userDao().insertAll(
                                it
                            )
                            AssetPPMApp.setUser(it)
                            val bundleData = Bundle()
                            intentMainActivity = Intent(applicationContext, MainActivity::class.java)
                            bundleData.putSerializable("UserInfo", it)
                            intentMainActivity.putExtras(bundleData)
                            startActivity(intentMainActivity)
                        }

                    }
                    loading.visibility = View.GONE
                    Toast.makeText(this@LoginActivity, "Login success!", Toast.LENGTH_LONG).show()
                    //var appDB = Room.databaseBuilder(applicationContext, AppDb::class.java, "AssetPPM").build()

                } else {
                    loading.visibility = View.GONE

                    Toast.makeText(this@LoginActivity, "Login failed!", Toast.LENGTH_LONG).show()
                }
            }
        })
    }


}