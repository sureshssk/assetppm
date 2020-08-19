package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Workingstatus(
    val activeStatus: Int,
    val cDate: String,
    val colorCode: String,
    val cuid: Int,
    val mDate: String,
    val muid: Int,
    val version: String,
    val workingStatus: String,
    @PrimaryKey val workingStatusId: Int,
    val workingStatusKey: String
)
{
    override fun toString(): String {
        return workingStatus
    }
}