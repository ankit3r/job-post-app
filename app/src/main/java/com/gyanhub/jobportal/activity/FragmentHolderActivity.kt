package com.gyanhub.jobportal.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.gyanhub.jobportal.R
import com.gyanhub.jobportal.databinding.ActivityFragmentHolderBinding
import com.gyanhub.jobportal.fragments.PostNewJobFragment
import com.gyanhub.jobportal.fragments.SignInFragment
import com.gyanhub.jobportal.fragments.SingUpFragment

class FragmentHolderActivity : AppCompatActivity() {
    lateinit var title: String
    lateinit var binding: ActivityFragmentHolderBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFragmentHolderBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolBar()
        title = intent.getStringExtra("data").toString()
        binding.topAppBar.title = title

        when(title){
            "Log In" -> setFragment(SignInFragment())
            "Sign Up" ->  setFragment(SingUpFragment())
        }

    }
     fun setFragment(fragment: Fragment) {
        val fragmentTransient = supportFragmentManager.beginTransaction()
        fragmentTransient.replace(R.id.frameLayout, fragment)
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
    fun BackToLiginActivity() {
        startActivity(Intent(this, loginActivity::class.java))
        finish()
    }

    override fun onBackPressed() {
        BackToLiginActivity()
        super.onBackPressed()
    }
}