package ssk.dbzeus.ppm.view.ui

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.asset.Frequencylang
import ssk.dbzeus.ppm.service.model.entity.weekassets.AssetFrequencyDetailModel
import ssk.dbzeus.ppm.service.viewmodel.ObjectViewModel
import ssk.dbzeus.ppm.view.adapter.AssetListAdapter

class AssetList : BaseActivity() {
    private var selectedWeek = ""
    private var selectedClientId = 0
    private var selectedFacilityId = 0
    private var selectedDepartmentId = 0
    private var selectedBuildingId = 0
    private var selectedFloorId = 0
    private var selectedWingId = 0
    private var selectedCategoryId = 0
    private var selectedSubCategoryId = 0
    private lateinit var recyclerAsset: RecyclerView
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var objectViewModel: ObjectViewModel
    private lateinit var radioGroupStatus: RadioGroup
    private lateinit var recyclerAssetAdapter: AssetListAdapter
    private lateinit var assetCompleteList: ArrayList<AssetFrequencyDetailModel>
    private lateinit var freqList: ArrayList<Frequencylang>

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_asset_list)
        initToolbar()
        setTitle("Asset List")
        var bundle: Bundle? = intent.extras
        selectedWeek = bundle!!.getString("SelectedWeek").toString()
        /*
        selectedClientId = bundle!!.getInt("ClientID")
        selectedFacilityId = bundle!!.getInt("FacilityID")
        selectedDepartmentId = bundle!!.getInt("DepartmentID")
        selectedBuildingId = bundle!!.getInt("BuildingID")
        selectedFloorId = bundle!!.getInt("FloorID")
        selectedWingId = bundle!!.getInt("WingID")*/

        recyclerAsset = findViewById(R.id.recyclerAsset)
        radioGroupStatus = findViewById(R.id.rgStatus)
        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerAsset.layoutManager = layoutManager

        objectViewModel = ViewModelProvider(this).get(ObjectViewModel::class.java)

        objectViewModel.getAssetByWeek(selectedWeek, application)
            .observe(this@AssetList, Observer { assetList ->
                assetList?.let {
                    val dataList = it.assetFrequencyDetailModels
                    assetCompleteList = it.assetFrequencyDetailModels
                    val openCount = it.openCount
                    val onholdCount = it.onHoldCount
                    val closedCount = it.completedCount

                    Log.i("AssetList", "assetFreq:$dataList")
                    objectViewModel.getAllFrequency(1, application)
                        .observe(this@AssetList, Observer { freList ->
                            freList.let {
                                freqList = freList as ArrayList<Frequencylang>
                                recyclerAssetAdapter = AssetListAdapter(
                                    dataList,
                                    freqList, this@AssetList,
                                    selectedWeek
                                )
                                recyclerAsset.adapter = recyclerAssetAdapter
                            }
                        })

                    objectViewModel.allStatus.observe(this, Observer { statusList ->
                        statusList?.let {
                            for (workingStatus in it) {
                                val statusButton = RadioButton(this)
                                statusButton.layoutParams = LinearLayout.LayoutParams(
                                    ViewGroup.LayoutParams.WRAP_CONTENT,
                                    ViewGroup.LayoutParams.WRAP_CONTENT
                                )
                                val marginParams = RadioGroup.LayoutParams(baseContext, null)
                                marginParams.setMargins(10, 0, 10, 0)
                                statusButton.layoutParams = marginParams
                                if(workingStatus?.workingStatus.contains("Open")){
                                    statusButton.text = workingStatus?.workingStatus+"("+openCount+")"
                                }
                                if(workingStatus?.workingStatus.contains("Hold")){
                                    statusButton.text = workingStatus?.workingStatus+"("+onholdCount+")"
                                }
                                if(workingStatus?.workingStatus.contains("Completed")||workingStatus?.workingStatus.contains("closed")){
                                    statusButton.text = workingStatus?.workingStatus+"("+closedCount+")"
                                }

                                statusButton.setTextColor(
                                    ContextCompat.getColorStateList(
                                        this@AssetList,
                                        R.color.tab_color
                                    )
                                )
                                statusButton.gravity = Gravity.CENTER
                                statusButton.setPadding(25, 0, 25, 0)
                                statusButton.background = getDrawable(R.drawable.bg_radiobutton)
                                statusButton.buttonDrawable = null
                                statusButton.id = workingStatus.workingStatusId
                                radioGroupStatus.addView(statusButton)
                            }
                        }
                    })
                }
            })

        radioGroupStatus.setOnCheckedChangeListener { _, checkedId ->
            recyclerAssetAdapter = AssetListAdapter(
                assetCompleteList.filter { it.workingStatusId == checkedId} as ArrayList<AssetFrequencyDetailModel>,
                freqList, this@AssetList,selectedWeek
            )
            recyclerAsset.adapter = recyclerAssetAdapter
        }

    }
}