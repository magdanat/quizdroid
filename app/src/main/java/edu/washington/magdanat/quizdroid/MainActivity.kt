package edu.washington.magdanat.quizdroid

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TabHost

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tabHost = findViewById<TabHost>(android.R.id.tabhost)
        val tabSpec = tabHost.newTabSpec("tab1")
        tabSpec.setIndicator(R.layout.acti)
        val intent = Intent(this, )
    }
}
