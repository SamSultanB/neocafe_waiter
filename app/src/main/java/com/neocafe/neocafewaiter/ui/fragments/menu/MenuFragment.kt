package com.neocafe.neocafewaiter.ui.fragments.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.neocafe.neocafewaiter.adapters.MenuRvAdapter
import com.neocafe.neocafewaiter.databinding.FragmentMenuBinding
import com.neocafe.neocafewaiter.entities.category.CategoryResonse
import com.neocafe.neocafewaiter.model.api.retrofit.Resource
import com.neocafe.neocafewaiter.viewModels.MenuViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : Fragment() {

    private lateinit var binding: FragmentMenuBinding
    private val viewModel by viewModel<MenuViewModel>()
    private val menuAdapter = MenuRvAdapter()
    private val selected = 1f
    private val unselected = 0.3f

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvMenu.layoutManager = LinearLayoutManager(requireContext())
        viewModel.getCategories()
        categoriesResponse()

        getMenuResponse()

        binding.category1.setOnClickListener {
            binding.category1.alpha = selected
            val category = binding.category1.tag as? CategoryResonse
            category?.let {
                getMenu(it.slug)
            }
            binding.category2.alpha = unselected
            binding.category3.alpha = unselected
            binding.category4.alpha = unselected
            binding.category5.alpha = unselected
            binding.category6.alpha = unselected
            binding.category7.alpha = unselected
            binding.category8.alpha = unselected
        }

        binding.category2.setOnClickListener {
            binding.category2.alpha = selected
            val category = binding.category2.tag as? CategoryResonse
            category?.let {
                getMenu(it.slug)
            }
            binding.category1.alpha = unselected
            binding.category3.alpha = unselected
            binding.category4.alpha = unselected
            binding.category5.alpha = unselected
            binding.category6.alpha = unselected
            binding.category7.alpha = unselected
            binding.category8.alpha = unselected
        }

        binding.category3.setOnClickListener {
            binding.category3.alpha = selected
            val category = binding.category3.tag as? CategoryResonse
            category?.let {
                getMenu(it.slug)
            }
            binding.category2.alpha = unselected
            binding.category1.alpha = unselected
            binding.category4.alpha = unselected
            binding.category5.alpha = unselected
            binding.category6.alpha = unselected
            binding.category7.alpha = unselected
            binding.category8.alpha = unselected
        }

        binding.category4.setOnClickListener {
            binding.category4.alpha = selected
            val category = binding.category4.tag as? CategoryResonse
            category?.let {
                getMenu(it.slug)
            }
            binding.category2.alpha = unselected
            binding.category3.alpha = unselected
            binding.category1.alpha = unselected
            binding.category5.alpha = unselected
            binding.category6.alpha = unselected
            binding.category7.alpha = unselected
            binding.category8.alpha = unselected
        }

        binding.category5.setOnClickListener {
            binding.category5.alpha = selected
            val category = binding.category5.tag as? CategoryResonse
            category?.let {
                getMenu(it.slug)
            }
            binding.category2.alpha = unselected
            binding.category3.alpha = unselected
            binding.category4.alpha = unselected
            binding.category1.alpha = unselected
            binding.category6.alpha = unselected
            binding.category7.alpha = unselected
            binding.category8.alpha = unselected
        }

        binding.category6.setOnClickListener {
            binding.category6.alpha = selected
            val category = binding.category6.tag as? CategoryResonse
            category?.let {
                getMenu(it.slug)
            }
            binding.category2.alpha = unselected
            binding.category3.alpha = unselected
            binding.category4.alpha = unselected
            binding.category5.alpha = unselected
            binding.category1.alpha = unselected
            binding.category7.alpha = unselected
            binding.category8.alpha = unselected
        }

        binding.category7.setOnClickListener {
            binding.category7.alpha = selected
            val category = binding.category7.tag as? CategoryResonse
            category?.let {
                getMenu(it.slug)
            }
            binding.category2.alpha = unselected
            binding.category3.alpha = unselected
            binding.category4.alpha = unselected
            binding.category5.alpha = unselected
            binding.category6.alpha = unselected
            binding.category1.alpha = unselected
            binding.category8.alpha = unselected
        }

        binding.category8.setOnClickListener {
            binding.category8.alpha = selected
            val category = binding.category8.tag as? CategoryResonse
            category?.let {
                getMenu(it.slug)
            }
            binding.category2.alpha = unselected
            binding.category3.alpha = unselected
            binding.category4.alpha = unselected
            binding.category5.alpha = unselected
            binding.category6.alpha = unselected
            binding.category7.alpha = unselected
            binding.category1.alpha = unselected
        }

    }

    private fun getMenuResponse(){
        viewModel.menuResponse.observe(viewLifecycleOwner, Observer{
            if (it is Resource.Success){
                binding.rvMenu.isVisible = true
                binding.progressBar.isVisible = false
                it.data?.let {
                    menuAdapter.setMenuList(it)
                    binding.rvMenu.adapter = menuAdapter
                }
            }else if (it is Resource.Error){
                binding.rvMenu.isVisible = false
                binding.progressBar.isVisible = true
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }else if (it is Resource.Loading){
                binding.rvMenu.isVisible = false
                binding.progressBar.isVisible = true
            }
        })
    }

    private fun getMenu(slug: String){
        viewModel.getMenu(slug)
    }

    private fun categoriesResponse(){
        viewModel.categoryResponse.observe(viewLifecycleOwner, Observer{
            if (it is Resource.Success){
                it.data?.let { it1 ->
                   for (i in 1..it1.size){
                       when(i){
                           1 -> {
                               binding.category1.text = it1[0].name
                               binding.category1.tag = it1[0]
                               binding.category1.isVisible = true
                           }
                           2 -> {
                               binding.category2.text = it1[1].name
                               binding.category2.isVisible = true
                               binding.category2.tag = it1[1]
                           }
                           3 -> {
                               binding.category3.text = it1[2].name
                               binding.category3.isVisible = true
                               binding.category3.tag = it1[2]
                           }
                           4 -> {
                               binding.category4.text = it1[3].name
                               binding.category4.isVisible = true
                               binding.category4.tag = it1[3]
                           }
                           5 -> {
                               binding.category5.text = it1[4].name
                               binding.category5.isVisible = true
                               binding.category5.tag =it1[4]
                           }
                           6 -> {
                               binding.category6.text = it1[5].name
                               binding.category6.isVisible = true
                               binding.category6.tag = it1[5]
                           }
                           7 -> {
                               binding.category7.text = it1[6].name
                               binding.category7.isVisible = true
                               binding.category7.tag = it1[6]
                           }
                           8 -> {
                               binding.category8.text = it1[7].name
                               binding.category8.isVisible = true
                               binding.category8.tag = it1[7]
                           }
                       }
                   }
                }
            }else if(it is Resource.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}