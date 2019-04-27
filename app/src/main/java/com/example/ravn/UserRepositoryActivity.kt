package com.example.ravn

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.github_api.FindQuery
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import kotlinx.android.synthetic.main.activity_user_repository.*
import kotlinx.android.synthetic.main.activity_main.*


class UserRepositoryActivity : AppCompatActivity() {

    val repositories: ArrayList<Repository> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_repository)
        val username: String = intent.getStringExtra("user_name")
        val context = this

        repository_recycler.layoutManager = LinearLayoutManager(this)

        repository_recycler.addItemDecoration(
            DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        )

        val client = ApolloClientSetup().mApolloClient


        client.query(
            FindQuery
                .builder()
                .user_name(username)
                .build()
        )
            .enqueue(object : ApolloCall.Callback<FindQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    Log.d("Error Message: ", e.message.toString())
                    Log.d("Trace Error: ", e.printStackTrace().toString())
                }

                override fun onResponse(response: Response<FindQuery.Data>) {
                    runOnUiThread {

                        val repos = response.data()?.user()?.repositories()?.edges()
                        repos!!.forEach {
                            repositories.add(
                                Repository(
                                    repositoryName = it.node()!!.name(),
                                    repositoryDescription = it.node()!!.description().toString(),
                                    pullRequestCount = it.node()!!.pullRequests().totalCount()
                                )
                            )
                        }
                        repository_recycler.adapter = RepositoryAdapter(repositories, context)
                    }
                }
            })
    }

}