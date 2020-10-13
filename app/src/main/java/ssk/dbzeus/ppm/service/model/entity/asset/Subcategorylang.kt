package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subcategorylang(
    val assetSubCategoryId: Int,
    @PrimaryKey val assetSubCategoryLangId: Int,
    val assetSubCategoryLangKey: String,
    val assetSubCategoryName: String,
    val assetSubCategoryShortName: String,
    val languageId: Int
)