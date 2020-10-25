package ssk.dbzeus.ppm.service.model.entity.insertdata

import androidx.room.ColumnInfo
import androidx.room.Entity

@Entity
data class WorkorderListItem(
    @ColumnInfo(name = "AssetWorkOrderId") val AssetWorkOrderId: String,
    @ColumnInfo(name = "IsCompleted") val IsCompleted: String
)