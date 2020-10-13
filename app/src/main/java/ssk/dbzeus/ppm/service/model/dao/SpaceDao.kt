package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.asset.Floor
import ssk.dbzeus.ppm.service.model.entity.asset.Space
import ssk.dbzeus.ppm.service.model.entity.asset.Wing

@Dao
interface SpaceDao {
    @Query("SELECT * FROM Space")
    fun getAll(): List<Space>

    @Query("SELECT * FROM Space WHERE spaceId IN (:spaceIds)")
    fun loadAllByIds(spaceIds: IntArray): LiveData<List<Space>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(wing: List<Space>)

    @Query("SELECT * FROM Space WHERE wingId IN (:wingId)")
    fun getWingByFloorId(wingId: Int): List<Space>

    @Delete
    fun delete(space: Space)
}
