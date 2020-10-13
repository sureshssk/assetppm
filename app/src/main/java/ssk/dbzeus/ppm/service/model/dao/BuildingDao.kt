package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.asset.Building
import ssk.dbzeus.ppm.service.model.entity.asset.Buildinglang

@Dao
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
interface BuildingDao {
    @Query("SELECT * FROM Building")
    fun getAll(): List<Building>

    @Query("SELECT * FROM Building WHERE buildingId IN (:buildingIds)")
    fun loadAllByIds(buildingIds: IntArray): LiveData<List<Building>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(department: List<Building>)

    @Query("SELECT * FROM Building WHERE facilityId IN (:facilityId)")
    fun getBuildingByFacilityId(facilityId: Int?): List<Building>


    //SELECT * FROM Buildinglang INNER JOIN Building WHERE Buildinglang.buildingId = Building.buildingId AND Building.facilityId = 1002 AND Buildinglang.languageId = 1
   /* @Query("SELECT * FROM Buildinglang INNER JOIN Building WHERE Buildinglang.buildingId = Building.buildingId AND facilityId IN (:facilityId)AND Buildinglang.languageId IN (:languageId)")
    fun getBuildingLangByFacilityId(facilityId: Int?, languageId: Int?): List<Buildinglang>*/

    @Delete
    fun delete(building: Building)
}
