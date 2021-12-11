package com.ss.apportar.nw

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

const val MESSAGES_COLLECTION_NAME = "mensajes"

class ColeccionesFirestore {
    val firebaseFirestore = FirebaseFirestore.getInstance()
    var list: MutableList<Mensaje> = mutableListOf()

    fun getMessages(callback: Callback<List<Mensaje>>) {
        firebaseFirestore.collection(MESSAGES_COLLECTION_NAME).get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    list = result.toObjects(Mensaje::class.java)
                    callback.onSuccess(list)
                    break
                }
            }
    }

    fun sendMessage(message: String, email: String, contexto: Context) {

        val actMessage = Mensaje()
        actMessage.email = email.substringBefore("@")
        actMessage.message = message

        val listaMensajes = mutableListOf<HashMap<String,String>>()
        list.forEach {
            listaMensajes.add(hashMapOf(it.email to email, it.message to message))
        }
        if(actMessage.message != "") {
            if (!listaMensajes.contains(hashMapOf(actMessage.email to email, actMessage.message to message))) {
                firebaseFirestore.collection(MESSAGES_COLLECTION_NAME)
                    .add(actMessage)
                    .addOnSuccessListener { documentReference ->
                        Toast.makeText(contexto, "Mensaje publicado", Toast.LENGTH_SHORT).show()
                        Log.d(
                            TAG,
                            "Añadido: ${documentReference.id}"
                        )
                    }
                    .addOnFailureListener { e ->
                        Log.w(TAG, "Error Cargando: " + { e })
                    }
            } else {
                Toast.makeText(contexto, "No puedes publicar el mismo mensaje", Toast.LENGTH_SHORT)
                    .show()
            }
        }else{
            Toast.makeText(contexto, "No puedes publicar un mensaje vacío", Toast.LENGTH_SHORT)
                .show()
        }
    }

    fun getEAA(): List<MutableList<String>> {
        val epss: MutableList<String> = arrayListOf()
        val arls: MutableList<String> = arrayListOf()
        val afps: MutableList<String> = arrayListOf()

        firebaseFirestore.collection("epss")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data["Nombre"] != null) {
                        epss.add(document.data["Nombre"] as String)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

        firebaseFirestore.collection("arlss")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data["Nombre"] != null) {
                        arls.add(document.data["Nombre"] as String)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }

        firebaseFirestore.collection("afpss")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    if (document.data["Nombre"] != null) {
                        afps.add(document.data["Nombre"] as String)
                    }
                }
            }
            .addOnFailureListener { exception ->
                Log.w(ContentValues.TAG, "Error getting documents.", exception)
            }
        return listOf(epss,arls,afps)
    }
}