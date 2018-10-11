package com.sri.testsen.view

import android.content.Context
import android.content.res.Configuration
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentTransaction
import android.widget.Toast
import com.sri.testsen.R

class MainActivity : AppCompatActivity() {

    // initialising fragments
    private var leftFragment: LeftFragment? = null
    private var rightFragment: RightFragment? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {
            if (savedInstanceState == null) {
                Toast.makeText(baseContext, "Loading....", Toast.LENGTH_SHORT).show()
                val fragmentManager: FragmentManager = supportFragmentManager
                val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
                fragmentTransaction.add(R.id.left_fragment, LeftFragment(), "leftfragment")
                fragmentTransaction.add(R.id.right_fragment, RightFragment(), "rightfragment")
                fragmentTransaction.commit()
            } else {
                leftFragment = supportFragmentManager.findFragmentByTag("leftfragment") as LeftFragment
                rightFragment = supportFragmentManager.findFragmentByTag("rightfragment") as RightFragment
            }
        } else {
            if (savedInstanceState == null) {
                Toast.makeText(baseContext, "Please check your internet connection and try again", Toast.LENGTH_LONG).show()
            }
        }
    }

    // this function is used to handle orientation changes. It will display right fragment is the orientation is landscape and it will hide right fragment if orientation is portrait
    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)
        if (newConfig != null) {
            if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
                val fragmentManager = this.supportFragmentManager
                fragmentManager.beginTransaction()
                        .show(fragmentManager.findFragmentById(R.id.right_fragment)!!)
                        .show(fragmentManager.findFragmentById(R.id.left_fragment)!!)
                        .commit()
            } else {
                val fragmentManager = this.supportFragmentManager
                fragmentManager.beginTransaction()
                        .hide(fragmentManager.findFragmentById(R.id.right_fragment)!!)
                        .show(fragmentManager.findFragmentById(R.id.left_fragment)!!)
                        .commit()
            }
        }
    }
}