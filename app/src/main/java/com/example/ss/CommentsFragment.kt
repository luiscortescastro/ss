package com.example.ss

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.os.Build

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AutoCompleteTextView
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat.getSystemService
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ss.apportar.nw.CommAdapter
import com.ss.apportar.nw.Mensaje
import com.ss.apportar.nw.MessagesListener

class CommentsFragment : Fragment(), MessagesListener {

    private lateinit var messagesAdapter: CommAdapter
    private lateinit var commentsViewModel: DataViewModel

    private lateinit var inComment: AutoCompleteTextView
    private lateinit var sendButton: ImageView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view: View = inflater.inflate(R.layout.fragment_comments, container, false)

        return view
    }


    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        inComment = view.findViewById(R.id.actual_user_comment)
        sendButton = view.findViewById(R.id.sendMessageButton)

        commentsViewModel = ViewModelProvider(this)[DataViewModel::class.java]
        commentsViewModel.refresh()

        sendButton.setOnClickListener {
            commentsViewModel.uploadMessage(inComment.text.toString(), requireContext())
            commentsViewModel.refresh()
            closeKeyboard()
        }



        messagesAdapter = CommAdapter(this)
        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = messagesAdapter
        observeViewModel()

    }

    @RequiresApi(Build.VERSION_CODES.M)
    private fun closeKeyboard() {
        val inputManager =
            requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(
            requireActivity().currentFocus!!.windowToken,
            InputMethodManager.HIDE_NOT_ALWAYS
        )
    }

    private fun observeViewModel() {
        commentsViewModel.listMessages.observe(viewLifecycleOwner, { messages ->
            messagesAdapter.updateData(messages)
        })
    }

    override fun OnMessagesClick(message: Mensaje, position: Int) {
        TODO("Not Necessary")
    }
}