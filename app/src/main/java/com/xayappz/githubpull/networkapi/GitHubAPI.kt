package com.xayappz.githubpull.networkapi
import android.util.Log
import com.xayappz.githubpull.interfaces.ApiEndpoints
import com.xayappz.githubpull.helper.RepoPull
import retrofit2.Callback
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


object GitHubRetriever {
    val serviceCall: ApiEndpoints

    init {
        val retrofit = Retrofit.Builder().baseUrl("https://api.github.com/")
            .addConverterFactory(GsonConverterFactory.create()).build()
        serviceCall = retrofit.create(ApiEndpoints::class.java)
    }


    fun userRepos(
        callback: Callback<List<RepoPull>>,
        ownerName: String,
        repoName: String,
        pageNum: Int
    ) {
        val call = serviceCall.getPullFor(ownerName, repoName,per_page = 25,pageNum)
        Log.d("url", call.request().url().toString());
        call.enqueue(callback)

    }

}