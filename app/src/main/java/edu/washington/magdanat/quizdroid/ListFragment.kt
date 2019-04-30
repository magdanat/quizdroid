package edu.washington.magdanat.quizdroid


import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_list.*

//import android.widget.Button
//import androidx.navigation.findNavController


class ListFragment : Fragment() {

    private var columnCount = 1

//    private var listener: OnListFragmentInteractionListener? = null

    private lateinit var recycler : RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_list, container, false)
        val list = listOf("Math","Physics","Marvel Super Heroes")

//        val adapter = RecyclerViewAdapter(list, listener)
        val adapter = RecyclerViewAdapter(list)
        recycler = view.findViewById(R.id.myRecyclerView)
        recycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recycler.adapter = adapter
        recycler.setHasFixedSize(true)

//        adapter.onPersonClickedListener = { position, name ->
//            Toast.makeText(context, "$name clicked!", Toast.LENGTH_SHORT).show()
//        }

        return view
    }

//    interface OnListFragmentInteractionListener {
//        fun onListFragmentInteraction(item: List<String>)
//    }
//
//    companion object {
//
//        // TODO: Customize parameter argument names
//        const val ARG_COLUMN_COUNT = "column-count"
//
//        // TODO: Customize parameter initialization
//        @JvmStatic
//        fun newInstance(columnCount: Int) =
//            ListFragment().apply {
//                arguments = Bundle().apply {
//                    putInt(ARG_COLUMN_COUNT, columnCount)
//                }
//            }
//    }

}