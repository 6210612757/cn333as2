package com.wanchana.deenotes.ui.detail

import android.app.Activity
import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.content.SharedPreferences
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.wanchana.deenotes.MainActivity
import com.wanchana.deenotes.R
import com.wanchana.deenotes.databinding.MainFragmentBinding
import com.wanchana.deenotes.databinding.NoteContentActivityBinding
import com.wanchana.deenotes.models.Noted
import com.wanchana.deenotes.ui.main.MainViewModel
import com.wanchana.deenotes.ui.main.MainViewModelFactory

class NoteContentFragment : Fragment() {

    companion object {
        fun newInstance() = NoteContentFragment()
    }

    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)
        val note: Noted? = arguments?.getParcelable(MainActivity.INTENT_LIST_KEY)

        note?.let{
            viewModel.list = note
            requireActivity().title = note.name
            val text:EditText = requireActivity().findViewById(R.id.editTextTextMultiLine)
            val sharedPreferences = viewModel.sharedPreferences
            val contented = sharedPreferences.getString(viewModel.list.name,"Not found")
            Log.d(TAG, note.content)
            text.setText(contented)
        }
    }

}