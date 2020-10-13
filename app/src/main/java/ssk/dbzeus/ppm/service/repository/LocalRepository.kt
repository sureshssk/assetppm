package ssk.dbzeus.ppm.service.repository

import androidx.lifecycle.LiveData
import ssk.dbzeus.ppm.service.model.entity.asset.Frequencylang
import ssk.dbzeus.ppm.service.model.dao.*
import ssk.dbzeus.ppm.service.model.entity.asset.Assetworkorder
import ssk.dbzeus.ppm.service.model.entity.asset.Workingstatus
import ssk.dbzeus.ppm.service.model.entity.userdata.Client
import ssk.dbzeus.ppm.service.model.entity.userdata.Department
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility
import ssk.dbzeus.ppm.service.model.entity.userdata.UserInfo
import ssk.dbzeus.ppm.service.model.entity.weekassets.Detail

class LocalRepository {


    fun userData(userDao: UserDao, userName: String, password: String): LiveData<UserInfo> {
        return userDao.checkLogin(userName, password)
    }

    fun weekData(detailDao: DetailDAO): LiveData<List<Detail>> {
        return detailDao.getAll()
    }

    fun clientData(clientDao: ClientDao): LiveData<List<Client>> {
        return clientDao.getAll()
    }
    fun statusData(workingStatusDao: WorkingStatusDao): LiveData<List<Workingstatus>> {
        return workingStatusDao.getAll()
    }

    fun facilityData(facilityDao: FacilityDao, clientId: Int): LiveData<List<Facility>> {
        return facilityDao.getFacilityByClientId(clientId = clientId)
    }

    fun departmentData(deptDao: DepartmentDao, clientId: Int): LiveData<List<Department>> {
        return deptDao.getDepartmentByFacilityId(clientId)
    }

    fun assetData(detailDao: DetailDAO, weekNo: String): LiveData<Detail> {
        return detailDao.loadByWeek(weekNo)
    }
    fun freqData(frequencyDAO: FrequencyDAO, frequencyId: Int, languageId:Int): Frequencylang {
        return frequencyDAO.getFrequencyLangByFrequencyId(frequencyId,languageId)
    }
    fun freqDataAll(frequencyDAO: FrequencyDAO, languageId:Int): LiveData<List<Frequencylang>> {
        return frequencyDAO.getAllLang(languageId)
    }
    fun workOrder(workOrderDAO: AssetWorkOrderDAO, assetId:Int): LiveData<List<Assetworkorder>> {
        return workOrderDAO.getWorkOrder(assetId)
    }

}