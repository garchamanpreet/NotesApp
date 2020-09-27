package com.manpreet.androidmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    private val TAG= "MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG, "onCreate: ")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Help to change the Action bar title as navigates to another destination
        setupActionBarWithNavController(findNavController(R.id.nav_host_fragment_container))

    }

    override fun onSupportNavigateUp(): Boolean {
        Log.d(TAG, "onSupportNavigateUp: ")
        val navController =findNavController(R.id.nav_host_fragment_container)
        return navController.navigateUp()||super.onSupportNavigateUp()
    }

}
