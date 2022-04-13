package com.aldimas.notesapp.ui.detail

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.aldimas.notesapp.data.entity.Notes
import com.aldimas.notesapp.data.utils.HelperFunctions
import com.aldimas.notesapp.data.utils.HelperFunctions.parseToPriority
import com.aldimas.notesapp.data.utils.setActionBar
import com.aldimas.notesapp.ui.NotesViewModel
import com.aldimas.notesapp.ui.update.UpdateFragmentArgs
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentAddBinding
import java.text.SimpleDateFormat
import java.util.*

class DetailFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding as FragmentAddBinding
    private val addViewModels by viewModels<NotesViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setHasOptionsMenu(true)
        binding.toolbarAdd.setActionBar(requireActivity())
        binding.spinnerPriorities.onItemSelectedListener =
            HelperFunctions.spinnerListener(context, binding.priorityIndicator)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
        val action = menu.findItem(R.id.menu_save)
        action.actionView.findViewById<AppCompatImageButton>(R.id.btn_save).setOnClickListener {
            insertNotes()
        }
    }

    private fun insertNotes() {
        binding.apply {
            val title = edtTitle.text.toString()
            val priority = spinnerPriorities.selectedItem.toString()
            val description = edtDescriptionUpdate.text.toString()

            val calender = Calendar.getInstance().time
            val date = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault()).format(calender)

            val notes = Notes(0, title, parseToPriority(priority, context), description, date)

            //Make Some Decicion if title empty show error massage
            //and if the description is empty show alert
            if (edtTitle.text.isEmpty()) {
                edtTitle.error = "Please Fill Field"
            } else if (edtDescriptionUpdate.text.isEmpty()) {
                Toast.makeText(context, "Your Notes Is still empty", Toast.LENGTH_LONG).show()
            } else {
                addViewModels.insertData(notes)
                Toast.makeText(context, "successful add note.", Toast.LENGTH_SHORT).show()
                findNavController().navigate(R.id.action_addFragment_to_homeFragment)
            }
        }

    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}