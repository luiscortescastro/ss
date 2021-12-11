package com.example.ss

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.AppBarConfiguration
import com.example.ss.databinding.ActivityAuthBinding
import com.google.firebase.auth.FirebaseAuth

class AuthActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAuthBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAuthBinding.inflate(layoutInflater)
        setContentView(binding.root)

        authSetup()
    }

    private fun authSetup() {
        title = "Autenticación"
        val emailText = binding.email.text
        val passText = binding.password.text

        binding.signUpButton.setOnClickListener {
            if (emailText.isNotEmpty() && passText.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .createUserWithEmailAndPassword(emailText.toString(), passText.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            autenticated(it.result?.user?.email ?: "", ProviderType.BAISC)
                        } else {
                            authError()
                        }
                    }
            }
        }
        binding.signInButton.setOnClickListener {
            if (emailText.isNotEmpty() && passText.isNotEmpty()) {
                FirebaseAuth.getInstance()
                    .signInWithEmailAndPassword(emailText.toString(), passText.toString())
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            autenticated(it.result?.user?.email ?: "", ProviderType.BAISC)
                        } else {
                            authError()
                        }
                    }
            }
        }
    }

    private fun authError() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Error")
        builder.setMessage("Ha habido un error en la autenticación")
        builder.setPositiveButton("Aceptar", null)
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }

    private fun autenticated(email: String, provider: ProviderType) {
        val readyIntent = Intent(this, MainActivity::class.java).apply {
            putExtra("email", email)
            putExtra("provider", provider.name)
        }
        startActivity(readyIntent)
    }
}