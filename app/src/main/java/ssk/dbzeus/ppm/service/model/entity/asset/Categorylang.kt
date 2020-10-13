package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Categorylang(
    val assetCategoryId: Int?,
    @PrimaryKey val assetCategoryLangId: Int?,
    val assetCategoryLangKey: String?,
    val assetCategoryName: String?,
    val assetCategoryShortName: String?,
    val languageId: Int?
)