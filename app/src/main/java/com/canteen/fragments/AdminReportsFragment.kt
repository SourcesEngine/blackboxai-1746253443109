package com.canteen.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.canteen.R
import com.canteen.databinding.FragmentAdminReportsBinding
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class AdminReportsFragment : Fragment() {
    private var _binding: FragmentAdminReportsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAdminReportsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupSalesChart()
    }

    private fun setupSalesChart() {
        val chart: BarChart = binding.salesChart
        val entries = ArrayList<BarEntry>().apply {
            add(BarEntry(0f, 10f)) // Monday
            add(BarEntry(1f, 15f)) // Tuesday
            add(BarEntry(2f, 8f))  // Wednesday
            add(BarEntry(3f, 20f)) // Thursday
            add(BarEntry(4f, 12f)) // Friday
        }

        val dataSet = BarDataSet(entries, "Daily Sales")
        dataSet.color = resources.getColor(R.color.colorPrimary)
        
        val data = BarData(dataSet)
        chart.data = data
        chart.description.text = "Weekly Sales Report"
        chart.animateY(1000)
        chart.invalidate()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}