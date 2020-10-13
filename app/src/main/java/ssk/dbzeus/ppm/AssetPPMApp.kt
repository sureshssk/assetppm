package ssk.dbzeus.ppm

import android.app.Application
import android.content.Context
import ssk.dbzeus.ppm.service.model.entity.userdata.UserInfo

class AssetPPMApp : Application() {

    init {
        instance = this
    }

    companion object {
        var ctx: Context? = null
        private var instance: AssetPPMApp? = null
        private lateinit var userInfo: UserInfo

        fun setUser(user: UserInfo) {
            userInfo = user
        }

        fun getUser(): UserInfo {
            return userInfo
        }
    }

    override fun onCreate() {
        super.onCreate()
        ctx = applicationContext
    }

}