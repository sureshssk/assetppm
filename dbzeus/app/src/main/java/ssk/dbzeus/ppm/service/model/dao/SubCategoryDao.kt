package ssk.dbzeus.ppm.service.model.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import ssk.dbzeus.ppm.service.model.asset.*
import ssk.dbzeus.ppm.service.model.entity.asset.SubcategoryandLang
import ssk.dbzeus.ppm.service.model.entity.asset.Subcategorylang


@Dao
interface SubCategoryDao {
    @Query("SELECT * FROM Subcategory")
    fun getAll(): List<Subcategory>

    @Query("SELECT * FROM Subcategory WHERE clientId IN (:categoryIds)")
    fun loadAllByIds(categoryIds: IntArray): LiveData<List<Subcategory?>>


    @Query("SELECT * FROM Subcategory WHERE clientId IN (:categoryId)")
    fun getClientById(categoryId: Int): Subcategory?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAll(category: List<Subcategory>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    @JvmSuppressWildcards
    fun insertAllLang(subcategoryLang: List<Subcategorylang>)



    @Query("SELECT * FROM Subcategorylang INNER JOIN Subcategory WHERE Subcategorylang.assetSubCategoryId = Subcategory.assetSubCategoryId AND assetCategoryId IN (:categoryId)AND Subcategorylang.languageId IN (:languageId)")
    fun getSubCategoryLangByCategoryId(categoryId: Int?, languageId: Int?): List<SubcategoryandLang>

    @Delete
    fun delete(category: Subcategory)
}
