package com.bondidos.clevertec_task1

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.util.AttributeSet
import android.view.View
import android.widget.Toast
import com.bondidos.clevertec_task1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(R.layout.activity_main),Navigation{



    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)

    }

    override fun navigateFirstFragment() {
        TODO("Not yet implemented")
    }

    override fun navigateDetailsFragment(item: Bundle) {
        supportFragmentManager
            .beginTransaction()
            .setCustomAnimations(
                R.animator.flip_in,
                R.animator.flip_out
            )
            .replace(R.id.container, DetailsFragment.newInstance(item))
            .commit()
    }
}