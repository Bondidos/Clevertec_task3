package com.bondidos.clevertec_task1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle

class MainActivity : AppCompatActivity(R.layout.activity_main){

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)
        supportFragmentManager
            .beginTransaction()
            .remove(FirstFragment())
            .commit()
    }
}