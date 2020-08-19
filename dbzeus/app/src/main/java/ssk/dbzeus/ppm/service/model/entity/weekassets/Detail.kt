package ssk.dbzeus.ppm.service.model.entity.weekassets

import android.util.Log
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.Serializable
import java.lang.reflect.Type

@Entity
@TypeConverters(DetailConverter::class)
data class Detail(
    //@PrimaryKey(autoGenerate = true)
    //@ColumnInfo(name = "assetFrequencyDetailModels")
    //@Embedded
    @ColumnInfo(name = "assetFrequencyDetailModels") val assetFrequencyDetailModels: ArrayList<AssetFrequencyDetailModel>,
    val completedCount: Int,
    val onHoldCount: Int,
    val openCount: Int,
    @PrimaryKey val weekNo: String,
    @ColumnInfo(name = "startDateofWeek") val startDateofWeek: String,
    @ColumnInfo(name = "endDateofWeek") val endDateofWeek: String
    //@ColumnInfo(name = "weekNo") val weekNo: String
): Serializable
/*{
    override fun toString(): String {
        return assetFrequencyDetailModels?.get(0)?.toString()
    }
}*/

class DetailConverter {
    private var gson = Gson()

    @TypeConverter
    fun listToJson(value: ArrayList<AssetFrequencyDetailModel>?): String??{
        return gson.toJson(value)
        Log.i("WeekAssetConversion","DatatoJson:$gson.toJson(value)")
    }

    @TypeConverter
    fun jsonToList(value: String?): ArrayList<AssetFrequencyDetailModel> {
        Log.i("WeekAssetConversion","Data:$value")
        val listType: Type = object : TypeToken<ArrayList<AssetFrequencyDetailModel?>?>() {}.type
        return gson.fromJson(value, listType)
    }

    @TypeConverter
    fun fromString(value: String?): ArrayList<String?>? {
        val listType =
            object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson<ArrayList<String?>>(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<String?>?): String? {
        val gson = Gson()
        return gson.toJson(list)
    }
}