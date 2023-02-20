package com.gyanhub.jobportal.activity


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyanhub.jobportal.R
import com.gyanhub.jobportal.ViewModel.JobFactory
import com.gyanhub.jobportal.ViewModel.JobViewModel
import com.gyanhub.jobportal.adapter.RcMainAdapter
import com.gyanhub.jobportal.databinding.ActivityMainBinding
import com.gyanhub.jobportal.interfaces.RcItemsClick
import com.gyanhub.jobportal.repository.JobRepository
import com.gyanhub.jobportal.room.DataBase
import com.gyanhub.jobportal.room.JobTableEntity


class MainActivity : AppCompatActivity(), RcItemsClick {
    lateinit var binding: ActivityMainBinding
    lateinit var jobViewModel: JobViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolBar()

        binding.rcMain.layoutManager = LinearLayoutManager(this)
        jobViewModel =
            ViewModelProvider(
                this,
                JobFactory(
                    JobRepository(
                        DataBase.getDatabase(applicationContext).Dao()
                    )
                )
            ).get(JobViewModel::class.java)




        jobViewModel.getJob().observe(this) {
            var adapter = RcMainAdapter(this, it, this)
            binding.rcMain.adapter = adapter
        }
    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.topAppBar)
    }

    override fun itemsClick(position: Int, data: List<JobTableEntity>) {
        val intent = Intent(this, JobDetailsActivity::class.java)
        intent.putExtra("jobType", data[position].jobType)
        intent.putExtra("jobCyName", data[position].jobCyName)
        intent.putExtra("jobPay", data[position].jobPay)
        intent.putExtra("jobDis", data[position].jobDiscription)
        intent.putExtra("jobOpp", data[position].jobPostOpportunitity)
        intent.putExtra("jobSkill", data[position].jobSkillRequrired)
        intent.putExtra("jobWho", data[position].whoCanApply)
        intent.putExtra("WhNo", data[position].whNo)
        startActivity(intent)
    }

    fun addNewJob(view: View) {
        val i = Intent(this, fragHolde01Activity::class.java)
        i.putExtra("data", "Job Post")
        startActivity(i)
    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.logout, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnLogOut-> {
                startActivity(Intent(this,loginActivity::class.java))
                finish()
            }

        }
        return super.onOptionsItemSelected(item)
    }

}