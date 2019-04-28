package edu.washington.magdanat.quizdroid

import android.app.TabActivity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.widget.TabHost

class MainActivity : TabActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val tabHost = findViewById<TabHost>(android.R.id.tabhost)
//        val tabSpec = tabHost.newTabSpec("tab1")
//        tabSpec.setIndicator(R.layout.overview_activity)
//        val intent = Intent(this, )
    }
}
