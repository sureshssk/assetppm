package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.userdata.Department
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility
import ssk.dbzeus.ppm.service.model.entity.weekassets.AssetFrequencyDetailModel
import ssk.dbzeus.ppm.service.model.entity.weekassets.Detail

@Dao
interface DetailDAO {
    @Query("SELECT * FROM Detail")
    fun getAll(): LiveData<List<Detail>>

    @Query("SELECT * FROM Detail WHERE weekNo IN (:weekNo)")
    fun loadByWeek(weekNo: String): LiveData<Detail>

   //@Query("SELECT Detail.assetFrequencyDetailModels FROM Detail WHERE weekNo IN (:weekNo)")
   //fun loadAssetByWeek(weekNo: String): List<AssetFrequencyDetailModel>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(detail: List<Detail>)

    @Delete
    fun delete(detail: Detail)
}
