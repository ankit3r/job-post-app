package com.gyanhub.jobportal.fragments


import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.gyanhub.jobportal.ViewModel.JobFactory
import com.gyanhub.jobportal.ViewModel.JobViewModel
import com.gyanhub.jobportal.activity.FragmentHolderActivity
import com.gyanhub.jobportal.activity.MainActivity
import com.gyanhub.jobportal.activity.loginActivity
import com.gyanhub.jobportal.databinding.FragmentSingUpBinding
import com.gyanhub.jobportal.repository.JobRepository
import com.gyanhub.jobportal.room.DataBase
import com.gyanhub.jobportal.room.UserEntity


class SingUpFragment : Fragment() {
    lateinit var binding: FragmentSingUpBinding
    lateinit var userViewModel: JobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSingUpBinding.inflate(inflater, container, false)

        userViewModel =
            ViewModelProvider(
                this,
                JobFactory(
                    JobRepository(
                        DataBase.getDatabase(context as Activity).Dao()
                    )
                )
            ).get(JobViewModel::class.java)

        binding.btnSignUp.setOnClickListener {
            if (binding.eTxtFullName.text.toString() == "") {
                binding.eTxtFullName.error =
                    "Please Enter Name"
                return@setOnClickListener
            }
            if (binding.eTxtEmail.text.toString() == "") {
                binding.eTxtEmail.error =
                    "Please Enter Email"
                return@setOnClickListener
            }
            if (binding.eTxtPass.text.toString() == "" || binding.eTxtPass.text.toString().length <= 4) {
                binding.eTxtPass.error =
                    "Please Enter Strong Password"
                return@setOnClickListener
            }
            signUp(
                binding.eTxtFullName.text.toString(),
                binding.eTxtEmail.text.toString(),
                binding.eTxtPass.text.toString()
            )
        }
        return binding.root
    }


    private fun signUp(fName: String, email: String, Password: String) {
        userViewModel.addUser(UserEntity(0,email, fName, Password))
        (context as FragmentHolderActivity).binding.topAppBar.title = "Sign In"
        (context as FragmentHolderActivity).setFragment(SignInFragment())

    }

}