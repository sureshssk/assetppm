package ssk.dbzeus.ppm.service.model.asset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subcategory(
    val activeStatus: Int,
    val assetCategoryId: Int,
    @PrimaryKey val assetSubCategoryId: Int,
    val assetSubCategoryKey: String,
    val cDate: String,
    val clientId: Int,
    val cuid: Int,
    val mDate: String,
    val muid: Int,
    val version: String
)