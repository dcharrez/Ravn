package com.example.ravn

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.github.UsersQuery
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.OkHttpClient

class MainActivity : AppCompatActivity() {

    val userList: ArrayList<User> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this

        github_users_recycler.layoutManager = LinearLayoutManager(this)


//        val client = setupApollo()
        val client = ApolloClientSetup().mApolloClient

        buttonSearch.setOnClickListener {
            //            progressBar2.visibility = View.VISIBLE
            client.query(
                UsersQuery
                    .builder()
                    .queryText(github_user_input.text.toString())
                    .build()
            )
                .enqueue(object : ApolloCall.Callback<UsersQuery.Data>() {
                    override fun onFailure(e: ApolloException) {
//                        progressBar2.visibility = View.GONE
                    }

                    override fun onResponse(response: Response<UsersQuery.Data>) {
                        runOnUiThread {
                            //                            progressBar2.visibility = View.GONE

                            val queryResult = response.data()?.search()?.edges()

                            queryResult!!.forEach {
                                var nodeString = it.node()
                                userList.add(HelperJSON.getUser(HelperJSON.toJsonString(nodeString)))
                            }
                            github_users_recycler.adapter =
                                UserAdapter(userList, context, { partItem: User -> partItemClicked(partItem) })
                        }
                    }
                })
        }

    }

    object HelperJSON {
        private var gson: Gson = Gson()
        fun getUser(jsonString: String): User {
            Log.e("JSONHelper ", "Enter: " + jsonString)
            return gson.fromJson(jsonString, User::class.java)
        }

        fun toJsonString(simpleObject: Any?): String = gson.toJson(simpleObject)
    }

    private fun partItemClicked(partItem: User) {
        val intent = Intent(this@MainActivity, UserRepositoryActivity::class.java)
        intent.putExtra("user_name", partItem.login)
        startActivity(intent)
    }
}
