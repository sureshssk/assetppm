package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.asset.Building
import ssk.dbzeus.ppm.service.model.entity.asset.BuildingandLang
import ssk.dbzeus.ppm.service.model.entity.asset.Buildinglang
import ssk.dbzeus.ppm.service.model.entity.userdata.Department
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility

@Dao
interface BuildingLangDao {
    @Query("SELECT * FROM Buildinglang")
    fun getAll(): List<Buildinglang>

    @Query("SELECT * FROM Buildinglang WHERE buildingId IN (:buildingIds)")
    fun loadAllByIds(buildingIds: IntArray): LiveData<List<Buildinglang>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(buildingLang: List<Buildinglang>)
/*
    @Query("SELECT * FROM Buildinglang WHERE buildingId IN (:buildingId) and languageId IN (:languageId)")
    fun getDepartmentByFacilityId(buildingId: Int?, languageId: Int?): List<Buildinglang>*/

    @Query("SELECT * FROM Buildinglang INNER JOIN Building WHERE Buildinglang.buildingId = Building.buildingId AND facilityId IN (:facilityId)AND Buildinglang.languageId IN (:languageId)")
    fun getBuildingLangByFacilityId(facilityId: Int?, languageId: Int?): List<BuildingandLang>

    @Delete
    fun delete(buildingLang: Buildinglang)
}
