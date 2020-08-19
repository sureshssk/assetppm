package ssk.dbzeus.ppm.service.model.asset


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Category(
    val activeStatus: Int?,
    @PrimaryKey val assetCategoryId: Int?,
    val assetCategoryKey: String?,
    val cDate: String?,
    val clientId: Int?,
    val cuid: Int?,
    val mDate: String?,
    val muid: Int?,
    val version: String?
)