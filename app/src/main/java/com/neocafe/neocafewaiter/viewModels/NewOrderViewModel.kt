package com.neocafe.neocafewaiter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafewaiter.entities.category.CategoryResonse
import com.neocafe.neocafewaiter.entities.menu.MenuResponse
import com.neocafe.neocafewaiter.entities.table.TableResponse
import com.neocafe.neocafewaiter.model.api.retrofit.Resource
import com.neocafe.neocafewaiter.model.repositories.NewOrderRepository
import kotlinx.coroutines.launch

class NewOrderViewModel(private val repository: NewOrderRepository): ViewModel() {

    val tablesResponse: MutableLiveData<Resource<List<TableResponse>>> = MutableLiveData()

    val categoryResponse: MutableLiveData<Resource<List<CategoryResonse>>> = MutableLiveData()

    val menuResponse: MutableLiveData<Resource<List<MenuResponse>>> = MutableLiveData()

    fun getTables(){
        viewModelScope.launch {
            tablesResponse.postValue(Resource.Loading())
            val response = repository.getTables()
            if (response.isSuccessful){
                response.body()?.let {
                    tablesResponse.postValue(Resource.Success(it))
                }
            }else{
                tablesResponse.postValue(Resource.Error(response.message()))
            }
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            categoryResponse.postValue(Resource.Loading())
            val response = repository.getCategories()
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
            val response = repository.getMenu(slug)
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