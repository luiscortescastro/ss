package com.example.ss

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider

class ProfileFragment : Fragment() {

    private lateinit var emailText : TextView
    private lateinit var commentsViewModel: DataViewModel

    @SuppressLint("SetTextI18n")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view =inflater.inflate(R.layout.fragment_profile, container, false)

        commentsViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        commentsViewModel.refresh()
        emailText = view.findViewById(R.id.emailNot)
        emailText.text = "Correo: "+commentsViewModel.getEmail()

        return view
    }

}