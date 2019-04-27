package com.example.ravn

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.item_repository.view.*
import java.util.*

class RepositoryAdapter (val items : ArrayList<Repository>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.repositoryName.text = items[position].repositoryName
        holder.descriptionRepo.text = items[position].repositoryDescription
        holder.prcount.text = ("PR Count: " + items[position].pullRequestCount.toString())
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_repository, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    val repositoryName : TextView = view.repository_name
    val descriptionRepo : TextView = view.repository_description
    val prcount : TextView = view.repository_prcount
}
