package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class SubcategoryandLang(
    val activeStatus: Int,
    val assetCategoryId: Int,
    @PrimaryKey val assetSubCategoryId: Int,
    val assetSubCategoryKey: String,
    val cDate: String,
    val clientId: Int,
    val cuid: Int,
    val mDate: String,
    val muid: Int,
    val version: String,
    val assetSubCategoryLangId: Int,
    val assetSubCategoryLangKey: String,
    val assetSubCategoryName: String,
    val assetSubCategoryShortName: String,
    val languageId: Int
)

{
    override fun toString(): String {
        return assetSubCategoryName
    }
}