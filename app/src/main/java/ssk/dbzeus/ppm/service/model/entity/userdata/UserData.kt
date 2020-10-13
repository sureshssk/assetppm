package ssk.dbzeus.ppm.service.model.entity.userdata

data class UserData(
    val clients: List<Client>,
    val departments: List<Department>,
    val facilities: List<Facility>,
    val userinfo: UserInfo
)