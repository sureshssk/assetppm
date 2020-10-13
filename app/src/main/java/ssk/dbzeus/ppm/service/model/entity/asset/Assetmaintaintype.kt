package ssk.dbzeus.ppm.service.model.asset

data class Assetmaintaintype(
    val activeStatus: Int,
    val assetMaintainType: String,
    val assetMaintainTypeId: Int,
    val assetMaintainTypeKey: String,
    val cDate: String,
    val cuid: Int,
    val mDate: String,
    val muid: Int,
    val version: String
)