package ssk.dbzeus.ppm.service.model.entity.asset


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity

data class Space(
    val activeStatus: Int?,
    val cDate: String?,
    val cuid: Int?,
    val mDate: String?,
    val muid: Int?,
    @PrimaryKey val spaceId: Int?,
    val spaceKey: String?,
    val spaceName: String?,
    val version: String?,
    val wingId: Int?
)