package ssk.dbzeus.ppm.service.model.asset

data class Assettype(
    val activeStatus: Int,
    val assetTypeId: Int,
    val assetTypeKey: String,
    val cDate: String,
    val cuid: Int,
    val mDate: String,
    val muid: Int,
    val version: String
)