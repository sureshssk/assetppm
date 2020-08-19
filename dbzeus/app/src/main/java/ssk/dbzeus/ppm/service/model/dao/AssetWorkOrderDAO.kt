package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.asset.Assetworkorder

@Dao
interface AssetWorkOrderDAO {
    @Query("SELECT * FROM Assetworkorder")
    fun getAll(): List<Assetworkorder>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(workOrders: List<Assetworkorder>)

    @Query("SELECT * FROM Assetworkorder WHERE assetId IN  (:assetId)")
    fun getWorkOrder(assetId: Int?): LiveData<List<Assetworkorder>>

    @Delete
    fun delete(workOrder: Assetworkorder)
}
