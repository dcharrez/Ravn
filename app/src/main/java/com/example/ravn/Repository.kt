package com.example.ravn

data class Repository(
    val githubUserName: String,
    val url: String,
    val description : String,
    val pullRequestCount : Int
)