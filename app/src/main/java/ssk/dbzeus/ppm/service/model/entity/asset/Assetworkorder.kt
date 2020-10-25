package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Assetworkorder(
    val activeStatus: Int,
    val assetId: Int,
    val assetMaintainTypeId: Int,
    @PrimaryKey val assetWorkOrderId: Int,
    val assetWorkOrderKey: String,
    val cDate: String,
    val cuId: Int,
    val frequencyId: Int,
    val mDate: String,
    val muid: Int,
    val version: String,
    val workOrder: String
) : Serializable {
    var isCompleted: Boolean = false
}