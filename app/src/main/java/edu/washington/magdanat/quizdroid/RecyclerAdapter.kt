package edu.washington.magdanat.quizdroid


import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item.view.*


class MyAdapter(private  val list: List<String>): RecyclerView.Adapter<MyAdapter.PersonViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(viewHolder: MyAdapter.PersonViewHolder, position: Int) {
        viewHolder.bindView(list[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewHolderType: Int): MyAdapter.PersonViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return PersonViewHolder(itemView)
    }

    inner class PersonViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(text: String) {
            itemView.textView.text = text
        }
    }

}
