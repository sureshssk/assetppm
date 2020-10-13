package ssk.dbzeus.ppm.service.model.dao

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.asset.Asset


@Dao
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
interface AssetDao {
    @Query("SELECT * FROM Asset")
    fun getAll(): List<Asset>

    @Query("SELECT * FROM Asset WHERE assetId IN (:assetIds)")
    fun loadAllByIds(assetIds: IntArray): LiveData<List<Asset?>>


    //SELECT * FROM Asset INNER JOIN Assetsmap WHERE Asset.assetId = Assetsmap.assetId AND Assetsmap.facilityId = 1002 AND Assetsmap.buildingId = 1002 AND Assetsmap.floorId = 1004 AND Assetsmap.wingId = 1013
    @Query("SELECT * FROM Asset INNER JOIN Assetsmap WHERE Asset.assetId = Assetsmap.assetId AND Assetsmap.clientId IN (:clientId) AND Assetsmap.facilityId IN (:facilityId) AND Assetsmap.buildingId IN (:buildingId) AND Assetsmap.floorId IN (:floorId)AND Assetsmap.wingId IN (:wingId) AND Assetsmap.departmentId IN (:deptId) ")
    fun loadAssetBySelection(clientId: Int?, facilityId: Int?,buildingId: Int?,floorId: Int?,wingId: Int?,deptId: Int?): List<Asset?>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(asset: List<Asset>)

    @Delete
    fun delete(asset: Asset)
}
