package com.sidratul.submission1bfaa.api

import com.sidratul.submission1bfaa.BuildConfig
import com.sidratul.submission1bfaa.data.DetailUserResponse
import com.sidratul.submission1bfaa.data.GithubResponse
import com.sidratul.submission1bfaa.data.SearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @Headers("Authorization: token" + BuildConfig.KEY)
    @GET("users")
    fun getUsers(): Call<List<GithubResponse>>

    @Headers("Authorization: token" + BuildConfig.KEY)
    @GET("search/users")
    fun searchUsers(@Query("q") login: String): Call<SearchResponse>

    @Headers("Authorization: token" + BuildConfig.KEY)
    @GET("users/{username}")
    fun getDetailUser(@Path("username") username: String): Call<DetailUserResponse>

    @Headers("Authorization: token" + BuildConfig.KEY)
    @GET("users/{login}/followers")
    fun getUserFollowers(@Path("login")login: String): Call<List<GithubResponse>>

    @Headers("Authorization: token" + BuildConfig.KEY)
    @GET("users/{login}/following")
    fun getUserFollowing(@Path("login")login: String): Call<List<GithubResponse>>

}