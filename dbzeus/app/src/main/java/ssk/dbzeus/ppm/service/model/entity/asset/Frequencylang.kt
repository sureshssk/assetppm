package ssk.dbzeus.ppm.service.model.entity.asset

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity
data class Frequencylang(
    var frequencyId: Int,
    @PrimaryKey val frequencyLangId: Int,
    val frequencyLangKey: String,
    val frequencyName: String,
    val frequencyShortName: String,
    val languageId: Int
): Serializable
{
    override fun toString(): String {
        return frequencyName
    }
}