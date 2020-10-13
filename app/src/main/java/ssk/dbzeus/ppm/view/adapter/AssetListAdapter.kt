package ssk.dbzeus.ppm.view.adapter

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.asset.Frequencylang
import ssk.dbzeus.ppm.service.model.entity.weekassets.AssetFrequencyDetailModel
import ssk.dbzeus.ppm.view.ui.AssetDetails
import ssk.dbzeus.ppm.view.ui.AssetList
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


class AssetListAdapter(
    private val assetList: ArrayList<AssetFrequencyDetailModel>,
    private val freqList: ArrayList<Frequencylang>,
    private val context: Context,
    private val weekNo: String
) : RecyclerView.Adapter<AssetListAdapter.AssetViewHolder>() {

    override fun onBindViewHolder(assetViewHolder: AssetViewHolder, index: Int) {
        if (assetList[index] != null) {
            val assetFM = assetList[index]
            assetViewHolder.assetNo.text = "" + assetFM.assetId.toString()
            assetViewHolder.assetName.text = "" + assetFM.assetName.toString()
            val dateConverted =
                SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").parse(assetFM.maintenanceDate)
            val cal: Calendar = Calendar.getInstance()
            cal.time = dateConverted
            assetViewHolder.assetSerial.text = cal.get(Calendar.DATE).toString() + "/" + (cal.get(
                Calendar.MONTH
            ) + 1) + "/" + cal.get(Calendar.YEAR)
            freqList.forEach { itemFreq ->
                if (itemFreq.frequencyId == assetFM.frequencyId!!) {
                    assetViewHolder.assetModel.text = itemFreq.frequencyName
                }
            }
            assetViewHolder.itemView.setOnClickListener {
                val intent = Intent(context, AssetDetails::class.java)
                intent.putExtra("SelectedAsset", assetFM)
                intent.putExtra("SelectedWeek", weekNo)
                intent.putExtra("FrequencyList", freqList)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        return AssetViewHolder(
            LayoutInflater.from(context).inflate(R.layout.asset_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return assetList.size
    }

    inner class AssetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val assetNo: TextView = view.findViewById(R.id.assetNo)
        val assetName: TextView = view.findViewById(R.id.assetName)
        val assetSerial: TextView = view.findViewById(R.id.assetSerial)
        val assetModel: TextView = view.findViewById(R.id.assetModel)
    }

    fun showByStatus(statusId: Int) {
        Log.i("AdapterSize", "Status:" + statusId + " Size:" + assetList.size)
        this.assetList.filter { it.workingStatusId == statusId }
        Log.i(
            "AdapterSize",
            "Status:" + statusId + " Filtered Size:" + assetList.filter { it.workingStatusId == statusId }.size
        )
        //notifyDataSetChanged()
    }
}