package com.neocafe.neocafewaiter.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.databinding.MenuInfoItemBinding
import com.neocafe.neocafewaiter.entities.menu.MenuResponse

class MenuRvAdapter : RecyclerView.Adapter<MenuRvAdapter.ViewHolder>() {
	
	private var menuList: List<MenuResponse> = emptyList()
	
	inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
		val binding = MenuInfoItemBinding.bind(itemView)
		fun bind(menu: MenuResponse) {
			binding.menuNameTxt.text = menu.name
			//todo:ready data
			//todo:Use resource string with placeholders
			binding.menuPriceTxt.text = menu.price.replace(".00", "") + " сом"
		}
	}
	
	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuRvAdapter.ViewHolder {
		val view =
			LayoutInflater.from(parent.context).inflate(R.layout.menu_info_item, parent, false)
		return ViewHolder(view)
	}
	
	override fun onBindViewHolder(holder: MenuRvAdapter.ViewHolder, position: Int) {
		holder.bind(menuList[position])
	}
	
	override fun getItemCount() = menuList.count()
	
	fun setMenuList(newList: List<MenuResponse>) {
		menuList = newList
		notifyItemChanged(itemCount)
	}
}