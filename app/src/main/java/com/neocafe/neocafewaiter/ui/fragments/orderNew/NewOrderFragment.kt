package com.neocafe.neocafewaiter.ui.fragments.orderNew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.adapters.TablesRvAdapter
import com.neocafe.neocafewaiter.databinding.FragmentNewOrderBinding
import com.neocafe.neocafewaiter.entities.basket.Basket
import com.neocafe.neocafewaiter.model.api.retrofit.Resource
import com.neocafe.neocafewaiter.viewModels.NewOrderViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewOrderFragment : Fragment() {

    private lateinit var binding: FragmentNewOrderBinding
    private val tablesAdapter = TablesRvAdapter()
    private val viewModel by viewModel<NewOrderViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewOrderBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.tablesRv.layoutManager = GridLayoutManager(requireContext(), 3)
        viewModel.getTables()
        getTablesResponse()
        tablesAdapter.selectTable = {
            if(it.status.equals("free")){
                Basket.tableNumber = it.number
                findNavController().navigate(R.id.action_newOrderFragment_to_newOrderMenuFragment)
            }else{
                Toast.makeText(requireContext(), "Занято!", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun getTablesResponse(){
        viewModel.tablesResponse.observe(viewLifecycleOwner, Observer{
            binding.progressBar.isVisible = true
            binding.tablesRv.isVisible = false
            if (it is Resource.Success){
                binding.progressBar.isVisible = false
                binding.tablesRv.isVisible = true
                it.data?.let { it1 ->
                    tablesAdapter.setTablesList(it1)
                    binding.tablesRv.adapter = tablesAdapter
                }
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}