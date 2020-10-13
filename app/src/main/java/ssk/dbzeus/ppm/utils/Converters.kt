package ssk.dbzeus.ppm.utils

import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import ssk.dbzeus.ppm.service.model.entity.userdata.MultiLanguageXXX
import java.lang.reflect.Type


object Converters {
    @TypeConverter
    fun fromTimestamp(value: String?): ArrayList<String> {
        val listType: Type = object : TypeToken<ArrayList<String?>?>() {}.type
        return Gson().fromJson(value, listType)
        // return value == null ? null : new Date(value);
    }

    @TypeConverter
    fun arraylistToString(list: ArrayList<String?>?): String {
        val gson = Gson()
        return gson.toJson(list)
        // return date == null ? null : date.getTime();
    }
}