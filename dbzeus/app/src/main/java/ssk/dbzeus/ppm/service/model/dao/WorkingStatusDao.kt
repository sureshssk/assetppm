package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ssk.dbzeus.ppm.service.model.entity.asset.Workingstatus

@Dao
interface WorkingStatusDao {
    @Query("SELECT * FROM Workingstatus")
    fun getAll(): LiveData<List<Workingstatus>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(workingStatus: List<Workingstatus>)
}
