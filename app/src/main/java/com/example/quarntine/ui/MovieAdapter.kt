@file:Suppress("DEPRECATION")

package com.example.quarntine.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.quarntine.model.Movies
import com.example.quarntine.R
import com.example.quarntine.databinding.ListUsersBinding
import com.example.quarntine.util.StorageUtil

class MovieAdapter(val context:Context, private var movieList: List<Movies>) :
    RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val binding =ListUsersBinding.inflate(layoutInflater,parent,false)
        return MovieViewHolder(binding)


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setList(list: List<Movies>) {
        this.movieList = list
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {


        holder.bind(movieList[position],context)
    }


    class MovieViewHolder(private val binding:ListUsersBinding) : RecyclerView.ViewHolder(binding.root) {




        fun bind(it: Movies,context:Context) {

            binding.movie = it
            val title = itemView.findViewById(R.id.name) as TextView
            val name = itemView.findViewById<TextView>(R.id.titles)
            val imageView = itemView.findViewById(R.id.Profile_image) as ImageView

            title.text = it.name
            name.text = it.bio
//            if (it.image!=null)
//            {
//                Glide.with(context).load(it.image!!)
//                    .placeholder(R.drawable.ic_hello)
//                    .load(StorageUtil.pathToReference(it.image!!))
//                    .into(imageView)
//            }




        }
    }
}

