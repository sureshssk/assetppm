package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.asset.Floor

@Dao
interface FloorDao {
    @Query("SELECT * FROM Floor")
    fun getAll(): List<Floor>

    @Query("SELECT * FROM Floor WHERE floorId IN (:floorIds)")
    fun loadAllByIds(floorIds: IntArray): LiveData<List<Floor>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(floor: List<Floor>)

    @Query("SELECT * FROM Floor WHERE buildingId IN (:buildingId)")
    fun getFloorbyBuildingId(buildingId: Int?): List<Floor>

    @Delete
    fun delete(floor: Floor)
}
