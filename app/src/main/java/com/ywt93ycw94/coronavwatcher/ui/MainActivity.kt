package com.ywt93ycw94.coronavwatcher.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ywt93ycw94.coronavwatcher.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)

        val adapter = ViewPagerAdapter(supportFragmentManager)
        adapter.addFragment(LocalInfoFragment(), "MindOrks")
        adapter.addFragment(GlobalInfoFragment(), "GetMeAnApp")
        adapter.addFragment(VirusInfoFragment(), "BestContentApp")
        viewPager.adapter = adapter
        tabs.setupWithViewPager(viewPager)
    }
}
