package ssk.dbzeus.ppm.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.weekassets.Detail
import ssk.dbzeus.ppm.view.ui.AssetList


class WeekAdapter(private val weekList: List<Detail?>, private val context: Context) : RecyclerView.Adapter<WeekAdapter.WeekViewHolder>() {

    override fun onBindViewHolder(weekViewHolder: WeekViewHolder, index: Int) {
        weekViewHolder.weekNo.text = "Week-"+weekList[index]?.weekNo
        weekViewHolder.weekCount.text = "Planned-"+weekList[index]?.assetFrequencyDetailModels!!.size
        weekViewHolder.weekDate.text = weekList[index]?.startDateofWeek!!.take(10)//+" - "+weekList[index]?.endDateofWeek!!.take(10)
        weekViewHolder.itemView.setOnClickListener {
            //val intent = Intent(context, AssetList::class.java)
            val intent = Intent(context, AssetList::class.java)
            intent.putExtra("SelectedWeek", weekList[index]?.weekNo)
            context.startActivity(intent)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeekViewHolder {
        return WeekViewHolder(LayoutInflater.from(context).inflate(R.layout.week_item, parent, false))
    }

    override fun getItemCount(): Int {
        return weekList.size
    }

    inner class WeekViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val weekNo: TextView = view.findViewById(R.id.textWeekno)
        val weekDate: TextView = view.findViewById(R.id.textWeekDate)
        val weekCount: TextView = view.findViewById(R.id.textCount)

    }
}