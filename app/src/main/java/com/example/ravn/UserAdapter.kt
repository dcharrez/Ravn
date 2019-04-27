package com.example.ravn

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_github_user.view.*
import java.util.*

class UserAdapter(val items: ArrayList<User>, val context: Context, val clickListener: (User) -> Unit) :
    RecyclerView.Adapter<UserHolder>() {

    override fun onBindViewHolder(holder: UserHolder, position: Int) {
        holder.nameLocation.text = (items[position].name + ", " + items[position].location)
        holder.login.text = items[position].login
        Picasso.get().load(items[position].avatarUrl).into(holder.avatar)
        (holder).bind(items[position], clickListener)

    }

    override fun onCreateViewHolder(parent: ViewGroup, position: Int): UserHolder {
        return UserHolder(LayoutInflater.from(context).inflate(R.layout.item_github_user, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


class UserHolder(view: View) : RecyclerView.ViewHolder(view) {
    fun bind(part: User, clickListener: (User) -> Unit) {
        itemView.setOnClickListener { clickListener(part) }
    }

    val avatar: ImageView = view.profile_image
    val nameLocation: TextView = view.github_user_location
    val login: TextView = view.login
}