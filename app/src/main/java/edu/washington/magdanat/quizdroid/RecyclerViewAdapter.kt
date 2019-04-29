package edu.washington.magdanat.quizdroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item.view.*

import edu.washington.magdanat.quizdroid.ListFragment.OnListFragmentInteractionListener


class RecyclerViewAdapter(private var list: List<String>, private var mListener: OnListFragmentInteractionListener?): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
            View.OnClickListener { v ->
            val item = v.tag as List<String>
            mListener?.onListFragmentInteraction(item)
        }

    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    }

}
