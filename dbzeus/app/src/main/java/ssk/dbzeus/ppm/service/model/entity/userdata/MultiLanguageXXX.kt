package ssk.dbzeus.ppm.service.model.entity.userdata

import java.io.Serializable


data class MultiLanguageXXX(
    val addressText: String,
    val firstName: String,
    val itemName: String,
    val itemShortName: String,
    val languageId: Int,
    val lastName: String,
    val middleName: String
):Serializable