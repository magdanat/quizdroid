package edu.washington.magdanat.quizdroid

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item.view.*

 class RecyclerViewAdapter(var list: List<String>): RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

     private val variableMath = bundleOf(
         "Topic" to "Math",
         "Description" to "This quiz will be a series of math questions",
         "Questions" to 5
     )

     private val variablePhysics = bundleOf(
         "Topic" to "Physics",
         "Description" to "This quiz will be a series of physics questions",
         "Questions" to 2
     )

     private val variableMCU = bundleOf(
         "Topic" to "Marvel Super Heroes",
         "Description" to "This quiz will be a series of questions about the Marvel Cinematic Universe",
         "Questions" to 4
     )

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
                Toast.makeText(itemView.context,  itemView.content.text.toString() + "clicked!", Toast.LENGTH_SHORT).show()
                if (itemView.content.text.toString().equals("Math")) {
                    it.findNavController().navigate(R.id.action_listFragment_to_detailFragment, variableMath)
                } else if (itemView.content.text.toString().equals("Physics")) {
                    it.findNavController().navigate(R.id.action_listFragment_to_detailFragment, variablePhysics)
                } else {
                    it.findNavController().navigate(R.id.action_listFragment_to_detailFragment, variableMCU)
                }
            }
        }


    }
}
