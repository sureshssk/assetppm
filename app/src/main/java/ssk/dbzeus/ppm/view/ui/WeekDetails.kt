package ssk.dbzeus.ppm.view.ui

import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ssk.dbzeus.ppm.R
import ssk.dbzeus.ppm.service.model.entity.weekassets.Detail
import ssk.dbzeus.ppm.service.viewmodel.ObjectViewModel
import ssk.dbzeus.ppm.view.adapter.WeekAdapter


class WeekDetails : BaseActivity() {

    private lateinit var recyclerWeek: RecyclerView
    private lateinit var layoutManagerGrid: GridLayoutManager
    private lateinit var weekData: List<Detail>
    private lateinit var weekViewModel: ObjectViewModel

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week_details)
        initToolbar()
        setTitle("Week Based Data")
        recyclerWeek = findViewById(R.id.recyclerWeek)
        layoutManagerGrid = GridLayoutManager(applicationContext, 3)
        recyclerWeek.layoutManager = layoutManagerGrid

        weekViewModel = ViewModelProvider(this).get(ObjectViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        weekViewModel.allWeeks.observe(this, Observer { weeksdata ->
            weeksdata?.let {
                val recyclerWeekAdapter = WeekAdapter(it, this)
                recyclerWeek.adapter = recyclerWeekAdapter
            }
        })


    }
}