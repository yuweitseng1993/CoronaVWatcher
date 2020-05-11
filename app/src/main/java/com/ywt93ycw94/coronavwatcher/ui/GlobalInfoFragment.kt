package com.ywt93ycw94.coronavwatcher.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.github.mikephil.charting.data.PieData
import com.github.mikephil.charting.data.PieDataSet
import com.github.mikephil.charting.data.PieEntry
import com.github.mikephil.charting.formatter.PercentFormatter
import com.github.mikephil.charting.utils.ColorTemplate
import com.ywt93ycw94.coronavwatcher.R
import com.ywt93ycw94.coronavwatcher.databinding.FragmentGlobalInfoBinding
import com.ywt93ycw94.coronavwatcher.utils.AppUtils
import com.ywt93ycw94.coronavwatcher.viewmodel.GlobalInfoViewModel
import kotlinx.android.synthetic.main.fragment_global_info.*

class GlobalInfoFragment : Fragment() {
    private lateinit var globalInfoViewModel: GlobalInfoViewModel
    private lateinit var fragmentGlobalInfoBinding: FragmentGlobalInfoBinding
    private val appUtils = AppUtils()
    val colors = java.util.ArrayList<Int>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        globalInfoViewModel = GlobalInfoViewModel()
        fragmentGlobalInfoBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_global_info, container, false)
        return fragmentGlobalInfoBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        println("DEBUG: GlobalInfoFragment onActivityCreated()")
        fragmentGlobalInfoBinding.globalViewModel = globalInfoViewModel
        fragmentGlobalInfoBinding.executePendingBindings()
        globalInfoViewModel.onFragmentTriggered()
        setupPieChart()

        globalInfoViewModel.topTenCountries.observe(viewLifecycleOwner, Observer {
            var number = globalInfoViewModel.totalConfirmed.get().toString().replace(",", "")
            val topTenCountryData = appUtils.topTenCountriesPercentage(it, number.toInt())
            updatePieChart(topTenCountryData)
        })

    }

    private fun setupPieChart() {
        pieChart.setUsePercentValues(true)
        pieChart.description.text = ""
        pieChart.setCenterTextSize(20f)
        pieChart.isRotationEnabled = false
        for (c in ColorTemplate.VORDIPLOM_COLORS) colors.add(c)
        for (c in ColorTemplate.COLORFUL_COLORS) colors.add(c)
        for (c in ColorTemplate.JOYFUL_COLORS) colors.add(c)
    }

    private fun updatePieChart(topTenCountryData: ArrayList<Pair<Float,String>>) {
        val xvalues = ArrayList<PieEntry>()
        for(t in topTenCountryData) {
            xvalues.add(PieEntry(t.first, t.second))
        }
        val dataSet = PieDataSet(xvalues, "")
        val data = PieData(dataSet)
        dataSet.colors = colors
        data.setValueFormatter(PercentFormatter())
        data.setValueTextSize(13f)
        pieChart.data = data
        pieChart.centerText = "Total:\n ${globalInfoViewModel.totalConfirmed.get()}"
    }

}