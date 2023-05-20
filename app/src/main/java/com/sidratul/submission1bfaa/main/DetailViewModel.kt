package com.sidratul.submission1bfaa.main

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sidratul.submission1bfaa.api.ApiConfig
import com.sidratul.submission1bfaa.data.DetailUserResponse
import com.sidratul.submission1bfaa.data.GithubResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DetailViewModel() : ViewModel() {

    private val _user = MutableLiveData<GithubResponse>()
    val user : LiveData<GithubResponse>
        get() =_user

    private val _DetailUser = MutableLiveData<DetailUserResponse>()
    val DetailUser : LiveData<DetailUserResponse>
        get() =_DetailUser

    private val _userFollower = MutableLiveData<List<GithubResponse>>()
    val userFollower : LiveData<List<GithubResponse>>
        get() =_userFollower

    private val _userFollowing = MutableLiveData<List<GithubResponse>>()
    val userFollowing : LiveData<List<GithubResponse>>
        get() =_userFollowing

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading : LiveData<Boolean>
        get() =_isLoading

    private val _isLoadingFollow = MutableLiveData<Boolean>()
    val isLoadingFollow : LiveData<Boolean>
        get() =_isLoadingFollow

    init {

    }

    fun getUser(login: String){
        getDetailUser(login)
        getUserFollower(login)
        getUserFollowing(login)
    }


    private fun getDetailUser(username: String){
        _isLoading.value = true
        val client = ApiConfig.getApiService().getDetailUser(username)
        client.enqueue(object: Callback<DetailUserResponse> {
            override fun onResponse(call: Call<DetailUserResponse>, response: Response<DetailUserResponse>) {
                _isLoading.value = false
                if(response.isSuccessful && response.body() != null){
                    _DetailUser.value = response.body()
                }else{
                    Log.e(TAG, "onResponse: ${response}")
                }
            }
            override fun onFailure(call: Call<DetailUserResponse>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getUserFollower(login: String){
        val client = ApiConfig.getApiService().getUserFollowers(login)
        client.enqueue(object: Callback<List<GithubResponse>> {
            override fun onResponse(call: Call<List<GithubResponse>>, response: Response<List<GithubResponse>>) {
                _isLoading.value = false
                if(response.isSuccessful && response.body() != null){
                    _userFollower.value = response.body()
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

    fun getUserFollowing(login: String){
        val client = ApiConfig.getApiService().getUserFollowing(login)
        client.enqueue(object: Callback<List<GithubResponse>> {
            override fun onResponse(call: Call<List<GithubResponse>>, response: Response<List<GithubResponse>>) {
                _isLoading.value = false
                if(response.isSuccessful && response.body() != null){
                    _userFollowing.value = response.body()
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

    companion object{
        private const val TAG = "DetailViewModel"
    }
}