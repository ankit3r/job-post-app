package com.gyanhub.jobportal.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gyanhub.jobportal.R
import com.gyanhub.jobportal.interfaces.RcItemsClick
import com.gyanhub.jobportal.room.JobTableEntity

class RcMainAdapter(
    val context: Context,
    val jobData: List<JobTableEntity>,
    val click: RcItemsClick
) : RecyclerView.Adapter<RcMainAdapter.RcViewHolder>() {
    class RcViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val jobType: TextView = itemView.findViewById(R.id.txtJobType)
        val jobCyName: TextView = itemView.findViewById(R.id.txtCyName)
        val jobPay: TextView = itemView.findViewById(R.id.txtPay)
        val item : RelativeLayout = itemView.findViewById(R.id.item)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RcViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rc_main_items, parent, false)
        return RcViewHolder(view)
    }

    override fun onBindViewHolder(holder: RcViewHolder, position: Int) {
        val item = jobData[position]
        holder.jobType.text = item.jobType
        holder.jobCyName.text = item.jobCyName
        holder.jobPay.text = item.jobPay
        holder.item.setOnClickListener{
            click.itemsClick(position,jobData)
        }
    }

    override fun getItemCount(): Int {
        return jobData.size
    }
}