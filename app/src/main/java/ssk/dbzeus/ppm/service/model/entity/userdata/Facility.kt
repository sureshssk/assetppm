package ssk.dbzeus.ppm.service.model.entity.userdata

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ssk.dbzeus.ppm.service.model.userdata.MultiLanguageXX
import java.lang.reflect.Type

@Entity
@TypeConverters(FacilityConverter::class)
data class Facility(
    @ColumnInfo(name = "activeStatus") val activeStatus: String?,
    @ColumnInfo(name = "cityId") val cityId: Int?,
    @ColumnInfo(name = "cityName") val cityName: String?,
    @ColumnInfo(name = "clientId") val clientId: Int?,
    @ColumnInfo(name = "contactNumber") val contactNumber: String?,
    @ColumnInfo(name = "countryId") val countryId: String?,
    @ColumnInfo(name = "countryName") val countryName: String?,
    @PrimaryKey val facilityId: Int?,
    @ColumnInfo(name = "facilityKey") val facilityKey: String?,
    @ColumnInfo(name = "facilityName") val facilityName: String?,
    @ColumnInfo(name = "facilityShortName") val facilityShortName: String?,
    @ColumnInfo(name = "facilitySqft") val facilitySqft: String?,
    @ColumnInfo(name = "fourWheelerSlots") val fourWheelerSlots: String?,
    @ColumnInfo(name = "landMark") val landMark: String?,
    @ColumnInfo(name = "mailId") val mailId: String?,
    @ColumnInfo(name = "multiLanguages") val multiLanguages: ArrayList<MultiLanguageXX>?,
    @ColumnInfo(name = "stateId") val stateId: String?,
    @ColumnInfo(name = "stateName") val stateName: String?,
    @ColumnInfo(name = "tellerDetails") val tellerDetails: String?,
    @ColumnInfo(name = "twoWheelerSlots") val twoWheelerSlots: String?,
    @ColumnInfo(name = "zipCode") val zipCode: String?
)

{
    override fun toString(): String {
        return multiLanguages?.get(0)?.itemName.toString()
    }
}


class FacilityConverter {
    private var gson = Gson()
    @TypeConverter
    fun listToJson(value: ArrayList<MultiLanguageXX>?): String?? = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String?): ArrayList<MultiLanguageXX> {
        val listType: Type = object : TypeToken<ArrayList<MultiLanguageXX?>?>() {}.type
        return gson.fromJson(value, listType)
    }
}