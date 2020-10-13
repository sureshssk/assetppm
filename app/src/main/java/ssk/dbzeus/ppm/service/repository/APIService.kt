package ssk.dbzeus.ppm.service.repository


import okhttp3.RequestBody
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query
import ssk.dbzeus.ppm.service.model.entity.asset.AssetData
import ssk.dbzeus.ppm.service.model.entity.userdata.UserData
import ssk.dbzeus.ppm.service.model.entity.weekassets.WeekAssets

interface APIService {

    @POST("LogInAPI/LogIn/")
    fun login(@Body fieldData: RequestBody): retrofit2.Call<UserData>

    @GET("AssetAPI/GetAssetBasedInfo")
    fun getAssets(@Query("UserId") userId: Int): retrofit2.Call<AssetData>

    @GET("AssetFrequencyMaintenanceAPI/GetAssetMaintenanceDetail")
    fun getWeekAssets(@Query("UserId") userId: Int): retrofit2.Call<WeekAssets>
}

