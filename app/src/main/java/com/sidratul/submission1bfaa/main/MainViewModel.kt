package com.sidratul.submission1bfaa.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sidratul.submission1bfaa.api.ApiConfig
import com.sidratul.submission1bfaa.data.GithubResponse
import com.sidratul.submission1bfaa.data.SearchResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel : ViewModel() {

    private val _listUsers = MutableLiveData<List<GithubResponse>>()
    val listUsers : LiveData<List<GithubResponse>>
        get() =_listUsers

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() =_isLoading

    init {
        getUsers()
    }

    private fun getUsers(){
        val client = ApiConfig.getApiService().getUsers()
        client.enqueue(object: Callback<List<GithubResponse>> {
            override fun onResponse(call: Call<List<GithubResponse>>, response: Response<List<GithubResponse>>) {
                _isLoading.value = false
                if(response.isSuccessful && response.body() != null){
                    _listUsers.value = response.body()
                }else{
                    Log.e(TAG, "onResponse: ${response}")
                }
            }
            override fun onFailure(call: Call<List<GithubResponse>>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun searchUser(query: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().searchUsers(query)
        client.enqueue(object: Callback<SearchResponse> {
            override fun onResponse(call: Call<SearchResponse>, response: Response<SearchResponse>) {
                _isLoading.value = false
                if(response.isSuccessful && response.body() != null){
                    _listUsers.value = response.body()!!.items
                }else{
                    Log.e(MainViewModel.TAG, "onResponse: ${response}")
                }
            }
            override fun onFailure(call: Call<SearchResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(MainViewModel.TAG, "onFailure: ${t.message}")
            }
        })
    }

    companion object{
        private const val TAG = "MainViewModel"
    }
}