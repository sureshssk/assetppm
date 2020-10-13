package ssk.dbzeus.ppm.service.model.entity.asset


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class BuildingandLang(
    @ColumnInfo(name = "buildingId") val buildingId: Int?,
    @PrimaryKey val buildingLangId: Int?,
    @ColumnInfo(name = "buildingLangKey")val buildingLangKey: String?,
    @ColumnInfo(name = "buildingName")val buildingName: String?,
    @ColumnInfo(name = "buildingShortName")val buildingShortName: String?,
    @ColumnInfo(name = "languageId")val languageId: Int?,
    @ColumnInfo(name = "activeStatus") val activeStatus: Int?,
    @ColumnInfo(name = "buildingKey") val buildingKey: String?,
    @ColumnInfo(name = "cDate") val cDate: String?,
    @ColumnInfo(name = "cuid") val cuid: Int?,
    @ColumnInfo(name = "facilityId") val facilityId: Int?,
    @ColumnInfo(name = "mDate") val mDate: String?,
    @ColumnInfo(name = "muid") val muid: Int?,
    @ColumnInfo(name = "version") val version: String?
)

{
    override fun toString(): String {
        return buildingName.toString()
    }
}