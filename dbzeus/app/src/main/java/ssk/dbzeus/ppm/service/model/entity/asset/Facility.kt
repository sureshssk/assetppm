package ssk.dbzeus.ppm.service.model.asset

data class Facility(
    val activeStatus: Int,
    val cDate: String,
    val cityId: Int,
    val contactNumber: String,
    val cuid: Int,
    val facilityId: Int,
    val facilityKey: String,
    val facilitySqft: String,
    val fourWheelerSlotsUsed: Int,
    val landMark: String,
    val mDate: String,
    val mailId: String,
    val muid: Int,
    val totalFourWheelerSlots: Int,
    val totalTwoWheelerSlots: Int,
    val twoWheelerSlotsUsed: Int,
    val version: String,
    val zipCode: String
)