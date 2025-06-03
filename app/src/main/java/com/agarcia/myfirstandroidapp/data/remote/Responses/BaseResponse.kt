package com.agarcia.myfirstandroidapp.data.remote.Responses

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<T>,
    @SerializedName("total_pages")
    val total_pages: Int,
    @SerializedName("total_results")
    val total_results: Int
)