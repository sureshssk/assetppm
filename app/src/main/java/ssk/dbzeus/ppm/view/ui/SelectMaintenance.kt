package ssk.dbzeus.ppm.view.ui

import android.content.Intent
import android.os.AsyncTask
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import androidx.annotation.RequiresApi
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_select_client.*
import kotlinx.android.synthetic.main.activity_select_maintenance.*
import ssk.dbzeus.ppm.AppDb
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.asset.*
import ssk.dbzeus.ppm.service.model.entity.userdata.Client
import ssk.dbzeus.ppm.service.model.entity.userdata.Department
import ssk.dbzeus.ppm.service.model.entity.userdata.Facility


class SelectMaintenance : BaseActivity() {
    var selectedClientId = 0;
    var selectedFacilityId = 0;
    var selectedDepartmentId = 0;
    var selectedBuildingId = 0;
    var selectedFloorId = 0;
    var selectedWingId = 0;

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_maintenance)
        initToolbar()

        lAudit.setOnClickListener {
            startActivity(Intent(this, SelectClient::class.java))
        }
        lPPM.setOnClickListener {
            startActivity(Intent(this, SelectClient::class.java))
        }
        lComplaince.setOnClickListener {
            startActivity(Intent(this, SelectClient::class.java))
        }
    }
}