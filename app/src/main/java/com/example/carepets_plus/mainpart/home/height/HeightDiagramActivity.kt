package com.example.carepets_plus.mainpart.home.height

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
import com.example.carepets_plus.database.Height
import com.example.carepets_plus.database.HeightRepository
import com.example.carepets_plus.databinding.ActivityWeightDiagramBinding
import com.example.carepets_plus.mainpart.TrackerActivity
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class HeightDiagramActivity : AppCompatActivity() {
    private lateinit var binding: ActivityWeightDiagramBinding
    private lateinit var res: HeightRepository
    private var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeightDiagramBinding.inflate(layoutInflater)
        setContentView(binding.root)
        res = HeightRepository(this)
        setToolBar()
        val intent: Intent = intent
        id = intent.getIntExtra("petId", 0)

        displayData(id)
        binding.btnAdd.setOnClickListener {
            linkToAddHeight(id)
        }
        binding.btnDel.setOnClickListener {
            val builder: AlertDialog.Builder? = AlertDialog.Builder(this)
            builder?.apply {
                setPositiveButton("Delete"
                ) { _, _ ->
                    if (id != null) {
                        res.delHeightById(id)
                        toReturn()
                    }
                }
                setNegativeButton("Cancle",
                    DialogInterface.OnClickListener { dialog, _ ->
                        dialog.cancel()
                    })
            }
            builder?.setMessage("Do you want to delete all heights of this pet?")
            builder?.create()
            builder?.show()
        }
    }
    private fun setToolBar() {
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolBar.title = "Pet's Height Diagram"
    }
    private fun displayData(id: Int) {
        val hList: MutableList<Height>? = res.getAllHeight(id)
        displayDiagram(hList)
        displayList(hList)
    }
    private fun displayDiagram(hList: MutableList<Height>?) {
        var pointList: MutableList<DataPoint> = mutableListOf()
        if (hList != null) {
            if (hList.size < 11) {
                for (x in 0..hList.size-1) {
                    var dataPoint: DataPoint = DataPoint(x.toDouble(), hList[x].heightResult.toDouble())
                    pointList.add(dataPoint)
                }
            } else {
                for (x in 0 .. 10) {
                    var dataPoint: DataPoint = DataPoint(x.toDouble(), hList[x-11].heightResult.toDouble())
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
    private fun displayList(hList: MutableList<Height>?) {
        if (hList != null) {
            val adapter = HeightListAdapter(hList)
            binding.rvWeight.adapter = adapter
            binding.rvWeight.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, true)
            var decorate: RecyclerView.ItemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
            binding.rvWeight.addItemDecoration(decorate)
        }
    }
    private fun linkToAddHeight(id: Int) {
        val i: Intent = Intent(this, AddHeightActivity::class.java)
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