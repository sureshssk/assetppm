package ssk.dbzeus.ppm.service.model.asset

data class Frequnecymaintanance(
    val assetFrequencyDetailId: Int,
    val assetFrequencyMaintenanceId: Int,
    val assetFrequencyMaintenanceKey: String,
    val assetWorkOrderId: Int,
    val cDate: String,
    val cuid: Int,
    val isComplete: Boolean,
    val mDate: String,
    val muid: Int,
    val version: String
)