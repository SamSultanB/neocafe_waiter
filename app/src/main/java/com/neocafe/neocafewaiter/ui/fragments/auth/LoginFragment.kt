package com.neocafe.neocafewaiter.ui.fragments.auth

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.databinding.FragmentLoginBinding
import com.neocafe.neocafewaiter.entities.login.LoginFormRequest
import com.neocafe.neocafewaiter.model.api.retrofit.Resource
import com.neocafe.neocafewaiter.viewModels.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding
    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.loginField.doAfterTextChanged { fieldsValidation() }
        binding.passwordField.doAfterTextChanged { fieldsValidation() }

        fieldsValidation()

        binding.getCodeBtn.setOnClickListener {
            val login = binding.loginField.text.toString()
            val password = binding.passwordField.text.toString()
            viewModel.login(LoginFormRequest(login, password))
//            findNavController().navigate(R.id.action_loginFragment_to_OTPFragment)
        }

        loginResponse()

    }

    private fun loginResponse(){
        viewModel.loginResponse.observe(viewLifecycleOwner, Observer{
            if(it is Resource.Success){
                val bundle = Bundle()
                bundle.putString("key", it.data?.phone_number)
                findNavController().navigate(R.id.action_loginFragment_to_OTPFragment, bundle)
            }else if(it is Resource.Error){
                binding.loginField.setTextColor(Color.RED)
                binding.loginTitle.setTextColor(Color.RED)
                binding.passwordField.setTextColor(Color.RED)
                binding.passwordTitle.setTextColor(Color.RED)
                println(it.message)
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun fieldsValidation(){
        if(binding.loginField.text!!.isNotEmpty() && binding.passwordField.text!!.isNotEmpty()){
            binding.getCodeBtn.isClickable = true
            binding.getCodeBtn.alpha = 1f
        }else{
            binding.getCodeBtn.isClickable = false
            binding.getCodeBtn.alpha = 0.5f
        }
    }

}