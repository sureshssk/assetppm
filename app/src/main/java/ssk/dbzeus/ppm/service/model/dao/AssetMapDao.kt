package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.asset.Asset
import ssk.dbzeus.ppm.service.model.entity.asset.Assetsmap


@Dao
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
interface AssetMapDao {
    @Query("SELECT * FROM Assetsmap")
    fun getAll(): List<Assetsmap>

    @Query("SELECT * FROM Assetsmap WHERE assetMapId IN (:assetMapIds)")
    fun loadAllByIds(assetMapIds: IntArray): LiveData<List<Assetsmap?>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(asset: List<Assetsmap>)

    @Delete
    fun delete(asset: Assetsmap)
}
