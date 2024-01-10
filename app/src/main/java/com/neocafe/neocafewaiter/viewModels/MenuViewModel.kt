package com.neocafe.neocafewaiter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafewaiter.entities.category.CategoryResonse
import com.neocafe.neocafewaiter.entities.menu.MenuResponse
import com.neocafe.neocafewaiter.model.api.retrofit.Resource
import com.neocafe.neocafewaiter.model.repositories.MenuRepository
import kotlinx.coroutines.launch

class MenuViewModel(private val menuRepository: MenuRepository): ViewModel() {

    val categoryResponse: MutableLiveData<Resource<List<CategoryResonse>>> = MutableLiveData()

    val menuResponse: MutableLiveData<Resource<List<MenuResponse>>> = MutableLiveData()

    fun getCategories(){
        viewModelScope.launch {
            categoryResponse.postValue(Resource.Loading())
            val response = menuRepository.getCategories()
            if(response.isSuccessful){
                response.body()?.let {
                    categoryResponse.postValue(Resource.Success(it))
                }
            }else{
                categoryResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

    fun getMenu(slug: String){
        viewModelScope.launch {
            menuResponse.postValue(Resource.Loading())
            val response = menuRepository.getMenu(slug)
            if(response.isSuccessful){
                response.body()?.let {
                    menuResponse.postValue(Resource.Success(it))
                }
            }else{
                menuResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

}