package com.gyanhub.jobportal.activity


import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Html
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.gyanhub.jobportal.R
import com.gyanhub.jobportal.ViewModel.JobFactory
import com.gyanhub.jobportal.ViewModel.JobViewModel
import com.gyanhub.jobportal.activity.comp.CustomSpinner
import com.gyanhub.jobportal.adapter.RcMainAdapter
import com.gyanhub.jobportal.databinding.ActivityMainBinding
import com.gyanhub.jobportal.interfaces.RcItemsClick
import com.gyanhub.jobportal.repository.JobRepository
import com.gyanhub.jobportal.room.DataBase
import com.gyanhub.jobportal.room.JobTableEntity


class MainActivity : AppCompatActivity(), RcItemsClick {
    lateinit var binding: ActivityMainBinding
    lateinit var jobViewModel: JobViewModel
    private lateinit var searchView: SearchView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.topAppBar)


        binding.rcMain.layoutManager = LinearLayoutManager(this)
        jobViewModel =
            ViewModelProvider(
                this,
                JobFactory(
                    JobRepository(
                        DataBase.getDatabase(applicationContext).Dao()
                    )
                )
            )[JobViewModel::class.java]

        jobViewModel.byJob.observe(this){
            dropdown(  binding.btnFilter1,it)
            dropdown(  binding.btnFilter2,it)
            dropdown(  binding.btnFilter3,it)


        }


        jobViewModel.getJob().observe(this) {
            var adapter = RcMainAdapter(this, it, this)
            binding.rcMain.adapter = adapter
        }
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
        val searchItem = menu!!.findItem(R.id.action_search)
        searchView = searchItem.actionView as SearchView
        searchView.setBackgroundColor(resources.getColor(R.color.SearchBG))
        searchView.queryHint = resources.getString(R.string.hintSearchMess)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                Toast.makeText(this@MainActivity, "enter: $query", Toast.LENGTH_SHORT).show()
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(this@MainActivity, "enter: $newText", Toast.LENGTH_SHORT).show()
                return false
            }

        } )




        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.btnFilter -> {
                Toast.makeText(this, "click on filter", Toast.LENGTH_SHORT).show()
                if (binding.filterLayout.visibility == View.VISIBLE) binding.filterLayout.visibility =
                    View.GONE
                else binding.filterLayout.visibility = View.VISIBLE
            }
            R.id.btnProfile -> {
                Toast.makeText(this, "click on profile", Toast.LENGTH_SHORT).show()

            }

        }
        return super.onOptionsItemSelected(item)
    }
    private fun dropdown(view: Spinner, list: List<String>) {
        val adapter = CustomSpinner(this, list)
        view.adapter = adapter
        view.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                Toast.makeText(this@MainActivity, list[position], Toast.LENGTH_SHORT).show()
                jobViewModel.byJobs = list[position]
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // do nothing
            }
        }
    }
}


