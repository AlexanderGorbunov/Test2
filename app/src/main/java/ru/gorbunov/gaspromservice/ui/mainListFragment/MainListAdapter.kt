package ru.gorbunov.gaspromservice.ui.mainListFragment

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import ru.gorbunov.gaspromservice.R
import ru.gorbunov.gaspromservice.models.MainListItem
import ru.gorbunov.gaspromservice.utils.SubUtils

class MainListAdapter(private val context: Context, private val list: MutableList<MainListItem>,
                      fragment: Fragment): RecyclerView.Adapter<MainListAdapter.ListViewHolder>() {

    private val listener: onItemClickListener

    init {
        this.listener = fragment as onItemClickListener
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        var listItem = list[position]
        holder!!.bind(listItem)
        holder!!.layout.setOnClickListener {
            listener.itemDetail(listItem.name)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.main_list_item, parent, false)
        return ListViewHolder(itemView)
    }

    class ListViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

        var layout = itemView.findViewById<ConstraintLayout>(R.id.main_list_item_layout)
        val title = itemView.findViewById<TextView>(R.id.main_list_item_name)
        val indicator = itemView.findViewById<ImageView>(R.id.main_list_item_indicator)
        val hours = itemView.findViewById<TextView>(R.id.main_list_item_hours)

        fun bind(item: MainListItem) {
            title.text = item.name
            indicator.setBackgroundColor(SubUtils.getMainColor(item.badCondition, item.goodCondition))
            Log.d("conditions", "Item: ${item.name} ${item.badCondition}/${item.badCondition+item.goodCondition}(${100*item.badCondition/(item.badCondition+item.goodCondition)})")
            hours.text = "${item.workHours.toString()} ч."
        }
    }



    interface onItemClickListener {
        fun itemDetail(name: String)
    }
}