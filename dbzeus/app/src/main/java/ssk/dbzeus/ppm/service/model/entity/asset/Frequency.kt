package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Frequency(
    val activeStatus: Int,
    val cDate: String,
    val colorCode: String,
    val cuid: Int,
    @PrimaryKey val frequencyId: Int,
    val frequencyKey: String,
    val mDate: String,
    val muid: Int,
    val noofDays: Int,
    val noofMonths: Int,
    val version: String
)