package com.example.myKotlinProject

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.example.mykotlinlessson.R

class MainActivity:AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewPager = findViewById<ViewPager2>(R.id.view_pager)
        val btnHome =findViewById<TextView>(R.id.btn_home)
        val btnProfile =findViewById<TextView>(R.id.btn_profile)

        btnHome.setOnClickListener {
            viewPager.currentItem = 0
        }

        btnProfile.setOnClickListener {
            viewPager.currentItem = 1
        }

        val viewPagerAdapter = ViewPagerAdapter(this)
        viewPager.adapter = viewPagerAdapter

        viewPager.registerOnPageChangeCallback(object :ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                updateTabSelection(position)
            }
        })
    }

    private fun updateTabSelection(position:Int){
        val btnHome =findViewById<TextView>(R.id.btn_home)
        val btnProfile =findViewById<TextView>(R.id.btn_profile)

        if(position == 0){
            btnHome.isSelected = true
            btnProfile.isSelected = false
        }else{
            btnHome.isSelected = false
            btnProfile.isSelected = true
        }

    }
}