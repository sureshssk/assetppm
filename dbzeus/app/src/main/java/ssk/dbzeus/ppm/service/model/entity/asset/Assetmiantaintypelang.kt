package ssk.dbzeus.ppm.service.model.asset

data class Assetmiantaintypelang(
    val assetId: Int,
    val assetMaintainTypeId: Int,
    val assetMaintainTypeMappingId: Int,
    val assetMaintainTypeMappingKey: String,
    val isExist: Boolean
)