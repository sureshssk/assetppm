package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.entity.userdata.UserInfo

@Dao
interface UserDao {
    @Query("SELECT * FROM UserInfo")
    fun getAll(): List<UserInfo>

    @Query("SELECT * FROM UserInfo WHERE userId IN (:userIds)")
    fun loadAllByIds(userIds: IntArray): List<UserInfo>


    @Query("SELECT * FROM UserInfo WHERE userName IN (:userName) AND passWord IN (:passWord)")
    fun checkLogin(userName: String, passWord: String): LiveData<UserInfo>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: UserInfo)

    @Delete
    fun delete(user: UserInfo)
}