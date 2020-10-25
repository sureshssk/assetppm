package ssk.dbzeus.ppm.service.model.entity.insertdata

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@TypeConverters(spareDataListConverter::class)
class sparedata(spareListitem: ArrayList<sparedataItem>) : ArrayList<sparedataItem>()
class spareDataListConverter {
    private var gson = Gson()

    @TypeConverter
    fun listToJson(value: ArrayList<sparedata>?): String? = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String): ArrayList<sparedata> {
        val listType: Type = object : TypeToken<ArrayList<sparedata?>?>() {}.type
        return gson.fromJson(value, listType)
    }
}
