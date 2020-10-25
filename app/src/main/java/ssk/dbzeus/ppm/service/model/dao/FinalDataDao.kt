package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.insertdata.FinalDBdata

@Dao
interface FinalDataDao {
    @Query("SELECT * FROM FinalDBdata")
    fun getAll(): List<FinalDBdata>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(finalDBdata: List<FinalDBdata>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertSingle(finalDBdata: FinalDBdata)

    @Query("SELECT * FROM FinalDBdata WHERE LogInUserId IN (:LogInUserId)")
    fun getfinalDatabyUserId(LogInUserId: Int): LiveData<List<FinalDBdata>>

    @Delete
    fun delete(finalDBdata: FinalDBdata)
}