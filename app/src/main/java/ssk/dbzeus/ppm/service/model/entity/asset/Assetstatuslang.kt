package ssk.dbzeus.ppm.service.model.asset

data class Assetstatuslang(
    val assetStatusId: Int,
    val assetStatusLangId: Int,
    val assetStatusLangKey: String,
    val assetStatusName: String,
    val assetStatusShortName: String,
    val languageId: Int
)