package com.neocafe.neocafewaiter.ui.fragments.orders

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.adapters.OrdersPageVpAdapter
import com.neocafe.neocafewaiter.databinding.FragmentOrdersBinding

class OrdersFragment : Fragment() {

    private lateinit var binding: FragmentOrdersBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOrdersBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.viewPager.adapter = OrdersPageVpAdapter(childFragmentManager, lifecycle)
        binding.profileBtn.setOnClickListener {
            findNavController().navigate(R.id.action_ordersFragment_to_profileFragment)
        }
        binding.ordersPage.setOnClickListener {
            binding.viewPager.currentItem = 1
            binding.ordersPage.setBackgroundColor(resources.getColor((R.color.selected_tab)))
            binding.tablesPage.setBackgroundColor(resources.getColor(R.color.not_selected_tab))
        }
        binding.tablesPage.setOnClickListener {
            binding.viewPager.currentItem = 0
            binding.tablesPage.setBackgroundColor(resources.getColor((R.color.selected_tab)))
            binding.ordersPage.setBackgroundColor(resources.getColor(R.color.not_selected_tab))
        }


        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                when (position) {
                    0 -> {
                        binding.tablesPage.setBackgroundColor(resources.getColor((R.color.selected_tab)))
                        binding.ordersPage.setBackgroundColor(resources.getColor(R.color.not_selected_tab))
                    }
                    1 -> {
                        binding.ordersPage.setBackgroundColor(resources.getColor((R.color.selected_tab)))
                        binding.tablesPage.setBackgroundColor(resources.getColor(R.color.not_selected_tab))
                    }
                }
            }
        })



    }

}