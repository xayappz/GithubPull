package com.xayappz.githubpull.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.xayappz.githubpull.R
import com.xayappz.githubpull.adapter.CustomAdapter
import com.xayappz.githubpull.helper.RepoPull
import com.xayappz.githubpull.viewmodel.PullVM
import kotlinx.android.synthetic.main.activity_search_result.*


class SearchResultActivity : AppCompatActivity() {
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var recyclerView: RecyclerView
    private lateinit var pullAdapter: CustomAdapter
    private var pageNumber = 1
    private var totalItemsCount = 0
    private var firstVisibleItemsCount = 0
    private var visibleItemsCount = 0
    private var previousTotal = 0
    private var loading = true
    private var fillPullList: ArrayList<RepoPull> = ArrayList()
    private var userName: String = ""
    private var repoName: String = ""
    private var isEnd = false

    private lateinit var myViewModel: PullVM
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initialize()

        myViewModel = ViewModelProviders.of(this).get(PullVM::class.java)

        myViewModel.endofList.observe(this, {
            if (it == true) {
                isEnd = true
                progressBar.visibility = View.GONE
                Toast.makeText(this@SearchResultActivity, "All PR Fetched", Toast.LENGTH_SHORT)
                    .show()

            }

        })

        myViewModel.status.observe(this, {
            if (it == false) {
                showError(getString(R.string.no_net))

            }
        })

        myViewModel.getPullDataFromVM().observe(this, {

            if (it != null) {
                listRepos(it)
            } else {

                showError(getString(R.string.nothing_found))
            }
        })

        getDataPull(userName, repoName)

        loadNextData()

    }

    private fun initialize() {
        setContentView(R.layout.activity_search_result)
        recyclerView = findViewById(R.id.repoRecView)
        layoutManager = LinearLayoutManager(this)

        getSearchQuery()

    }

    private fun getSearchQuery() {
        userName = intent.getStringExtra("owner").toString()
        repoName = intent.getStringExtra("repo").toString()
        populatePullRv()

    }

    private fun populatePullRv() {
        recyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        recyclerView.layoutManager = layoutManager
        pullAdapter = CustomAdapter(this, fillPullList)
        recyclerView.adapter = pullAdapter
        progressBar.visibility = View.VISIBLE
    }

    private fun loadNextData() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)

                val mLayoutManger = recyclerView.layoutManager as LinearLayoutManager
                visibleItemsCount = mLayoutManger.childCount
                totalItemsCount = mLayoutManger.itemCount
                firstVisibleItemsCount = mLayoutManger.findFirstVisibleItemPosition()

                myViewModel.status.observe(this@SearchResultActivity, {
                    if (it == false) {
                        showError(getString(R.string.no_net))
                        return@observe

                    }
                })

                if (loading) {
                    if (totalItemsCount > previousTotal) {
                        previousTotal = totalItemsCount
                        pageNumber++
                        loading = false
                        progressBar.visibility = View.GONE
                    }
                }

                if (!loading && (firstVisibleItemsCount + visibleItemsCount) >= totalItemsCount) {
                    getDataPull(userName, repoName)
                    loading = true
                    progressBar.visibility = View.VISIBLE
                }


            }

        })
    }

    private fun getDataPull(username: String?, reponame: String?) {
        myViewModel.getPullList(username.toString(), reponame.toString(), pageNumber)

    }

    private fun showError(s: String) {
        var snackActonText = ""

        snackActonText = if (s == getString(R.string.no_net)) {
            "Retry"
        } else {
            "Search Again"
        }
        progressBar.visibility = View.GONE
        val theView =
            this@SearchResultActivity.findViewById<View>(android.R.id.content)

        val snackbar = Snackbar
            .make(theView, s, Snackbar.LENGTH_LONG)
            .setAction(snackActonText) {

                when (s) {
                    getString(R.string.no_net) -> {
                        retryAgain()
                    }
                    getString(R.string.nothing_found) -> {
                        onBackPressed()
                    }
                }
            }

        snackbar.show()
    }

    private fun retryAgain() {
        myViewModel.getPullList(userName, repoName, pageNumber)

    }


    @SuppressLint("NotifyDataSetChanged")
    fun listRepos(repos: List<RepoPull>) {
        if (!isEnd) {
            progressBar.visibility = View.GONE
            fillPullList.addAll(repos)
            pullAdapter.notifyDataSetChanged()
        }
    }
}







