package ssk.dbzeus.ppm.service.model.entity.userdata

import androidx.annotation.Nullable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import java.io.Serializable


@Entity
@TypeConverters(UserInfoConverter::class)
data class UserInfo(
    @ColumnInfo(name = "activeStatus")
    @Nullable val activeStatus: Int?,
    @ColumnInfo(name = "buildingId")
    @Nullable val buildingId: Int?,
    @ColumnInfo(name = "cityId")
    @Nullable val cityId: Int?,
    @ColumnInfo(name = "cityName")
    @Nullable val cityName: String?,
    @ColumnInfo(name = "clientGroupId")
    @Nullable val clientGroupId: Int?,
    @ColumnInfo(name = "clientId")
    @Nullable val clientId: Int,
    @ColumnInfo(name = "clientName")
    @Nullable val clientName: String?,
    @ColumnInfo(name = "clientRoleId")
    @Nullable val clientRoleId: Int?,
    @ColumnInfo(name = "contactNumber")
    @Nullable val contactNumber: String?,
    @ColumnInfo(name = "countryId")
    @Nullable val countryId: Int?,
    @ColumnInfo(name = "countryName")
    @Nullable val countryName: String?,
    @ColumnInfo(name = "designation")
    @Nullable val designation: String?,
    @ColumnInfo(name = "deviceToken")
    @Nullable val deviceToken: String?,
    @ColumnInfo(name = "deviceType")
    @Nullable val deviceType: String?,
    @ColumnInfo(name = "facilityId")
    @Nullable val facilityId: Int,
    @ColumnInfo(name = "floorId")
    @Nullable val floorId: Int?,
    @ColumnInfo(name = "multiLanguages")
    @Nullable val multiLanguages: ArrayList<MultiLanguageXXX>?,
    @ColumnInfo(name = "password")
    @Nullable val password: String?,
    @ColumnInfo(name = "personalEmail")
    @Nullable val personalEmail: String?,
    @ColumnInfo(name = "photoPath")
    @Nullable val photoPath: String?,
    @ColumnInfo(name = "spaceId")
    @Nullable val spaceId: Int?,
    @ColumnInfo(name = "stateId")
    @Nullable val stateId: Int?,
    @ColumnInfo(name = "stateName")
    @Nullable val stateName: String?,
    @ColumnInfo(name = "tenantId")
    @Nullable val tenantId: Int?,
    @ColumnInfo(name = "userEmail")
    @Nullable val userEmail: String?,

    @PrimaryKey val userId: Int,

    @ColumnInfo(name = "userKey")
    @Nullable val userKey: String?,
    @ColumnInfo(name = "userName")
    @Nullable val userName: String?,
    @ColumnInfo(name = "wingId")
    @Nullable val wingId: Int?,
    @ColumnInfo(name = "zipCode")
    @Nullable val zipCode: String?
): Serializable


