package ssk.dbzeus.ppm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.asset.Asset



class AssetAdapter(private val assetList: List<Asset?>, private val context: Context) : RecyclerView.Adapter<AssetAdapter.AssetViewHolder>() {

    override fun onBindViewHolder(categoryViewHolder: AssetViewHolder, index: Int) {
        categoryViewHolder.assetNo.text = assetList[index]?.assetNo
        categoryViewHolder.assetName.text = assetList[index]?.assetName
        categoryViewHolder.assetSerial.text = assetList[index]?.serialNo
        categoryViewHolder.assetModel.text = assetList[index]?.model
        categoryViewHolder.itemView.setOnClickListener {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetViewHolder {
        return AssetViewHolder(LayoutInflater.from(context).inflate(R.layout.asset_item, parent, false))
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
}