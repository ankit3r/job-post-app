package com.gyanhub.jobportal.activity

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.gyanhub.jobportal.R
import com.gyanhub.jobportal.ViewModel.JobFactory
import com.gyanhub.jobportal.ViewModel.JobViewModel
import com.gyanhub.jobportal.repository.JobRepository
import com.gyanhub.jobportal.room.DataBase

@Suppress("DEPRECATION")
class loginActivity : AppCompatActivity() {
    lateinit var loginBtn: Button
    lateinit var signUpBtn: Button
    lateinit var userViewModel: JobViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        loginBtn = findViewById(R.id.btnLogin)
        signUpBtn = findViewById(R.id.btnSignup)


        userViewModel =
            ViewModelProvider(
                this,
                JobFactory(
                    JobRepository(
                        DataBase.getDatabase(this).Dao()
                    )
                )
            ).get(JobViewModel::class.java)

        


        loginBtn.setOnClickListener {
            val intent = Intent(this, FragmentHolderActivity::class.java)
            intent.putExtra("data", "Log In")
            startActivity(intent)
            finish()
        }
        signUpBtn.setOnClickListener {
            val intent = Intent(this, FragmentHolderActivity::class.java)
            intent.putExtra("data", "Sign Up")
            startActivity(intent)
            finish()
        }

    }

}