package ssk.dbzeus.ppm.view.ui

import android.os.Build
import android.os.Bundle
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_asset_details.*
import org.angmarch.views.NiceSpinner
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.asset.Assetworkorder
import ssk.dbzeus.ppm.service.model.entity.asset.Frequencylang
import ssk.dbzeus.ppm.service.model.entity.asset.Workingstatus
import ssk.dbzeus.ppm.service.model.entity.weekassets.AssetFrequencyDetailModel
import ssk.dbzeus.ppm.service.viewmodel.ObjectViewModel
import ssk.dbzeus.ppm.view.adapter.AssetListAdapter
import ssk.dbzeus.ppm.view.adapter.WorkOrderAdapter
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class AssetDetails : BaseActivity() {
    lateinit var textWeek: TextView
    lateinit var textFreq: TextView
    lateinit var textStatusUpdate: TextView
    lateinit var textAssetNo: EditText
    lateinit var textAssetName: EditText
    lateinit var assetStatus: EditText
    lateinit var spinnerStatus: NiceSpinner
    private lateinit var objectViewModel: ObjectViewModel
    private lateinit var freqList: ArrayList<Frequencylang>
    private lateinit var statusList: ArrayList<Workingstatus>

    private lateinit var recyclerWorkOrder: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset_details)
        initToolbar()
        setTitle("Asset Details")
        val bundle: Bundle? = intent.extras
        //val userInfo = bundle!!.getSerializable("UserInfo") as? UserInfo
        val selectedAsset = bundle!!.getSerializable("SelectedAsset") as AssetFrequencyDetailModel
        val selectedWeek = bundle!!.getString("SelectedWeek").toString()
        freqList = bundle!!.getSerializable("FrequencyList") as ArrayList<Frequencylang>

        textWeek = findViewById(R.id.textViewWeek)
        textFreq = findViewById(R.id.textViewFreq)
        textAssetNo = findViewById(R.id.assetNo)
        textAssetName = findViewById(R.id.assetName)
        assetStatus = findViewById(R.id.assetCurrentStatus)
        textStatusUpdate = findViewById(R.id.textStatusUpdate)
        spinnerStatus = findViewById(R.id.spinnerStatus)

        recyclerWorkOrder = findViewById(R.id.recyclerWorkOrder)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerWorkOrder.layoutManager = layoutManager

        textAssetNo.isEnabled = false
        textAssetName.isEnabled = false
        assetStatus.isEnabled = false

        textWeek.text = "Week - $selectedWeek"
        val dateConverted =
            SimpleDateFormat("MM/dd/yyyy hh:mm:ss a").parse(selectedAsset.maintenanceDate)
        val cal: Calendar = Calendar.getInstance()
        cal.time = dateConverted
        textFreq.text =
            cal.get(Calendar.DATE).toString() + "/" + (cal.get(Calendar.MONTH) + 1) + "/" + cal.get(
                Calendar.YEAR
            )
        textAssetNo.setText(selectedAsset.assetId.toString())
        textAssetName.setText(selectedAsset.assetName)
        assetStatus.setText(selectedAsset.assetId.toString())

        objectViewModel = ViewModelProvider(this).get(ObjectViewModel::class.java)

        freqList.forEach { t: Frequencylang? ->
            if (t != null) {
                if (t.frequencyId == selectedAsset.frequencyId) textViewFreq.text =
                    textFreq.text as String + " - " + t.frequencyName
            }
        }


        objectViewModel.allStatus.observe(this, Observer { statList ->
            statList?.let { it ->
                statusList = statList as ArrayList<Workingstatus>
                for (workingStatus in it) {
                    if (workingStatus?.workingStatusId == selectedAsset.workingStatusId)
                        assetStatus.setText(workingStatus.workingStatus)
                }
                statusList.filter { it.workingStatusId == selectedAsset.workingStatusId}
                spinnerStatus.attachDataSource(statusList)
            }
        })
        objectViewModel.getAllWorkOrder(selectedAsset.assetId!!,this).observe(this, Observer { woList ->
            woList?.let { it ->
                val workOrderAdapter = WorkOrderAdapter(it as ArrayList<Assetworkorder>,this)
                recyclerWorkOrder.adapter = workOrderAdapter
            }
        })

    }
}
