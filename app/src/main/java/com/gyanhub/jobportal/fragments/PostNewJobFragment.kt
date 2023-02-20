package com.gyanhub.jobportal.fragments

import android.app.Activity
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
import com.gyanhub.jobportal.activity.fragHolde01Activity
import com.gyanhub.jobportal.databinding.FragmentPostNewJobBinding
import com.gyanhub.jobportal.repository.JobRepository
import com.gyanhub.jobportal.room.DataBase
import com.gyanhub.jobportal.room.JobTableEntity

class PostNewJobFragment : Fragment() {
    private lateinit var binding: FragmentPostNewJobBinding
    lateinit var jobViewModel: JobViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPostNewJobBinding.inflate(layoutInflater, container, false)

        jobViewModel =
            ViewModelProvider(
                this,
                JobFactory(
                    JobRepository(
                        DataBase.getDatabase(context as Activity).Dao()
                    )
                )
            ).get(JobViewModel::class.java)

        binding.btnPostJob.setOnClickListener {
            if (binding.eTxtCyName.text.isEmpty()) {
                binding.eTxtCyName.error = "Enter Required"
                return@setOnClickListener
            } else if (binding.eTxtType.text.isEmpty()) {
                binding.eTxtType.error = "Enter Required"
                return@setOnClickListener
            } else if (binding.eTxtPay.text.isEmpty()) {
                binding.eTxtPay.error = "Enter Required"
                return@setOnClickListener
            } else if (binding.eTxtTotalPost.text.isEmpty()) {
                binding.eTxtPay.error = "Enter Required"
                return@setOnClickListener
            } else if (binding.eTxtDisc.text.isEmpty()) {
                binding.eTxtDisc.error = "Enter Required"
                return@setOnClickListener
            } else if (binding.eTxtSkill.text.isEmpty()) {
                binding.eTxtSkill.error = "Enter Required"
                return@setOnClickListener
            } else if (binding.eTxtWCA.text.isEmpty()) {
                binding.eTxtWCA.error = "Enter Required"
                return@setOnClickListener
            } else if(binding.eTxtWhNo.text.isEmpty()){
                binding.eTxtWhNo.error = "Enter Required"
                return@setOnClickListener
            }
            jobViewModel.addJobs(
                JobTableEntity(
                    0,
                    binding.eTxtType.text.toString(),
                    binding.eTxtCyName.text.toString(),
                    binding.eTxtDisc.text.toString(),
                    binding.eTxtPay.text.toString(),
                    binding.eTxtSkill.text.toString(),
                    binding.eTxtTotalPost.text.toString(),
                    binding.eTxtWCA.text.toString(),
                    binding.eTxtWhNo.text.toString()
                )
            )
            Toast.makeText(context, "Job Posted", Toast.LENGTH_SHORT).show()
            binding.eTxtType.setText("")
            binding.eTxtCyName.setText("")
            binding.eTxtDisc.setText("")
            binding.eTxtPay.setText("")
            binding.eTxtSkill.setText("")
            binding.eTxtTotalPost.setText("")
            binding.eTxtWCA.setText("")
            binding.eTxtWhNo.setText("")

            (context as fragHolde01Activity).onBackPressed()

        }




        return binding.root
    }


}