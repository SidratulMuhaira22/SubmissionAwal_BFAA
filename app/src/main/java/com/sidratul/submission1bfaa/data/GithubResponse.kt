package com.sidratul.submission1bfaa.data

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class GithubResponse(

	@field:SerializedName("id")
	val id: Long,

	@field:SerializedName("login")
	val login: String,

	@field:SerializedName("avatar_url")
	val avatar_url: String
): Parcelable

data class SearchResponse(

	@field:SerializedName("total_count")
	val total_count: Long,

	@field:SerializedName("incomplete_results")
	val incomplete_results: Boolean,

	@field:SerializedName("items")
	val items: List<GithubResponse>
)