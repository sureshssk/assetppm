package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Building(
    @ColumnInfo(name = "activeStatus") val activeStatus: Int?,
    @PrimaryKey val buildingId: Int?,
    @ColumnInfo(name = "buildingKey") val buildingKey: String?,
    @ColumnInfo(name = "cDate") val cDate: String?,
    @ColumnInfo(name = "cuid") val cuid: Int?,
    @ColumnInfo(name = "facilityId") val facilityId: Int?,
    @ColumnInfo(name = "mDate") val mDate: String?,
    @ColumnInfo(name = "muid") val muid: Int?,
    @ColumnInfo(name = "version") val version: String?
)
