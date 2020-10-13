package ssk.dbzeus.ppm.service.model.entity.weekassets

data class WeekAssets(
    val details: List<Detail>,
    val httpcode: String,
    val message: String,
    val status: Boolean
)