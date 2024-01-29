package com.neocafe.neocafewaiter.auth.auth

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.databinding.FragmentOTPBinding
import com.neocafe.neocafewaiter.auth.login.OTPRequest
import com.neocafe.neocafewaiter.model.api.retrofit.NetworkStatus
import com.neocafe.neocafewaiter.ui.activities.MainActivity
import com.neocafe.neocafewaiter.login.presentation.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class OTPFragment : Fragment() {

    private lateinit var binding: FragmentOTPBinding
    private val viewModel by viewModel<AuthViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentOTPBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val phoneNumber = arguments?.getString("key")
        binding.helpText.text = getString(R.string.otpHelperText) + " "+ phoneNumber
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
        binding.signInBtn.setOnClickListener {
            otpCheckRequest()
//            val intent = Intent(this.activity, MainActivity::class.java)
//            startActivity(intent)
//            activity?.finish()
        }

        otpResponse()


    }

    private fun otpCheckRequest(){
        val otpCode = (binding.inputCode1.text.toString() + binding.inputCode2.text.toString() + binding.inputCode3.text.toString() + binding.inputCode4.text.toString()).toInt()
        viewModel.otpCheck(OTPRequest(otpCode))
    }

    private fun otpResponse(){
        viewModel.otpResponse.observe(viewLifecycleOwner, Observer {
            if(it is NetworkStatus.Success){
                val intent = Intent(this.activity, MainActivity::class.java)
                startActivity(intent)
                activity?.finish()
            }else if(it is NetworkStatus.Error){
                binding.helpText.text = "Код введен неверно, попробуйте еще раз"
                binding.helpText.setTextColor(Color.RED)

                binding.inputCode1.setTextColor(Color.RED)
                binding.inputCode1.backgroundTintList = ColorStateList.valueOf(Color.RED)

                binding.inputCode2.setTextColor(Color.RED)
                binding.inputCode2.backgroundTintList = ColorStateList.valueOf(Color.RED)

                binding.inputCode3.setTextColor(Color.RED)
                binding.inputCode3.backgroundTintList = ColorStateList.valueOf(Color.RED)

                binding.inputCode4.setTextColor(Color.RED)
                binding.inputCode4.backgroundTintList = ColorStateList.valueOf(Color.RED)
            }
        })
    }

}