package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.asset.Category
import ssk.dbzeus.ppm.service.model.entity.asset.CategoryandLang
import ssk.dbzeus.ppm.service.model.entity.asset.Categorylang


@Dao
interface CategoryDao {
    @Query("SELECT * FROM Category")
    fun getAll(): List<Category>

    @Query("SELECT * FROM Category WHERE clientId IN (:categoryIds)")
    fun loadAllByIds(categoryIds: IntArray): LiveData<List<Category?>>


    @Query("SELECT * FROM Category WHERE clientId IN (:categoryId)")
    fun getClientById(categoryId: Int): Category?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(category: List<Category>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllLang(categoryLang: List<Categorylang>)

    @Query("SELECT * FROM Categorylang INNER JOIN Category WHERE Categorylang.assetCategoryId = Category.assetCategoryId AND clientId IN (:clientId)AND Categorylang.languageId IN (:languageId)")
    fun getCategoryLangByClientId(clientId: Int?, languageId: Int?): List<CategoryandLang>

    @Delete
    fun delete(category: Category)
}
