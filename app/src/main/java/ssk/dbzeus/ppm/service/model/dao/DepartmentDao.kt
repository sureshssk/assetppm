package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.userdata.Department
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility

@Dao
interface DepartmentDao {
    @Query("SELECT * FROM Department")
    fun getAll(): List<Department>

    @Query("SELECT * FROM Department WHERE clientId IN (:departmentIds)")
    fun loadAllByIds(departmentIds: IntArray): LiveData<List<Department>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(department: List<Department>)

    @Query("SELECT * FROM Department WHERE clientId IN (:clientId)")
    fun getDepartmentByFacilityId(clientId: Int): LiveData<List<Department>>

    @Delete
    fun delete(client: Department)
}
