package com.example.carepets_plus.mainpart.home.heartbeat

import android.app.AlertDialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.carepets_plus.R
import com.example.carepets_plus.database.HeartBeat
import com.example.carepets_plus.database.HeartBeatRepository
import com.example.carepets_plus.database.Height
import com.example.carepets_plus.database.WeightRepository
import com.example.carepets_plus.databinding.ActivityHeightDiagrmaBinding
import com.example.carepets_plus.databinding.ActivityWeightDiagramBinding
import com.example.carepets_plus.mainpart.TrackerActivity
import com.example.carepets_plus.mainpart.home.height.AddHeightActivity
import com.example.carepets_plus.mainpart.home.height.HeightListAdapter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class HeartBeatDiagramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeightDiagramBinding
    private lateinit var res: HeartBeatRepository
    private var id: Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightDiagramBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setToolBar()
        res = HeartBeatRepository(this)
        val intent: Intent = intent
        id = intent.getIntExtra("petId", 0)

        displayData(id)
        binding.btnAdd.setOnClickListener {
            linkToAddHeartBeat(id)
        }
        binding.btnDel.setOnClickListener {
            val builder: AlertDialog.Builder? = AlertDialog.Builder(this)
            builder?.apply {
                setPositiveButton("Delete"
                ) { _, _ ->
                    if (id != null) {
                        res.delHeartBeatById(id)
                        toReturn()
                    }
                }
                setNegativeButton("Cancle",
                    DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    })
            }
            builder?.setMessage("Do you want to delete all heart beat data of this pet?")
            builder?.create()
            builder?.show()
        }
    }
    private fun setToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolBar.title = "Pet's Heart Beat Diagram"
    }
    private fun displayData(id: Int) {
        val hbList: MutableList<HeartBeat>? = res.getAllHeartBeat(id)
        displayDiagram(hbList)
        displayList(hbList)
    }
    private fun displayDiagram(hbList: MutableList<HeartBeat>?) {
        var pointList: MutableList<DataPoint> = mutableListOf()
        if (hbList != null) {
            if (hbList.size < 11) {
                for (x in 0..hbList.size-1) {
                    var dataPoint: DataPoint = DataPoint(x.toDouble(), hbList[x].heartBeatResult.toDouble())
                    pointList.add(dataPoint)
                }
            } else {
                for (x in 0 .. 10) {
                    var dataPoint: DataPoint = DataPoint(x.toDouble(), hbList[x-11].heartBeatResult.toDouble())
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
    private fun displayList(hbList: MutableList<HeartBeat>?) {
        if (hbList != null) {
            val adapter = HeartBeatListAdapter(hbList)
            binding.rvWeight.adapter = adapter
            binding.rvWeight.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
            var decorate: RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            binding.rvWeight.addItemDecoration(decorate)
        }
    }
    private fun linkToAddHeartBeat(id: Int) {
        val i: Intent = Intent(this, AddHeartBeatActivity::class.java)
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
    private fun toReturn() {
        recreate()
    }
}