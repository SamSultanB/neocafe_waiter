package com.neocafe.neocafewaiter.ui.fragments.orderNew

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.databinding.FragmentNewOrderSuccessBinding

class NewOrderSuccessFragment : Fragment() {

    private lateinit var binding: FragmentNewOrderSuccessBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentNewOrderSuccessBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}