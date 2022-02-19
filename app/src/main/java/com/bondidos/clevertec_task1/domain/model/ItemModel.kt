package com.bondidos.clevertec_task1.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.bondidos.clevertec_task1.domain.constants.Const.DISPLAY_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.FAMILY_NAME
import com.bondidos.clevertec_task1.domain.constants.Const.GIVEN_NAME

@Entity
@TypeConverters(NameConverter::class)
data class ItemModel(
    @PrimaryKey
    val id: String,
    val image: String?,
    val name: Map<String,String?>?,
    val number: String?,
    val email: String?
)

class NameConverter{

    @TypeConverter
    fun fromMapToString(name: Map<String,String>?): String?{
        if(name == null) return null
        return name.toString()
    }

    @TypeConverter
    fun fromStringToMap(name: String?): Map<String,String>?{
        if(name == null) return null
        val namesList = name.split(",")
        return mapOf(
            GIVEN_NAME to namesList.first().removeSuffix("$GIVEN_NAME="),
            FAMILY_NAME to namesList.first().removeSuffix("$FAMILY_NAME="),
            DISPLAY_NAME to namesList.first().removeSuffix("$DISPLAY_NAME=")
        )
    }
}