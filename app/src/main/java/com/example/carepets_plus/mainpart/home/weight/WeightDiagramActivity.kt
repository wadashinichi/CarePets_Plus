package com.example.carepets_plus.mainpart.home.weight

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carepets_plus.R
import com.example.carepets_plus.database.Weight
import com.example.carepets_plus.database.WeightRepository
import com.example.carepets_plus.databinding.ActivityWeightDiagramBinding
import com.example.carepets_plus.mainpart.TrackerActivity
import com.example.carepets_plus.mainpart.home.HomeFragment
import com.example.carepets_plus.mainpart.reminder.ReminderFragment
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class WeightDiagramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeightDiagramBinding
    private lateinit var res: WeightRepository
    private var id = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightDiagramBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setToolBar()
        res = WeightRepository(this)
        val intent: Intent = intent
        id = intent.getIntExtra("petId", 0)

        displayData(id)
        binding.btnAdd.setOnClickListener {
            linkToAddWeight(id)
        }
    }

    private fun setToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }
    private fun displayData(id: Int) {
        val wList: MutableList<Weight>? = res.getAllWeight(id)
        displayDiagram(wList)
        displayList(wList)
    }
    private fun displayDiagram(wList: MutableList<Weight>?) {
        var pointList: MutableList<DataPoint> = mutableListOf()
        if (wList != null) {
            if (wList.size < 11) {
                for (x in 0..wList.size-1) {
                    var dataPoint: DataPoint = DataPoint(x.toDouble(), wList[x].weightResult.toDouble())
                    pointList.add(dataPoint)
                }
            } else {
                for (x in 0 .. 10) {
                    var dataPoint: DataPoint = DataPoint(x.toDouble(), wList[x-11].weightResult.toDouble())
                    pointList.add(dataPoint)
                }
            }
        }
        var arr: Array<DataPoint> = pointList.toTypedArray()
        val series: LineGraphSeries<DataPoint> = LineGraphSeries(arr)
        binding.weightGraph.animate()
        binding.weightGraph.viewport.isScalable = true
        binding.weightGraph.viewport.isScrollable = true
        binding.weightGraph.viewport.setScalableY(true)
        binding.weightGraph.viewport.setScrollableY(true)
        series.color = R.color.primary
        binding.weightGraph.addSeries(series)
    }
    private fun displayList(wList: MutableList<Weight>?) {
        if (wList != null) {
            val adapter = WeightListAdapter(wList)
            binding.rvWeight.adapter = adapter
            binding.rvWeight.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
            var decorate: RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            binding.rvWeight.addItemDecoration(decorate)
        }
    }
    private fun linkToAddWeight(id: Int) {
        val i: Intent = Intent(this, AddWeightActivity::class.java)
        i.putExtra("petId", id)
        startActivity(i)
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> reDirect(id)
        }
        return super.onOptionsItemSelected(item)
    }
    private fun reDirect(id: Int) {
        val i: Intent = Intent(this, TrackerActivity::class.java)
        i.putExtra("petId", id)
        startActivity(i)
    }
}