package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Asset(
    @ColumnInfo(name = "activeStatus")val activeStatus: Int?,
    @ColumnInfo(name = "amcAmount")val amcAmount: String?,
    @ColumnInfo(name = "amcTypeId")val amcTypeId: Int?,
    @PrimaryKey val assetId: Int?,
    @ColumnInfo(name = "assetImage")val assetImage: String?,
    @ColumnInfo(name = "assetKey")val assetKey: String?,
    @ColumnInfo(name = "assetName")val assetName: String?,
    @ColumnInfo(name = "assetNo")val assetNo: String?,
    @ColumnInfo(name = "assetStatusId")val assetStatusId: Int?,
    @ColumnInfo(name = "assetTypeId")val assetTypeId: Int?,
    @ColumnInfo(name = "cDate")val cDate: String?,
    @ColumnInfo(name = "cuid")val cuid: Int?,
    @ColumnInfo(name = "endDate")val endDate: String?,
    @ColumnInfo(name = "installationYear")val installationYear: Int?,
    @ColumnInfo(name = "invoiceNo")val invoiceNo: String?,
    @ColumnInfo(name = "mDate")val mDate: String?,
    @ColumnInfo(name = "model")val model: String?,
    @ColumnInfo(name = "muid")val muid: Int?,
    @ColumnInfo(name = "oemId")val oemId: Int?,
    @ColumnInfo(name = "periodMonth")val periodMonth: Int?,
    @ColumnInfo(name = "purchaseDate")val purchaseDate: String?,
    @ColumnInfo(name = "purchaseValue")val purchaseValue: Double?,
    @ColumnInfo(name = "serialNo")val serialNo: String?,
    @ColumnInfo(name = "startDate")val startDate: String?,
    @ColumnInfo(name = "uomId")val uomId: Int?,
    @ColumnInfo(name = "vendorId")val vendorId: Int?,
    @ColumnInfo(name = "version")val version: String?,
    @ColumnInfo(name = "warrantyEndDate")val warrantyEndDate: String?,
    @ColumnInfo(name = "yom")val yom: Int?
)


