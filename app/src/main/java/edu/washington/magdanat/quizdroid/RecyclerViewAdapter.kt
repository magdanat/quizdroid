package edu.washington.magdanat.quizdroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item.view.*

import edu.washington.magdanat.quizdroid.ListFragment.OnListFragmentInteractionListener


class RecyclerViewAdapter(private var list: List<String>, private var mListener: OnListFragmentInteractionListener?): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as List<String>
            // Notify the active callbacks interface (the activity, if the fragment is attached to
            // one) that an item has been selected.
            mListener?.onListFragmentInteraction(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): ViewHolder {
        // Creates ViewHolder to hold reference of the views
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        // Size of items to load
        return list.size
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        // Sets data on view
//
//        val test = list[position]
//        viewHolder.bindView(list[position], position)
        val item = list[position]
//        viewHolder.mIdView.text = item.id
//        viewHolder.mContentView.text = item.content

        with(viewHolder.itemView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val mIdView: TextView = itemView.item_number
        private val mContentView: TextView = itemView.content

        override fun toString(): String {
            return super.toString() + " '" + mContentView.text + "'"
        }
    }

}
