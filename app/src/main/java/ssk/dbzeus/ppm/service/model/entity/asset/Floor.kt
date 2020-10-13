package ssk.dbzeus.ppm.service.model.entity.asset


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Floor(
    val activeStatus: Int?,
    val buildingId: Int?,
    val cDate: String?,
    val cuid: Int?,
    @PrimaryKey val floorId: Int?,
    val floorKey: String?,
    val floorName: String?,
    val floorSqft: String?,
    val mDate: String?,
    val muid: Int?,
    val version: String?
)
{
    override fun toString(): String {
        return floorName.toString()
    }
}