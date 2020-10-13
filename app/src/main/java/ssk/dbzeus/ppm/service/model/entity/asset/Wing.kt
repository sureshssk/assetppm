package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Wing(
    val activeStatus: Int?,
    val cDate: String?,
    val cuid: Int?,
    val floorId: Int?,
    val mDate: String?,
    val muid: Int?,
    val version: String?,
    @PrimaryKey val wingId: Int?,
    val wingKey: String?,
    val wingName: String?
)

{
    override fun toString(): String {
        return wingName.toString()
    }
}