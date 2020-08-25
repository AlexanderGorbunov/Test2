package ru.gorbunov.gaspromservice.ui.detailedFragment

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.gorbunov.gaspromservice.R
import ru.gorbunov.gaspromservice.models.DetailedListItem

class DetailedListAdapter(private val context: Context, private val list: MutableList<DetailedListItem>)
    : RecyclerView.Adapter<DetailedListAdapter.ListViewHolder>() {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder:ListViewHolder, position: Int) {
        var listItem = list[position]
        holder!!.bind(listItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.detailed_list_item, parent, false)
        return ListViewHolder(itemView)
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        val title = itemView.findViewById<TextView>(R.id.detailed_item_name)
        val workshop = itemView.findViewById<TextView>(R.id.detailed_item_workshop)
        val hours = itemView.findViewById<TextView>(R.id.detailed_item_hours)
        val indicator = itemView.findViewById<ImageView>(R.id.detailed_item_indicator)

        fun bind(item: DetailedListItem) {
            title.text = item.name
            workshop.text = item.workshop
            hours.text = "${item.work_hours.toString()} ч."
            if (item.condition)
                indicator.setBackgroundColor(Color.GREEN)
            else
                indicator.setBackgroundColor(Color.RED)
        }
    }


}