@file:Suppress("DEPRECATION")

package com.example.quarntine.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import com.example.quarntine.R
import com.example.quarntine.databinding.ActivityMainBinding


@Suppress("TYPEALIAS_EXPANSION_DEPRECATION")
class MainActivity : AppCompatActivity() {

    private var firestoreViewModel = MainActivityViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activityMainBinding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        activityMainBinding.viemodel = firestoreViewModel
        setSupportActionBar(toolbar)
        replaceFragement(PeopleFragment())
        nav_view.setOnNavigationItemSelectedListener {
            when (it.itemId) {
                R.id.navigation_people -> {
                    replaceFragement(PeopleFragment())
                    true
                }
                R.id.navigation_my_account -> {
                    replaceFragement(AccountFragment())
                    nav_view.visibility = View.GONE
                    true
                }
                else -> false
            }
        }


    }


    private fun replaceFragement(newFrag: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragmentholder, newFrag)
            commit()
            fragmentholder.visibility = View.VISIBLE
        }
    }
}
