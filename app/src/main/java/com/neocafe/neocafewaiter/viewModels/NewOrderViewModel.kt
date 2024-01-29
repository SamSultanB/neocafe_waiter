package com.neocafe.neocafewaiter.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.neocafe.neocafewaiter.entities.category.CategoryResonse
import com.neocafe.neocafewaiter.entities.menu.MenuResponse
import com.neocafe.neocafewaiter.entities.table.TableResponse
import com.neocafe.neocafewaiter.model.api.retrofit.NetworkStatus
import com.neocafe.neocafewaiter.model.repositories.NewOrderRepository
import kotlinx.coroutines.launch

class NewOrderViewModel(private val repository: NewOrderRepository): ViewModel() {

    val tablesResponse: MutableLiveData<NetworkStatus<List<TableResponse>>> = MutableLiveData()

    val categoryResponse: MutableLiveData<NetworkStatus<List<CategoryResonse>>> = MutableLiveData()

    val menuResponse: MutableLiveData<NetworkStatus<List<MenuResponse>>> = MutableLiveData()

	//save state
	
    fun getTables(){
        viewModelScope.launch {
            tablesResponse.postValue(NetworkStatus.Loading())
            val response = repository.getTables()
            if (response.isSuccessful){
                response.body()?.let {
                    tablesResponse.postValue(NetworkStatus.Success(it))
                }
            }else{
                tablesResponse.postValue(NetworkStatus.Error(response.message()))
            }
        }
    }

    fun getCategories(){
        viewModelScope.launch {
            categoryResponse.postValue(NetworkStatus.Loading())
            val response = repository.getCategories()
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
            val response = repository.getMenu(slug)
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