package com.example.kotlin_ex2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kotlin_ex2.R

class AddWhatsappGroupDialogFragment : DialogFragment() {


    companion object {
        fun getInstance(): AddWhatsappGroupDialogFragment {
            return AddWhatsappGroupDialogFragment()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_whatsappgroup_dialog_fragment, container, false)
    }

    override fun onResume() {
        // Sets the height and the width of the DialogFragment
        val width = 1000
        val height = 700
        dialog!!.window!!.setLayout(width, height)
        super.onResume()
    }
}