package com.gyanhub.jobportal.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.gyanhub.jobportal.R
import com.gyanhub.jobportal.ViewModel.JobFactory
import com.gyanhub.jobportal.ViewModel.JobViewModel
import com.gyanhub.jobportal.activity.FragmentHolderActivity
import com.gyanhub.jobportal.activity.MainActivity
import com.gyanhub.jobportal.activity.loginActivity
import com.gyanhub.jobportal.databinding.FragmentSignInBinding
import com.gyanhub.jobportal.repository.JobRepository
import com.gyanhub.jobportal.room.DataBase


class SignInFragment : Fragment() {
    lateinit var binding: FragmentSignInBinding
    lateinit var userViewModel: JobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        userViewModel =
            ViewModelProvider(
                this,
                JobFactory(
                    JobRepository(
                        DataBase.getDatabase(context as Activity).Dao()
                    )
                )
            ).get(JobViewModel::class.java)

        binding.btnLogin.setOnClickListener {
            if (binding.eTxtEmail.text.toString().isEmpty()) {
                binding.eTxtEmail.error = "Enter Email"
                return@setOnClickListener
            } else if (binding.eTxtPass.text.toString().isEmpty()) {
                binding.eTxtPass.error = "Enter Password"
                return@setOnClickListener
            }
            userViewModel.getUser(
                binding.eTxtEmail.text.toString(),
                binding.eTxtPass.text.toString()
            ).observe(viewLifecycleOwner) {
                if (it.isNotEmpty()) {
                    (context as FragmentHolderActivity).finshActivity()
                } else {
                    Toast.makeText(context, "Account No Match", Toast.LENGTH_SHORT).show()
                }
            }

        }

        return binding.root
    }
}