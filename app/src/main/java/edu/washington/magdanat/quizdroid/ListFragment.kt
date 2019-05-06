//package edu.washington.magdanat.quizdroid
//
//
//import android.content.Context
//import android.os.Bundle
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import androidx.fragment.app.Fragment
//import androidx.recyclerview.widget.LinearLayoutManager
//import androidx.recyclerview.widget.RecyclerView
//
//
//
//
//class OriginalListFragment : Fragment() {
//
//    private lateinit var recycler : RecyclerView
//
//    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
//        val view = inflater.inflate(R.layout.fragment_list, container, false)
//        val list = listOf("Math","Physics","Marvel Super Heroes")
//
//        val adapter = RecyclerViewAdapter(list)
//        recycler = view.findViewById(R.id.myRecyclerView)
//        recycler.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
//        recycler.adapter = adapter
//        recycler.setHasFixedSize(true)
//
//
//        return view
//    }
//
//
//
//}