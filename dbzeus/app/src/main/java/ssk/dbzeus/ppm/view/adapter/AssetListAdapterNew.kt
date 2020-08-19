package ssk.dbzeus.ppm.view.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.weekassets.AssetFrequencyDetailModel


class AssetListAdapterNew(private val assetList: ArrayList<String>, private val context: Context) : RecyclerView.Adapter<AssetListAdapterNew.AssetViewHolder>() {

    override fun onBindViewHolder(assetViewHolder: AssetViewHolder, index: Int) {
        if(assetList[index] != null) {
            val assetFM = assetList[index]
            Log.i("AdapterData", "Data:$assetFM")
            assetViewHolder.assetNo.text = ""+index
            assetViewHolder.assetName.text = "" + assetFM
        }
        //assetViewHolder.assetSerial.text = ""+assetFM.maintenanceDate
        //categoryViewHolder.assetModel.text = assetList[index]?.model
        /*categoryViewHolder.itemView.setOnClickListener {

        }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        return AssetViewHolder(LayoutInflater.from(context).inflate(R.layout.asset_item, parent, false))
    }

    override fun getItemCount(): Int {
        Log.i("AdapterSize", "Size:"+assetList.size)
        return assetList.size
    }

    inner class AssetViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val assetNo: TextView = view.findViewById(R.id.assetNo)
        val assetName: TextView = view.findViewById(R.id.assetName)
        val assetSerial: TextView = view.findViewById(R.id.assetSerial)
        val assetModel: TextView = view.findViewById(R.id.assetModel)

    }
}