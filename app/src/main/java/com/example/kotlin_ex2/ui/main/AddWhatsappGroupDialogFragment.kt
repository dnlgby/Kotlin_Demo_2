package com.example.kotlin_ex2.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.kotlin_ex2.R
import kotlinx.android.synthetic.main.add_whatsappgroup_dialog_fragment.view.*

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        childFragmentManager.beginTransaction()
            .replace(
                R.id.main_AddWhatsappGroupDialogTagFilterFragmentPlaceHolder,
                TagFilterFragment()
            )
            .commit()
        view.main_AddWhatsappGroupDialogTagFilterFragmentPlaceHolder
        super.onViewCreated(view, savedInstanceState)
    }

}