package com.bondidos.clevertec_task1.domain.model

import android.util.Log
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.bondidos.clevertec_task1.domain.constants.Const.DISPLAY_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.FAMILY_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.GIVEN_NAME
import java.lang.StringBuilder

@Entity
@TypeConverters(NameConverter::class)
data class ItemModel(
    @PrimaryKey
    val id: String,
    val image: String?,
    val name: Map<String, String?>?,
    val number: String?,
    val email: String?
)

class NameConverter {

    @TypeConverter
    fun fromMapToString(name: Map<String, String>?): String? {
        if (name == null) return null
        val str = StringBuilder()
        str.append("${name[GIVEN_NAME]},")
        str.append("${name[FAMILY_NAME]},")
        str.append("${name[DISPLAY_NAME]}")
        return str.toString()
    }

    @TypeConverter
    fun fromStringToMap(name: String?): Map<String, String?>? {
        if (name == null) return null
        val namesList = name.split(",")
        val result: MutableMap<String, String?> = mutableMapOf()
        Log.d("Main", namesList.toString())

        namesList.forEach {
            result[GIVEN_NAME] = it.removeSuffix(",")
            result[FAMILY_NAME] = it.removeSuffix(",")
            result[DISPLAY_NAME] = it
        }
        return result.toMap()
    }
}