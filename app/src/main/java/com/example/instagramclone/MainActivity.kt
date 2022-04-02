package com.example.instagramclone


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.instagramclone.fragments.ComposeFragment
import com.example.instagramclone.fragments.FeedFragment
import com.example.instagramclone.fragments.ProfileFragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.parse.*
import java.io.File
import android.content.Intent
import android.content.Context
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import com.google.android.material.internal.ContextUtils.getActivity
import java.security.AccessController.getContext

class MainActivity : AppCompatActivity() {
    val TAG = "Main Page"
    val CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 1034
    val photoFileName = "photo.jpg"
    var photoFile: File? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val fragmentManager: FragmentManager = supportFragmentManager


        var fragmentToShow: Fragment? = null
        findViewById<BottomNavigationView>(R.id.bottom_navigation).setOnItemSelectedListener {
            item ->

            var fragmentToShow: Fragment? = null
            when (item.itemId) {
                R.id.action_home -> {
                    fragmentToShow = FeedFragment()
                }
                R.id.action_profile -> {
                    fragmentToShow = ProfileFragment()
                }
                // Show Composed Fragments
                R.id.action_compose -> {
                    fragmentToShow = ComposeFragment()
                    
                }
                // Log Out User
                R.id.action_signout -> run {
                    ParseUser.logOut()
                    val intent = Intent(this, LoginActivity::class.java)
                    startActivity(intent)
                }

            }
            if (fragmentToShow != null) {
                fragmentManager.beginTransaction().replace(R.id.flContainer, fragmentToShow).commit()
            }

            // Return True - we handled interaction
            true
        }

        findViewById<BottomNavigationView>(R.id.bottom_navigation).selectedItemId = R.id.action_home

    }




}