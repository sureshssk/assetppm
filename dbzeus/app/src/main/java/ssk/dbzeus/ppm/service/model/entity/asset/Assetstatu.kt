package ssk.dbzeus.ppm.service.model.asset

data class Assetstatu(
    val activeStatus: Int,
    val assetStatusId: Int,
    val assetStatusKey: String,
    val cDate: String,
    val cuid: Int,
    val mDate: String,
    val muid: Int,
    val version: String
)