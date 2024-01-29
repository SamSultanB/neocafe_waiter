package com.neocafe.neocafewaiter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafewaiter.entities.category.CategoryResonse
import com.neocafe.neocafewaiter.entities.menu.MenuResponse
import com.neocafe.neocafewaiter.model.api.retrofit.NetworkStatus
import com.neocafe.neocafewaiter.model.repositories.MenuRepository
import kotlinx.coroutines.launch

class MenuViewModel(private val menuRepository: MenuRepository): ViewModel() {

    val categoryResponse: MutableLiveData<NetworkStatus<List<CategoryResonse>>> = MutableLiveData()

    val menuResponse: MutableLiveData<NetworkStatus<List<MenuResponse>>> = MutableLiveData()

    fun getCategories(){
        viewModelScope.launch {
            categoryResponse.postValue(NetworkStatus.Loading())
            val response = menuRepository.getCategories()
            if(response.isSuccessful){
                response.body()?.let {
                    categoryResponse.postValue(NetworkStatus.Success(it))
                }
            }else{
                categoryResponse.postValue(NetworkStatus.Error(response.message()))
            }
        }
    }

    fun getMenu(slug: String){
        viewModelScope.launch {
            menuResponse.postValue(NetworkStatus.Loading())
            val response = menuRepository.getMenu(slug)
            if(response.isSuccessful){
                response.body()?.let {
                    menuResponse.postValue(NetworkStatus.Success(it))
                }
            }else{
                menuResponse.postValue(NetworkStatus.Error(response.message()))
            }
        }
    }

}