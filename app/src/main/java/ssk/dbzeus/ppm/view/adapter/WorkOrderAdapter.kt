package ssk.dbzeus.ppm.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.asset.Assetworkorder


class WorkOrderAdapter(
    private val workOrderList: ArrayList<Assetworkorder>,
    private val context: Context
) : RecyclerView.Adapter<WorkOrderAdapter.WorkOrderHolder>() {

    override fun onBindViewHolder(woHolder: WorkOrderHolder, index: Int) {
        if (workOrderList[index] != null) {
            val workOrder = workOrderList[index]
            woHolder.textWo.text = workOrder.workOrder
            woHolder.checkWo.setOnCheckedChangeListener { buttonView, isChecked ->
                workOrder.isCompleted = isChecked
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkOrderHolder {
        return WorkOrderHolder(
            LayoutInflater.from(context).inflate(R.layout.workorder_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return workOrderList.size
    }

    inner class WorkOrderHolder(view: View) : RecyclerView.ViewHolder(view) {
        val textWo: TextView = view.findViewById(R.id.textWorkOrder)
        val remarkWo: EditText = view.findViewById(R.id.textRemarks)
        val checkWo: CheckBox = view.findViewById(R.id.checkWorkOrder)
    }

    fun getAssetWorkOrderList(): ArrayList<Assetworkorder> {
        return workOrderList;
    }
}