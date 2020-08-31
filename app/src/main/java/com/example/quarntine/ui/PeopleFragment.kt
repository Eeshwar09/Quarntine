package com.example.quarntine.ui


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.quarntine.R


class PeopleFragment : Fragment() {
    lateinit var mainActivityViewModel: MainActivityViewModel
    private var adapters: MovieAdapter? = null


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_people, container, false)
        val mRecyclerView = view.findViewById(R.id.recycler_view_people) as RecyclerView
        mainActivityViewModel = ViewModelProviders.of(requireActivity()).get(MainActivityViewModel::class.java)
        mainActivityViewModel.apply {
            addData().observe(this@PeopleFragment, Observer {
                val activity = activity
                if (activity != null) {
                    adapters = MovieAdapter(requireActivity(), it)
                    adapters?.setList(it)
                    mRecyclerView.layoutManager = LinearLayoutManager(activity)
                    mRecyclerView.adapter = adapters
                    adapters?.notifyDataSetChanged()

                }

            })

        }


        return view
    }


}


