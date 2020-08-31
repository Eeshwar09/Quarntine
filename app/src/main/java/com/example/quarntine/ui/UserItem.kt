@file:Suppress("DEPRECATION")

package com.example.quarntine.ui

import android.annotation.SuppressLint
import android.content.Context
import com.bumptech.glide.Glide
import com.example.quarntine.R
import com.example.quarntine.model.Movies
import com.example.quarntine.util.StorageUtil
import com.xwray.groupie.kotlinandroidextensions.Item
import com.xwray.groupie.kotlinandroidextensions.ViewHolder
import kotlinx.android.synthetic.main.list_users.*

class UserItem(val person:Movies, val userId:String,
               private val context: Context): Item() {
    @SuppressLint("CheckResult")
    override fun bind(viewHolder: ViewHolder, position: Int) {
        viewHolder.titles.text =person.name
        viewHolder.name.text = person.bio
         if (person.image != null){
             Glide.with(context)
                 .load(StorageUtil.pathToReference(person.image!!))
                 .placeholder(R.drawable.ic_hello)
                 .into(viewHolder.Profile_image)

         }

    }

    override fun getLayout() = R.layout.list_users
}