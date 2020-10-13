package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.userdata.Client


@Dao
interface ClientDao {
    @Query("SELECT * FROM Client")
    fun getAll(): LiveData<List<Client>>

    @Query("SELECT * FROM Client WHERE clientId IN (:clientIds)")
    fun loadAllByIds(clientIds: IntArray): LiveData<List<Client?>>


    @Query("SELECT * FROM Client WHERE clientId IN (:clientId)")
    fun getClientById(clientId: Int): Client?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(client: List<Client>)

    @Delete
    fun delete(client: Client)
}
