package ssk.dbzeus.ppm.service.model.entity.asset


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class CategoryandLang(
    val activeStatus: Int?,
    val assetCategoryId: Int?,
    val assetCategoryKey: String?,
    val cDate: String?,
    val clientId: Int?,
    val cuid: Int?,
    val mDate: String?,
    val muid: Int?,
    val version: String?,
    @PrimaryKey val assetCategoryLangId: Int?,
    val assetCategoryLangKey: String?,
    val assetCategoryName: String?,
    val assetCategoryShortName: String?,
    val languageId: Int?
)
{
    override fun toString(): String {
        return assetCategoryName.toString()
    }
}