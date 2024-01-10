package com.neocafe.neocafewaiter.adapters

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.databinding.TablesItemBinding
import com.neocafe.neocafewaiter.entities.table.TableResponse

class TablesRvAdapter: RecyclerView.Adapter<TablesRvAdapter.ViewHolder>() {

    private var tablesList: List<TableResponse> = emptyList()
    var selectTable: ((TableResponse)->Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val binding = TablesItemBinding.bind(itemView)
        fun bind(table: TableResponse){
            binding.tablesNumberTxt.text = table.number.toString()
            if(table.status.equals("free")){
                binding.root.backgroundTintList = ColorStateList.valueOf(
                    itemView.context.getColor(R.color.free_table_color)
                )
            }else{
                binding.root.backgroundTintList = ColorStateList.valueOf(
                    itemView.context.getColor(R.color.booked_table_color)
                )
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.tables_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return tablesList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tablesList[position])
        holder.binding.root.setOnClickListener{
            selectTable?.invoke(tablesList[position])
        }
    }

    fun setTablesList(newList: List<TableResponse>){
        tablesList = newList
        notifyDataSetChanged()
    }

}