package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.asset.Frequencylang
import ssk.dbzeus.ppm.service.model.entity.asset.Frequency

@Dao
interface FrequencyDAO {
    @Query("SELECT * FROM Frequency")
    fun getAll(): List<Frequency>

    @Query("SELECT * FROM Frequencylang WHERE Frequencylang.languageId IN (:languageId)")
    fun getAllLang( languageId: Int ): LiveData<List<Frequencylang>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(frequencies: List<Frequency>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllfreqLang(frequencies: List<Frequencylang>)

    @Query("SELECT * FROM Frequencylang WHERE Frequencylang.frequencyId  IN ( :frequencyId) AND Frequencylang.languageId IN (:languageId)")
    fun getFrequencyLangByFrequencyId(frequencyId: Int, languageId: Int): Frequencylang

    @Delete
    fun delete(frequency: Frequency)
}