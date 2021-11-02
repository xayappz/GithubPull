package com.xayappz.githubpull.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xayappz.githubpull.helper.RepoPull
import com.xayappz.githubpull.networkapi.GitHubRetriever
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PullVM : ViewModel() {
    var pullList: MutableLiveData<List<RepoPull>>
    var status = MutableLiveData<Boolean?>()
    var endofList = MutableLiveData<Boolean?>()

    init {

        pullList = MutableLiveData()
    }

    fun getPullDataFromVM(): MutableLiveData<List<RepoPull>> {

        return pullList
    }

    fun getPullList(ownerName: String, repoName: String, pgNo: Int) {

        val retriever = GitHubRetriever


        val callback = object : Callback<List<RepoPull>> {
            override fun onFailure(call: Call<List<RepoPull>>, t: Throwable) {
                status.value = false
            }

            override fun onResponse(
                call: Call<List<RepoPull>>,
                response: Response<List<RepoPull>>
            ) {
                if (response.body()?.size == 0) {
                    endofList.value = true
                }
                if (response.code() == 404) {
                    pullList.postValue(null)

                } else {
                    status.value = true
                    val repos = response.body()
                    if (repos != null) {
                        pullList.postValue(repos)
                    }
                }
            }

        }

        retriever.userRepos(
            callback,
            ownerName,
            repoName,
            pgNo
        )
    }
}