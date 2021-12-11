package com.example.ss

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import com.google.firebase.auth.FirebaseAuth


class SignOutFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("Entrar","Entra")
        val builder = AlertDialog.Builder(this.requireContext())
        builder.setMessage("Quieres cerrar sesión?")
            .setCancelable(false)
            .setPositiveButton("Si") { dialog, id ->
                val signAgainIntent = Intent(this.requireContext(), AuthActivity::class.java)
                startActivity(signAgainIntent)
                FirebaseAuth.getInstance().signOut()
            }
            .setNegativeButton("No") { dialog, id ->
                val backAgain = Intent(this.requireContext(), MainActivity::class.java)
                startActivity(backAgain)
                dialog.dismiss()
            }
        val alert = builder.create()
        alert.show()
        return inflater.inflate(R.layout.fragment_sign_out, container, false)
    }

}