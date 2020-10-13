package ssk.dbzeus.ppm.service.model.entity.userdata

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import ssk.dbzeus.ppm.service.model.userdata.MultiLanguageX
import java.lang.reflect.Type

@Entity
@TypeConverters(DepartmentConverter::class)
data class Department(
    @ColumnInfo(name = "activeStatus") val activeStatus: Int?,
    @ColumnInfo(name = "clientId") val clientId: Int?,
    @ColumnInfo(name = "clientName") val clientName: String?,
    @PrimaryKey val departmentId: Int?,
    @ColumnInfo(name = "departmentKey") val departmentKey: String?,
    @ColumnInfo(name = "departmentLangId") val departmentLangId: Int?,
    @ColumnInfo(name = "departmentName") val departmentName: String?,
    @ColumnInfo(name = "departmentShortName") val departmentShortName: String?,
    @ColumnInfo(name = "languageId") val languageId: String?,
    @ColumnInfo(name = "multiLanguages") val multiLanguages: ArrayList<MultiLanguageX>?
)

{
    override fun toString(): String {
        return multiLanguages?.get(0)?.itemName.toString()
    }
}

class DepartmentConverter {
    private var gson = Gson()

    @TypeConverter
    fun listToJson(value: ArrayList<MultiLanguageX>?): String?? = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String?): ArrayList<MultiLanguageX> {
        val listType: Type = object : TypeToken<ArrayList<MultiLanguageX?>?>() {}.type
        return gson.fromJson(value, listType)
    }
}