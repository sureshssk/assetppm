package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Amclang(
    @ColumnInfo(name = "amcId")val amcId: Int,
    @PrimaryKey val amcLangId: Int,
    @ColumnInfo(name = "amcLangKey")val amcLangKey: String,
    @ColumnInfo(name = "amcName")val amcName: String,
    @ColumnInfo(name = "amcShortName")val amcShortName: String,
    @ColumnInfo(name = "languageId")val languageId: Int
)