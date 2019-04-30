package edu.washington.magdanat.quizdroid

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.item.view.*

//import edu.washington.magdanat.quizdroid.ListFragment.OnListFragmentInteractionListener


//class RecyclerViewAdapter(private var list: List<String>, private var mListener: OnListFragmentInteractionListener?): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): ViewHolder {
//        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
//        return ViewHolder(itemView)
//    }
//
//    override fun getItemCount(): Int {
//        return list.size
//    }
//
//    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//            View.OnClickListener { v ->
//            val item = v.tag as List<String>
//            mListener?.onListFragmentInteraction(item)
//        }
//
//    }
//
//    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
//
//    }
//
//}

 class RecyclerViewAdapter(var list: List<String>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

//     var onPersonClickedListener: ((position: Int, name: String) -> Unit)? = null
//    var onPersonClickedListener: ((position: Int, name: String) -> Unit)? = null

     override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): ViewHolder {
         // Creates ViewHolder to hold reference of the views
         val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
         return ViewHolder(itemView)
     }

     override fun getItemCount(): Int {
         return list.size
     }

     override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
         viewHolder.bindView(list[position], position)
     }


     inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(listItem: String, position: Int) {
            itemView.content.text = listItem

            itemView.setOnClickListener {
//                onPersonClickedListener?.invoke(position, listItem)
//                Toast.makeText(itemView.context,  itemView.content.text.toString() + "clicked!", Toast.LENGTH_SHORT).show()
                it.findNavController().navigate(R.id.action_listFragment_to_detailFragment)
            }
        }


    }
}
