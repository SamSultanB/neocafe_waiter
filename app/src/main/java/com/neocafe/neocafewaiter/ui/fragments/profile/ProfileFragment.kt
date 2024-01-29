package com.neocafe.neocafewaiter.ui.fragments.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.neocafe.neocafewaiter.R
import com.neocafe.neocafewaiter.databinding.FragmentProfileBinding
import com.neocafe.neocafewaiter.model.api.retrofit.NetworkStatus
import com.neocafe.neocafewaiter.viewModels.ProfileViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding
    private val viewModel by viewModel<ProfileViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getProfile()
        getProfileResponse()
        binding.arrowBackBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    private fun getProfileResponse(){
        viewModel.profileResponse.observe(viewLifecycleOwner, Observer{
            if(it is NetworkStatus.Success){
                it.data?.let {
                    binding.nameEditText.setText(it.first_name)
                    binding.surnameEditText.setText(it.last_name)
                    binding.birthDateEditText.setText(it.date_of_birth)
                    binding.phoneNumberEditTxt.setText(it.phone_number.replace("+996", ""))
                    if(it.monday){
                        if(it.monday_start_time.equals("10:00:00")){
                            Glide.with(binding.iconMonday).load(R.drawable.sun_icon).into(binding.iconMonday)
                            binding.mondayScheduleTxt.text = "Дневная смена с ${it.monday_start_time.removeRange(4, 7)} до ${it.monday_end_time.removeRange(4,7)}"
                        }else{
                            Glide.with(binding.iconMonday).load(R.drawable.night_icon).into(binding.iconMonday)
                            binding.mondayScheduleTxt.text = "Вечерняя смена с ${it.monday_start_time.removeRange(4, 7)} до ${it.monday_end_time.removeRange(4,7)}"
                        }
                    }else{
                        binding.mondayScheduleTxt.text = "Выходной"
                        Glide.with(binding.iconMonday).load(R.drawable.free_day_icon).into(binding.iconMonday)
                    }

                    if(it.tuesday){
                        if(it.tuesday_start_time.equals("10:00:00")){
                            Glide.with(binding.iconTuesday).load(R.drawable.sun_icon).into(binding.iconTuesday)
                            binding.tuesdayScheduleTxt.text = "Дневная смена с ${it.tuesday_start_time.removeRange(4, 7)} до ${it.tuesday_end_time.removeRange(4,7)}"
                        }else{
                            Glide.with(binding.iconTuesday).load(R.drawable.night_icon).into(binding.iconTuesday)
                            binding.tuesdayScheduleTxt.text = "Вечерняя смена с ${it.tuesday_start_time.removeRange(4, 7)} до ${it.tuesday_end_time.removeRange(4,7)}"
                        }
                    }else{
                        binding.tuesdayScheduleTxt.text = "Выходной"
                        Glide.with(binding.iconTuesday).load(R.drawable.free_day_icon).into(binding.iconTuesday)
                    }

                    if(it.wednesday){
                        if(it.wednesday_start_time.equals("10:00:00")){
                            Glide.with(binding.iconWednesday).load(R.drawable.sun_icon).into(binding.iconWednesday)
                            binding.wednesdayScheduleTxt.text = "Дневная смена с ${it.wednesday_start_time.removeRange(4, 7)} до ${it.wednesday_end_time.removeRange(4,7)}"
                        }else{
                            Glide.with(binding.iconWednesday).load(R.drawable.night_icon).into(binding.iconWednesday)
                            binding.wednesdayScheduleTxt.text = "Вечерняя смена с ${it.wednesday_start_time.removeRange(4, 7)} до ${it.wednesday_end_time.removeRange(4,7)}"
                        }
                    }else{
                        binding.wednesdayScheduleTxt.text = "Выходной"
                        Glide.with(binding.iconWednesday).load(R.drawable.free_day_icon).into(binding.iconWednesday)
                    }

                    if(it.thursday){
                        if(it.thursday_start_time.equals("10:00:00")){
                            Glide.with(binding.iconThursday).load(R.drawable.sun_icon).into(binding.iconThursday)
                            binding.thursdayScheduleTxt.text = "Дневная смена с ${it.thursday_start_time.removeRange(4, 7)} до ${it.thursday_end_time.removeRange(4,7)}"
                        }else{
                            Glide.with(binding.iconThursday).load(R.drawable.night_icon).into(binding.iconThursday)
                            binding.thursdayScheduleTxt.text = "Вечерняя смена с ${it.thursday_start_time.removeRange(4, 7)} до ${it.thursday_end_time.removeRange(4,7)}"
                        }
                    }else{
                        binding.thursdayScheduleTxt.text = "Выходной"
                        Glide.with(binding.iconThursday).load(R.drawable.free_day_icon).into(binding.iconThursday)
                    }

                    if(it.friday){
                        if(it.friday_start_time.equals("10:00:00")){
                            Glide.with(binding.iconFriday).load(R.drawable.sun_icon).into(binding.iconFriday)
                            binding.fridayScheduleTxt.text = "Дневная смена с ${it.friday_start_time.removeRange(4, 7)} до ${it.friday_end_time.removeRange(4,7)}"
                        }else{
                            Glide.with(binding.iconFriday).load(R.drawable.night_icon).into(binding.iconFriday)
                            binding.fridayScheduleTxt.text = "Вечерняя смена с ${it.friday_start_time.removeRange(4, 7)} до ${it.friday_end_time.removeRange(4,7)}"
                        }
                    }else{
                        binding.fridayScheduleTxt.text = "Выходной"
                        Glide.with(binding.iconFriday).load(R.drawable.free_day_icon).into(binding.iconFriday)
                    }

                    if(it.saturday){
                        if(it.saturday_start_time.equals("10:00:00")){
                            Glide.with(binding.iconSaturday).load(R.drawable.sun_icon).into(binding.iconSaturday)
                            binding.saturdayScheduleTxt.text = "Дневная смена с ${it.saturday_start_time.removeRange(4, 7)} до ${it.saturday_end_time.removeRange(4,7)}"
                        }else{
                            Glide.with(binding.iconSaturday).load(R.drawable.night_icon).into(binding.iconSaturday)
                            binding.saturdayScheduleTxt.text = "Вечерняя смена с ${it.saturday_start_time.removeRange(4, 7)} до ${it.saturday_end_time.removeRange(4,7)}"
                        }
                    }else{
                        binding.saturdayScheduleTxt.text = "Выходной"
                        Glide.with(binding.iconSaturday).load(R.drawable.free_day_icon).into(binding.iconSaturday)
                    }

                    if(it.sunday){
                        if(it.sunday_start_time.equals("10:00:00")){
                            Glide.with(binding.iconSunday).load(R.drawable.sun_icon).into(binding.iconSunday)
                            binding.sundayScheduleTxt.text = "Дневная смена с ${it.sunday_start_time.removeRange(4, 7)} до ${it.sunday_end_time.removeRange(4,7)}"
                        }else{
                            Glide.with(binding.iconSunday).load(R.drawable.night_icon).into(binding.iconSunday)
                            binding.sundayScheduleTxt.text = "Вечерняя смена с ${it.sunday_start_time.removeRange(4, 7)} до ${it.sunday_end_time.removeRange(4,7)}"
                        }
                    }else{
                        binding.sundayScheduleTxt.text = "Выходной"
                        Glide.with(binding.iconSunday).load(R.drawable.free_day_icon).into(binding.iconSunday)
                    }


                }
            }else if (it is NetworkStatus.Error){
                Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

}