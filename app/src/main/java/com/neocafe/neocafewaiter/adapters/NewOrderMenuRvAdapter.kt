package com.neocafe.neocafewaiter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.databinding.MenuNewOrderItemBinding
import com.neocafe.neocafewaiter.entities.menu.MenuResponse

class NewOrderMenuRvAdapter: RecyclerView.Adapter<NewOrderMenuRvAdapter.ViewHolder>() {

    private var menuList: List<MenuResponse> = emptyList()

    var addItemBtn: ((MenuResponse)->Unit)? = null

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val binding = MenuNewOrderItemBinding.bind(itemView)
        fun bind(menu: MenuResponse){
            binding.menuNameTxt.text = menu.name
            binding.menuPriceTxt.text = menu.price.replace(".00", "") + " сом"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewOrderMenuRvAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.menu_new_order_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: NewOrderMenuRvAdapter.ViewHolder, position: Int) {
        holder.bind(menuList[position])
        holder.binding.addItemBtn.setOnClickListener { addItemBtn?.invoke(menuList[position]) }
    }

    override fun getItemCount(): Int {
        return menuList.size
    }

    fun setMenuList(newList: List<MenuResponse>){
        menuList = newList
        notifyDataSetChanged()
    }
}