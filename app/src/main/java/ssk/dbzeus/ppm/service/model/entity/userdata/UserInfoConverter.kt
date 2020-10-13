package ssk.dbzeus.ppm.service.model.entity.userdata

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type


class UserInfoConverter {

    private var gson = Gson()

    @TypeConverter
    fun listToJson(value: ArrayList<MultiLanguageXXX>?): String? = gson.toJson(value)

    @TypeConverter
    fun jsonToList(value: String): ArrayList<MultiLanguageXXX> {
        val listType: Type = object : TypeToken<ArrayList<MultiLanguageXXX?>?>() {}.type
        return gson.fromJson(value, listType)
    }

}