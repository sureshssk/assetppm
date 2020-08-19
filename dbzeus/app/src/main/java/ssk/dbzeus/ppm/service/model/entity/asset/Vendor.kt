package ssk.dbzeus.ppm.service.model.asset

data class Vendor(
    val activeStatus: Int,
    val cDate: String,
    val cityId: Int,
    val contactNumber: String,
    val countryId: Int,
    val cuid: Int,
    val deviceToken: Any,
    val deviceType: Any,
    val emailId: String,
    val mDate: String,
    val muid: Int,
    val stateId: Int,
    val vendorId: Int,
    val vendorKey: String,
    val version: String,
    val zipCode: String
)