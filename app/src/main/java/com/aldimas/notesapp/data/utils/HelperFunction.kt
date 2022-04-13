package com.aldimas.notesapp.data.utils

import android.content.Context
import android.text.TextUtils
import android.view.View
import android.widget.AdapterView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.MutableLiveData
import com.aldimas.notesapp.data.entity.Notes
import com.aldimas.notesapp.data.entity.Priority
import com.aldimas.notesapp.ui.MainActivity
import com.example.notesapp.R
import com.google.android.material.appbar.MaterialToolbar
import java.text.SimpleDateFormat
import java.util.*

//untuk mengganti warna bulet pada homeAdd di bagian priority

object HelperFunctions {
    fun spinnerListener(
        context: Context?,
        priorityIndicator: CardView
    ): AdapterView.OnItemSelectedListener = object : AdapterView.OnItemSelectedListener {
        override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
            context?.let {
                when (position) {
                    0 -> {
                        //for chosing color
                        val pink = ContextCompat.getColor(it, R.color.pink)
                        //set background color
                        priorityIndicator.setCardBackgroundColor(pink)
                    }
                    1 -> {
                        val yellow = ContextCompat.getColor(it, R.color.yellow)
                        priorityIndicator.setCardBackgroundColor(yellow)
                    }
                    2 -> {
                        val green = ContextCompat.getColor(it, R.color.green)
                        priorityIndicator.setCardBackgroundColor(green)
                    }
                }
            }
        }

        override fun onNothingSelected(parent: AdapterView<*>?) {}
    }

    fun parseToPriority(priority: String, context: Context?): Priority {
        val expectedPriority = context?.resources?.getStringArray(R.array.priorities)
        return when (priority) {
            expectedPriority?.get(0) -> Priority.HIGH
            expectedPriority?.get(1) -> Priority.MEDIUM
            expectedPriority?.get(2) -> Priority.LOW
            else -> Priority.HIGH
        }
    }

    val emptyDatabase: MutableLiveData<Boolean> = MutableLiveData(true)
    fun checkIsDataEmpty(data: List<Notes>) {
        emptyDatabase.value = data.isEmpty()
    }
}