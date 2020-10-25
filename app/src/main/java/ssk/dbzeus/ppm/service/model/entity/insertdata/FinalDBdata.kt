package ssk.dbzeus.ppm.service.model.entity.insertdata

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FinalDBdata(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "AssetFrequencyDetailId") val AssetFrequencyDetailId: Int,
    @ColumnInfo(name = "AssetFrequencyDetailKey") val AssetFrequencyDetailKey: String,
    @ColumnInfo(name = "Description") val Description: String,
    @ColumnInfo(name = "Remark") val Remark: String,
    @ColumnInfo(name = "LogInUserId") val LogInUserId: Int,
    @ColumnInfo(name = "AssetBeforeMaintenanceImage") val AssetBeforeMaintenanceImage: String,
    @ColumnInfo(name = "AssetAfterMaintenanceImage") val AssetAfterMaintenanceImage: String,
    @ColumnInfo(name = "AttachmentImage") val AttachmentImage: String,
    @ColumnInfo(name = "WorkOrderList") val WorkOrderList: WorkorderList,
    @ColumnInfo(name = "SpareList") val SpareList: sparedata,
    @ColumnInfo(name = "AttachementName") val AttachementName: String,
)



