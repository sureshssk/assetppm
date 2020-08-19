package ssk.dbzeus.ppm.service.model.entity.asset


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Buildinglang(
    @ColumnInfo(name = "buildingId") val buildingId: Int?,
    @PrimaryKey val buildingLangId: Int?,
    @ColumnInfo(name = "buildingLangKey")val buildingLangKey: String?,
    @ColumnInfo(name = "buildingName")val buildingName: String?,
    @ColumnInfo(name = "buildingShortName")val buildingShortName: String?,
    @ColumnInfo(name = "languageId")val languageId: Int?
)

{
    override fun toString(): String {
        return buildingName.toString()
    }
}