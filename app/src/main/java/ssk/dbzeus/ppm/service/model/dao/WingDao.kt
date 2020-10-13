package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.asset.Floor
import ssk.dbzeus.ppm.service.model.entity.asset.Wing

@Dao
interface WingDao {
    @Query("SELECT * FROM Wing")
    fun getAll(): List<Wing>

    @Query("SELECT * FROM Wing WHERE floorId IN (:wingIds)")
    fun loadAllByIds(wingIds: IntArray): LiveData<List<Wing>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(wing: List<Wing>)

    @Query("SELECT * FROM Wing WHERE floorId IN (:floorId)")
    fun getWingFloorId(floorId: Int?): List<Wing>

    @Delete
    fun delete(floor: Floor)
}
