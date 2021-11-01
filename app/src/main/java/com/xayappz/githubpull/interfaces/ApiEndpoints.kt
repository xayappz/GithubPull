package com.xayappz.githubpull.interfaces;

import com.xayappz.githubpull.helper.RepoPull
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiEndpoints {
     @GET("repos/{owner}/{repo}/pulls?")
     fun getPullFor(
         @Path("owner") owner: String,
         @Path("repo") repoName: String,
         @Query("per_page") per_page: Int,
         @Query("page") page: Int,

         ): Call<List<RepoPull>>
}
