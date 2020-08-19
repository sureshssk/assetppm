package ssk.dbzeus.ppm.service.model.entity.userdata

import androidx.annotation.Nullable
import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity
@TypeConverters(ClientConverter::class)
data class Client(
    @ColumnInfo(name = "activeStatus") val activeStatus: String?,
    @ColumnInfo(name = "cityId") val cityId: Int?,
    @ColumnInfo(name = "cityName") val cityName: String?,
    @ColumnInfo(name = "clientGroupId") val clientGroupId: String?,
    @ColumnInfo(name = "clientGroupName") val clientGroupName: String?,
    @PrimaryKey val clientId: Int,
    @ColumnInfo(name = "clientImage") val clientImage: String?,
    @ColumnInfo(name = "clientKey") val clientKey: String?,
    @ColumnInfo(name = "clientName") val clientName: String?,
    @ColumnInfo(name = "clientShortName") val clientShortName: String?,
    @ColumnInfo(name = "contactNumber") val contactNumber: String?,
    @ColumnInfo(name = "continentId") val continentId: Int?,
    @ColumnInfo(name = "continentName") val continentName: String?,
    @ColumnInfo(name = "countryId") val countryId: Int?,
    @ColumnInfo(name = "countryName") val countryName: String?,
    @ColumnInfo(name = "mailId") val mailId: String?,
    @ColumnInfo(name = "multiLanguages")
    @Nullable val multiLanguages: ArrayList<MultiLanguage>?,
    @ColumnInfo(name = "stateId") val stateId: Int?,
    @ColumnInfo(name = "stateName") val stateName: String?,
    @ColumnInfo(name = "zipCode") val zipCode: String?
)

{
    override fun toString(): String {
        return multiLanguages?.get(0)?.itemName.toString()
    }
}

class ClientConverter {
    private var gson = Gson()

    @TypeConverter
    fun listToJson(value: ArrayList<MultiLanguage>?): String? = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String): ArrayList<MultiLanguage> {
        val listType: Type = object : TypeToken<ArrayList<MultiLanguage?>?>() {}.type
        return gson.fromJson(value, listType)
    }
}
