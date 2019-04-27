package com.example.ravn

import com.apollographql.apollo.ApolloClient
import okhttp3.OkHttpClient

class ApolloClientSetup {

    val baseURL: String = "https://api.github.com/graphql"
    val mApolloClient: ApolloClient = setupApollo()

    private fun setupApollo(): ApolloClient {

        val okHttp = OkHttpClient
            .Builder()
            .addInterceptor { chain ->
                val original = chain.request()
                val builder = original.newBuilder().method(
                    original.method(),
                    original.body()
                )
                builder.addHeader(
                    "Authorization"
                    , "Bearer TOKEN"
                )
                chain.proceed(builder.build())
            }
            .build()
        return ApolloClient.builder()
            .serverUrl(baseURL)
            .okHttpClient(okHttp)
            .build()
    }


}