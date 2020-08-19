package ssk.dbzeus.ppm.service.model.entity.weekassets

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class AssetFrequencyDetailModel(
    @SerializedName("afterMaintenanceImagePath") val afterMaintenanceImagePath: String?,
    @SerializedName("assetFrequencyDetailId") val assetFrequencyDetailId: Int?,
    @SerializedName("assetFrequencyDetailKey") val assetFrequencyDetailKey: String?,
    @SerializedName("assetId") val assetId: Int?,
    @SerializedName("assetName") val assetName: String?,
    @SerializedName("assetMaintainTypeId") val assetMaintainTypeId: Int?,
    @SerializedName("attachmentName") val attachmentName: String?,
    @SerializedName("attachmentPath") val attachmentPath: String?,
    @SerializedName("beforeMaintenanceImagePath") val beforeMaintenanceImagePath: String?,
    @SerializedName("buildingId") val buildingId: Int?,
    @SerializedName("clientId") val clientId: Int?,
    @SerializedName("description") val description: String?,
    @SerializedName("facilityId") val facilityId: Int?,
    @SerializedName("floorId") val floorId: Int?,
    @SerializedName("frequencyId") val frequencyId: Int?,
    @SerializedName("maintenanceDate") val maintenanceDate: String?,
    @SerializedName("remark") val remark: String?,
    @SerializedName("spaceId") val spaceId: Int?,
    @SerializedName("wingId") val wingId: Int?,
    @SerializedName("workingStatusId") val workingStatusId: Int?

): Serializable

