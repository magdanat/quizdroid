package edu.washington.magdanat.quizdroid

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager

import kotlinx.android.synthetic.main.home_activity.*

class HomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.home_activity)

        // Hard-coded for now
        val list = listOf("Math", "Physics", "Marvel Super Heroes")




//
//        val adapter = MyAdapter(list)
//
//        myRecyclerView.adapter = adapter
//        myRecyclerView.setHasFixedSize(true)
//        myRecyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
    }
}
