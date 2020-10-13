package ssk.dbzeus.ppm.service.model.asset

data class Frequencydetail(
    val activeStatus: Int,
    val afterMaintenanceImagePath: String,
    val assetFrequencyDetailId: Int,
    val assetFrequencyDetailKey: String,
    val assetId: Int,
    val assetMaintainTypeId: Int,
    val attachmentName: Any,
    val attachmentPath: Any,
    val beforeMaintenanceImagePath: String,
    val cDate: String,
    val cuid: Int,
    val description: String,
    val frequencyId: Int,
    val mDate: String,
    val maintenanceDate: String,
    val muid: Int,
    val remark: String,
    val userId: Int,
    val version: String,
    val workingStatusId: Int
)