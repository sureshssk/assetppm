package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
/*
@Entity
@ColumnInfo(name = "activeStatus")
@PrimaryKey
*/
data class Assetbreakdownhistory(
    @ColumnInfo(name = "activeStatus") val activeStatus: Int,
    @PrimaryKey val assetBreakdownHistoryId: Int,
    @ColumnInfo(name = "assetBreakdownHistoryKey") val assetBreakdownHistoryKey: String,
    @ColumnInfo(name = "assetId") val assetId: Int,
    @ColumnInfo(name = "breakdownDatetime") val breakdownDatetime: String,
    @ColumnInfo(name = "cDate") val cDate: String,
    @ColumnInfo(name = "cuid") val cuid: Int,
    @ColumnInfo(name = "description") val description: String,
    @ColumnInfo(name = "endDatetime") val endDatetime: Any,
    @ColumnInfo(name = "mDate") val mDate: String,
    @ColumnInfo(name = "muid") val muid: Int,
    @ColumnInfo(name = "reasonId") val reasonId: Int,
    @ColumnInfo(name = "repairedDetail") val repairedDetail: String,
    @ColumnInfo(name = "startDatetime") val startDatetime: Any,
    @ColumnInfo(name = "supplierDetail") val supplierDetail: String,
    @ColumnInfo(name = "vendorId") val vendorId: Int,
    @ColumnInfo(name = "version") val version: String
)