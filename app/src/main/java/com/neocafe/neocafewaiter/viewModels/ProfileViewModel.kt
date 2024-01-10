package com.neocafe.neocafewaiter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafewaiter.entities.profileWaiter.WaiterProfileResponse
import com.neocafe.neocafewaiter.model.api.retrofit.Resource
import com.neocafe.neocafewaiter.model.repositories.ProfileRepository
import kotlinx.coroutines.launch

class ProfileViewModel(private val repository: ProfileRepository): ViewModel() {

    val profileResponse: MutableLiveData<Resource<WaiterProfileResponse>> = MutableLiveData()

    fun getProfile(){
        viewModelScope.launch {
            profileResponse.postValue(Resource.Loading())
            val response = repository.getProfile()
            if(response.isSuccessful){
                response.body()?.let {
                    profileResponse.postValue(Resource.Success(it))
                }
            }else{
                profileResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

}