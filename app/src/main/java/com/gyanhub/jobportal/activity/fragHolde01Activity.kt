package com.gyanhub.jobportal.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gyanhub.jobportal.R
import com.gyanhub.jobportal.databinding.ActivityFragHolde01Binding
import com.gyanhub.jobportal.fragments.PostNewJobFragment

class fragHolde01Activity : AppCompatActivity() {
    private lateinit var binding: ActivityFragHolde01Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragHolde01Binding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpToolBar()
        binding.topAppBar.title = "Job Post"



        setFragment(PostNewJobFragment())

    }

    private fun setFragment(fragment: Fragment) {
        val fragmentTransient = supportFragmentManager.beginTransaction()
        fragmentTransient.replace(R.id.frameLayout1, fragment)
        fragmentTransient.commit()
    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    fun finshActivity() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

}