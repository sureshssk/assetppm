package ssk.dbzeus.ppm.view.ui

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_select_client.*
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.userdata.Client
import ssk.dbzeus.ppm.service.model.entity.userdata.Department
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility
import ssk.dbzeus.ppm.service.viewmodel.ObjectViewModel


class SelectClient : BaseActivity() {
    var selectedClientId = 0
    var selectedFacilityId = 0
    var selectedDepartmentId = 0
    var selectedBuildingId = 0
    var selectedFloorId = 0
    var selectedWingId = 0
    private lateinit var objectViewModel: ObjectViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_client)
        initToolbar()
        this.spinnerClient.setTitle("Select Client")
        this.spinnerLocation.setTitle("Select Facility")
        this.spinnerDepartment.setTitle("Select Department")
        spinnerBuilding.visibility = View.GONE
        spinnerFloor.visibility = View.GONE
        spinnerWing.visibility = View.GONE

        objectViewModel = ViewModelProvider(this).get(ObjectViewModel::class.java)

        objectViewModel.allClient.observe(this, Observer { clients ->
            clients?.let {
                val clientAdapter =
                    ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, it)
                this.spinnerClient.adapter = clientAdapter
            }
        })

        /* AsyncTask.execute {
             val clientData = AppDb.getInstance(applicationContext).clientDao().getAll()
             val clientAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, clientData)
             this.spinnerClient.adapter = clientAdapter
         }*/
        this.spinnerClient.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedClient: Client = parent.selectedItem as Client
                selectedClientId = selectedClient.clientId
                Log.i("SelectClient", "Client:$selectedClient")
                /* AsyncTask.execute {
                     val facilityData = AppDb.getInstance(applicationContext).facilityDao().getFacilityByClientId(selectedClient.clientId)
                     val deptData = AppDb.getInstance(applicationContext).departmentDao().getDepartmentByFacilityId(selectedClient.clientId)
                     val facilityAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, facilityData)
                     val deptAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, deptData)
                     runOnUiThread {
                         spinnerLocation.adapter = facilityAdapter
                         spinnerDepartment.adapter = deptAdapter
                     }
                 }*/
                objectViewModel.getFacilityData(selectedClientId, application)
                    .observe(this@SelectClient, Observer { facility ->
                        facility?.let {
                            val facilityAdapter = ArrayAdapter(
                                applicationContext,
                                android.R.layout.simple_spinner_item,
                                it
                            )
                            spinnerLocation.adapter = facilityAdapter
                        }
                    })
                objectViewModel.getDepartmentData(selectedClientId, application)
                    .observe(this@SelectClient, Observer { deptData ->
                        deptData?.let {
                            val deptAdapter = ArrayAdapter(
                                applicationContext,
                                android.R.layout.simple_spinner_item,
                                deptData
                            )
                            spinnerDepartment.adapter = deptAdapter
                        }
                    })
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        this.spinnerLocation.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedFacility: Facility = parent.selectedItem as Facility
                selectedFacilityId = selectedFacility.facilityId!!
                Log.i("SelectClient", "Facility:$selectedFacility")
                /*AsyncTask.execute {
                    val buildingData = AppDb.getInstance(applicationContext).buildingLangDao().getBuildingLangByFacilityId(selectedFacility.facilityId,1)
                    val buildingAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, buildingData)
                    runOnUiThread {
                        spinnerBuilding.adapter = buildingAdapter
                    }
                }*/
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        /*
        this.spinnerBuilding.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedBuilding: BuildingandLang = parent.selectedItem as BuildingandLang
                selectedBuildingId = selectedBuilding.buildingId!!
                Log.i("SelectClient", "Building:$selectedBuilding")
                AsyncTask.execute {
                    val floorData = AppDb.getInstance(applicationContext).floorDao().getFloorbyBuildingId(selectedBuilding.buildingId)
                    val floorAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, floorData)
                    runOnUiThread {
                        spinnerFloor.adapter = floorAdapter
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        this.spinnerFloor.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedFloor: Floor = parent.selectedItem as Floor
                selectedFloorId = selectedFloor.floorId!!
                Log.i("SelectClient", "Floor:$selectedFloor")
                AsyncTask.execute {
                    val wingData = AppDb.getInstance(applicationContext).wingDao().getWingFloorId(selectedFloor.floorId)
                    val wingAdapter = ArrayAdapter(applicationContext, android.R.layout.simple_spinner_item, wingData)
                    runOnUiThread {
                        spinnerWing.adapter = wingAdapter
                    }
                }
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        this.spinnerWing.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
                val selectedWing: Wing = parent.selectedItem as Wing
                selectedWingId = selectedWing.wingId!!
                Log.i("SelectClient", "Floor:$selectedWing")
            }
            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        */

        this.spinnerDepartment.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>,
                view: View?,
                position: Int,
                id: Long
            ) {
                val selectedDept: Department = parent.selectedItem as Department
                selectedDepartmentId = selectedDept.departmentId!!
                Log.i("SelectClient", "Dept:$selectedDepartmentId")
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        this.buttonNext.setOnClickListener {
            val intent = Intent(this, WeekDetails::class.java)
            //val intent = Intent(this, AssetList::class.java)
            intent.putExtra("ClientID", selectedClientId)
            intent.putExtra("FacilityID", selectedFacilityId)
            intent.putExtra("DepartmentID", selectedDepartmentId)
            intent.putExtra("BuildingID", selectedBuildingId)
            intent.putExtra("FloorID", selectedFloorId)
            intent.putExtra("WingID", selectedWingId)
            this.startActivity(intent)
        }
    }
}