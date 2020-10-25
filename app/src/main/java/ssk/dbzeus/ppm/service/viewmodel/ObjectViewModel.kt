package ssk.dbzeus.ppm.service.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import ssk.dbzeus.ppm.AppDb
import ssk.dbzeus.ppm.service.model.entity.asset.Assetworkorder
import ssk.dbzeus.ppm.service.model.entity.asset.Frequencylang
import ssk.dbzeus.ppm.service.model.entity.asset.Workingstatus
import ssk.dbzeus.ppm.service.model.entity.insertdata.FinalDBdata
import ssk.dbzeus.ppm.service.model.entity.userdata.Client
import ssk.dbzeus.ppm.service.model.entity.userdata.Department
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility
import ssk.dbzeus.ppm.service.model.entity.userdata.UserInfo
import ssk.dbzeus.ppm.service.model.entity.weekassets.Detail
import ssk.dbzeus.ppm.service.repository.LocalRepository


class ObjectViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: LocalRepository
    // Using LiveData and caching what getAlphabetizedWords returns has several benefits:
    // - We can put an observer on the data (instead of polling for changes) and only update the
    //   the UI when the data actually changes.
    // - Repository is completely separated from the UI through the ViewModel.
    val allWeeks: LiveData<List<Detail>>
    val allClient: LiveData<List<Client>>
    val allStatus: LiveData<List<Workingstatus>>

    init {
        val weekDAO = AppDb.getInstance(application, viewModelScope).detailDao()
        val clientDAO = AppDb.getInstance(application, viewModelScope).clientDao()
        val workingStatusDao = AppDb.getInstance(application, viewModelScope).workingStatusDao()
        //repository = LocalRepository(wordsDao)
        repository = LocalRepository()
        allWeeks = repository.weekData(weekDAO)
        allClient = repository.clientData(clientDAO)
        allStatus = repository.statusData(workingStatusDao)
    }

    fun getFacilityData(clientId :Int,application: Application) :LiveData<List<Facility>>{
        val facilityDAO = AppDb.getInstance(application, viewModelScope).facilityDao()
        return repository.facilityData(facilityDAO, clientId)
    }
    fun getFinalData(LogInUserId :Int,application: Application) :LiveData<List<FinalDBdata>>{
        val finalDataDao = AppDb.getInstance(application, viewModelScope).finalDataDao()
        return repository.finalData(finalDataDao, LogInUserId)
    }
    fun getDepartmentData(clientId :Int,application: Application) :LiveData<List<Department>>{
        val deptDAO = AppDb.getInstance(application, viewModelScope).departmentDao()
        return repository.departmentData(deptDAO, clientId)
    }
    fun getAssetByWeek(weekNo :String,application: Application) :LiveData<Detail>{
        val weekDAO = AppDb.getInstance(application, viewModelScope).detailDao()
        return repository.assetData(weekDAO,weekNo)
    }
    fun getUserInfo(username:String, password:String,application: Application) :LiveData<UserInfo>{
        val userDAO = AppDb.getInstance(application, viewModelScope).userDao()
        return repository.userData(userDAO,username,password)
    }
    fun getFrequency(frequencyId:Int,languageId:Int, context: Context) : Frequencylang {
        val frequencyDAO = AppDb.getInstance(context, viewModelScope).frequencyDao()
        return repository.freqData(frequencyDAO,frequencyId,languageId)
    }
    fun getAllFrequency(languageId:Int, context: Context) : LiveData<List<Frequencylang>> {
        val frequencyDAO = AppDb.getInstance(context, viewModelScope).frequencyDao()
        return repository.freqDataAll(frequencyDAO,languageId)
    }
    fun getAllWorkOrder(assetId:Int, context: Context) : LiveData<List<Assetworkorder>> {
        val workOrderDAO = AppDb.getInstance(context, viewModelScope).workOrderDao()
        return repository.workOrder(workOrderDAO,assetId)
    }
}

