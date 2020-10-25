package ssk.dbzeus.ppm.view.ui

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_asset_details.*
import org.angmarch.views.NiceSpinner
import ssk.dbzeus.ppm.AppDb
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.asset.Assetworkorder
import ssk.dbzeus.ppm.service.model.entity.asset.Frequencylang
import ssk.dbzeus.ppm.service.model.entity.asset.Workingstatus
import ssk.dbzeus.ppm.service.model.entity.insertdata.*
import ssk.dbzeus.ppm.service.model.entity.userdata.UserInfo
import ssk.dbzeus.ppm.service.model.entity.weekassets.AssetFrequencyDetailModel
import ssk.dbzeus.ppm.service.viewmodel.ObjectViewModel
import ssk.dbzeus.ppm.utils.Utils
import ssk.dbzeus.ppm.view.adapter.WorkOrderAdapter
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList


open class AssetDetails : BaseActivity() {

    private var loginUserID: Int = 0
    lateinit var textWeek: TextView
    lateinit var textFreq: TextView
    lateinit var textStatusUpdate: TextView
    lateinit var textAssetNo: EditText
    lateinit var textAssetName: EditText
    lateinit var submitButton: Button
    private lateinit var assetStatus: EditText
    lateinit var buttonBreakdown: Button
    lateinit var buttonBeforeImage: ImageView
    lateinit var buttonAfterImage: ImageView
    lateinit var Sigimgview: ImageView
    lateinit var llSignature: LinearLayout
    lateinit var spinnerStatus: NiceSpinner
    private lateinit var objectViewModel: ObjectViewModel

    private lateinit var recyclerWorkOrder: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var selectedAsset: AssetFrequencyDetailModel
    private lateinit var selectedWeek: String
    private lateinit var freqList: ArrayList<Frequencylang>
    private lateinit var statusList: ArrayList<Workingstatus>
    private val IMAGE_SIGNATURE = 700
    private var SigantureString: String? = null
    private lateinit var workOrderAdapter: WorkOrderAdapter

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset_details)
        initToolbar()
        setTitle("Asset Details")
        val bundle: Bundle? = intent.extras
        val userInfo = bundle!!.getSerializable("UserInfo") as? UserInfo
        selectedAsset = bundle!!.getSerializable("SelectedAsset") as AssetFrequencyDetailModel
        selectedWeek = bundle!!.getString("SelectedWeek").toString()
        freqList = bundle!!.getSerializable("FrequencyList") as ArrayList<Frequencylang>

        textWeek = findViewById(R.id.textViewWeek)
        textFreq = findViewById(R.id.textViewFreq)
        textAssetNo = findViewById(R.id.assetNo)
        textAssetName = findViewById(R.id.assetName)
        assetStatus = findViewById(R.id.assetCurrentStatus)
        textStatusUpdate = findViewById(R.id.textStatusUpdate)
        spinnerStatus = findViewById(R.id.spinnerStatus)
        buttonBreakdown = findViewById(R.id.buttonBreakdown)
        buttonBeforeImage = findViewById(R.id.beforeImage)
        buttonAfterImage = findViewById(R.id.afterImage)
        Sigimgview = findViewById(R.id.Sigimgview)
        llSignature = findViewById(R.id.llSignature)
        submitButton = findViewById(R.id.buttonSubmit)

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
                statusList.filter { it.workingStatusId == selectedAsset.workingStatusId }
                spinnerStatus.attachDataSource(statusList)
            }
        })
        objectViewModel.getAllWorkOrder(selectedAsset.assetId!!, this)
            .observe(this, Observer { woList ->
                woList?.let { it ->
                    workOrderAdapter = WorkOrderAdapter(it as ArrayList<Assetworkorder>, this)
                    recyclerWorkOrder.adapter = workOrderAdapter
                }
            })
        buttonBreakdown.setOnClickListener {
            val intent = Intent(this, Breakdown::class.java)
            intent.putExtra("SelectedAsset", selectedAsset)
            intent.putExtra("SelectedWeek", selectedWeek)
            intent.putExtra("FrequencyList", freqList)
            startActivity(intent)
        }
        buttonBeforeImage.setOnClickListener {

        }
        buttonAfterImage.setOnClickListener {

        }
        llSignature.setOnClickListener {
            Utils.newIntentWithResult(
                this,
                SignatureActivity::class.java,
                Bundle.EMPTY,
                IMAGE_SIGNATURE
            )
        }
        submitButton.setOnClickListener {
            val sharedPreferences = getSharedPreferences("ASSETBBM", Context.MODE_PRIVATE)
            loginUserID = sharedPreferences.getInt("USERID", 0)
            workOrderAdapter.getAssetWorkOrderList();
            var arrayListItem: ArrayList<WorkorderListItem> = ArrayList()
            var spareListitem: ArrayList<sparedataItem> = ArrayList()
            for (assetWorkOrder in workOrderAdapter.getAssetWorkOrderList()) {

                val workorderListItem = WorkorderListItem(
                    assetWorkOrder.assetWorkOrderId.toString(),
                    assetWorkOrder.isCompleted.toString()
                )
                arrayListItem.add(workorderListItem)
            }
            val workorderList = WorkorderList(arrayListItem)
            val spareDataList = sparedata(spareListitem)
            val finalData = FinalDBdata(
                selectedAsset.assetFrequencyDetailId!!,
                selectedAsset.assetFrequencyDetailKey!!,
                "",
                "",
                loginUserID,
                "",
                "",
                "",
                workorderList,
                spareDataList,
                ""
            )
            AsyncTask.execute {
                AppDb.getInstance(this).finalDataDao().insertSingle(
                    finalData
                )
            }
        }


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            IMAGE_SIGNATURE -> if (resultCode == RESULT_OK) {
                val image_path = data!!.getStringExtra("imagePath")
                val imgFile = File(image_path)
                if (imgFile.exists()) {
                    val myBitmap = BitmapFactory.decodeFile(imgFile.absolutePath)
                    Sigimgview.setImageBitmap(myBitmap)
                    val imgSigntureba64 = Utils.bitmaptoBase(myBitmap)
                    SigantureString = image_path
                }
            }


        }
    }
}
