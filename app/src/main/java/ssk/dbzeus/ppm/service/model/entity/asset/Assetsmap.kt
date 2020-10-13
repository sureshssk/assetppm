package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.RoomWarnings

@Entity
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
data class Assetsmap(
    @ColumnInfo(name = "activeStatus") val activeStatus: Int?,
    @ColumnInfo(name = "assetCategoryId") val assetCategoryId: Int?,
    @ColumnInfo(name = "assetId") val assetId: Int?,
    @PrimaryKey val assetMapId: Int?,
    @ColumnInfo(name = "assetMapKey") val assetMapKey: String?,
    @ColumnInfo(name = "assetSubCategoryId") val assetSubCategoryId: Int?,
    @ColumnInfo(name = "buildingId") val buildingId: Int?,
    @ColumnInfo(name = "cDate") val cDate: String?,
    @ColumnInfo(name = "clientId") val clientId: Int?,
    @ColumnInfo(name = "cuid") val cuid: Int?,
    @ColumnInfo(name = "departmentId") val departmentId: Int?,
    @ColumnInfo(name = "description") val description: String?,
    @ColumnInfo(name = "facilityId") val facilityId: Int?,
    @ColumnInfo(name = "floorId") val floorId: Int?,
    @ColumnInfo(name = "mDate") val mDate: String?,
    @ColumnInfo(name = "muid") val muid: Int?,
    @ColumnInfo(name = "spaceId") val spaceId: Int?,
    @ColumnInfo(name = "version") val version: String?,
    @ColumnInfo(name = "wingId") val wingId: Int?
)