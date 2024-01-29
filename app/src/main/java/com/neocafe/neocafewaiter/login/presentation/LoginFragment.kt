package com.neocafe.neocafewaiter.login.presentation

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.databinding.FragmentLoginBinding
import com.neocafe.neocafewaiter.auth.login.LoginFormRequest
import com.neocafe.neocafewaiter.model.api.retrofit.NetworkStatus
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment() {
	
	//todo:clear binding reference in onDestroyView
	private var _binding: FragmentLoginBinding? = null
	val binding: FragmentLoginBinding get() = _binding!!
	
	private val viewModel by viewModel<AuthViewModel>()
	
	override fun onCreateView(
		inflater: LayoutInflater, container: ViewGroup?,
		savedInstanceState: Bundle?,
	): View {
		_binding = FragmentLoginBinding.inflate(layoutInflater, container, false)
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
		}
		
		loginResponse()
	}
	
	override fun onDestroyView() {
		super.onDestroyView()
		_binding = null
	}
	
	private fun loginResponse() {
		viewModel.loginReponse.observe(viewLifecycleOwner) { status ->
			when (status) {
				is NetworkStatus.Success -> {
					val bundle = Bundle()
					bundle.putString("key", status.data.phoneNumber)
					findNavController().navigate(R.id.action_loginFragment_to_OTPFragment, bundle)
				}
				
				is NetworkStatus.Error -> {
					binding.loginField.setTextColor(Color.RED)
					binding.loginTitle.setTextColor(Color.RED)
					binding.passwordField.setTextColor(Color.RED)
					binding.passwordTitle.setTextColor(Color.RED)
					println(status.message)
					Toast.makeText(requireContext(), status.message, Toast.LENGTH_SHORT).show()
				}
				
				is NetworkStatus.Loading -> {
				
				}
			}
		}
	}
	
	private fun fieldsValidation() {
		//todo:null safety
		if (!binding.loginField.text.isNullOrEmpty() && !binding.passwordField.text.isNullOrEmpty()) {
			binding.getCodeBtn.isClickable = true
			binding.getCodeBtn.alpha = 1f
		} else {
			binding.getCodeBtn.isClickable = false
			binding.getCodeBtn.alpha = 0.5f
		}
	}
	
}