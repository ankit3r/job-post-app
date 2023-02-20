package com.gyanhub.jobportal.activity

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.gyanhub.jobportal.R
import com.gyanhub.jobportal.databinding.ActivityJobDetailsBinding
import com.gyanhub.jobportal.databinding.ActivityMainBinding

class JobDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityJobDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpToolBar()

        binding.txtJobType.text = intent.getStringExtra("jobType").toString()
        binding.txtCyName.text = intent.getStringExtra("jobCyName").toString()
        binding.txtPay.text = intent.getStringExtra("jobPay").toString()
        binding.txtAbout.text = intent.getStringExtra("jobDis").toString()
        binding.txtOpportunities.text = "Total Post: ${intent.getStringExtra("jobOpp").toString()}"
        binding.txtSkill.text = intent.getStringExtra("jobSkill").toString()
        binding.txtWhoCanApply.text = intent.getStringExtra("jobWho").toString()
        binding.button.setOnClickListener {
            bottomSheet()
        }


    }

    private fun bottomSheet() {
        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(R.layout.apply_bottom_sheet)
        val btn = bottomSheetDialog.findViewById<Button>(R.id.button2)
        btn?.setOnClickListener {

            val massages =
                "Name: ${bottomSheetDialog.findViewById<TextView>(R.id.fullname)?.text.toString()} \n" +
                        "Email id: ${bottomSheetDialog.findViewById<TextView>(R.id.email)?.text.toString()} \n" +
                        "Whatsapp no: ${bottomSheetDialog.findViewById<TextView>(R.id.whNo)?.text.toString()} \n" +
                        "Skills: ${bottomSheetDialog.findViewById<TextView>(R.id.skills)?.text.toString()}\n" +
                        "Higher Education : ${bottomSheetDialog.findViewById<TextView>(R.id.Hed)?.text.toString()} \n" +
                        "Address : ${bottomSheetDialog.findViewById<TextView>(R.id.address)?.text.toString()}\n" +
                        "Pin Code : ${bottomSheetDialog.findViewById<TextView>(R.id.pinCode)?.text.toString()}\n" +
                        "Country : ${bottomSheetDialog.findViewById<TextView>(R.id.Cyname)?.text.toString()}\n"


            val Whno = intent.getStringExtra("WhNo").toString()
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data =
                Uri.parse("http://api.whatsapp.com/send?phone=+91$Whno + &text= $massages")
            startActivity(intent)
            bottomSheetDialog.dismiss()


        }
        bottomSheetDialog.show()
    }

    private fun setUpToolBar() {
        setSupportActionBar(binding.topAppBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}