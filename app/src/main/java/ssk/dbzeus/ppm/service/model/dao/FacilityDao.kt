package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.userdata.Client
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility

@Dao
interface FacilityDao {
    @Query("SELECT * FROM Facility")
    fun getAll(): List<Facility>

    @Query("SELECT * FROM Facility WHERE facilityId IN (:facilityIds)")
    fun loadAllByIds(facilityIds: IntArray): LiveData<List<Facility>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(facilities: List<Facility>)

    @Query("SELECT * FROM Facility WHERE clientId IN (:clientId)")
    fun getFacilityByClientId(clientId: Int): LiveData<List<Facility>>

    @Query("SELECT * FROM Facility WHERE facilityId IN (:facilityId)")
    fun getFacilityById(facilityId: Int): Facility?

    @Delete
    fun delete(facility: Facility)
}