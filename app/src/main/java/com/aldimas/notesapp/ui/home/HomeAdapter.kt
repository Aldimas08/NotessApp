package com.aldimas.notesapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.aldimas.notesapp.data.entity.Notes
import com.example.notesapp.databinding.RowItemNotesBinding
import java.util.ArrayList

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {

    val listNotes = ArrayList<Notes>()

    inner class MyViewHolder(val binding: RowItemNotesBinding) :
        RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder(
        RowItemNotesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
    )

    override fun onBindViewHolder(holder: HomeAdapter.MyViewHolder, position: Int) {
        val data = listNotes[position]
        holder.binding.apply {

            mNotes = data
            executePendingBindings()
        }
    }

    override fun getItemCount() = listNotes.size

    fun setData(data: List<Notes>?) {
        if (data == null) return
        val diffCalback = DiffCallback(listNotes, data)
        val diffCallbackResult = DiffUtil.calculateDiff(diffCalback)
        listNotes.clear()
        listNotes.addAll(data)
        diffCallbackResult.dispatchUpdatesTo(this)
    }
}