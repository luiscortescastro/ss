package com.example.ss

import android.content.Context
import android.util.Log
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.auth.FirebaseAuth
import com.ss.apportar.nw.Callback
import com.ss.apportar.nw.ColeccionesFirestore
import com.ss.apportar.nw.Mensaje
import java.lang.Exception

const val SALARIO_MINIMO = 908526

class DataViewModel : ViewModel() {
    val firestoreService = ColeccionesFirestore()
    var listMessages: MutableLiveData<List<Mensaje>> = MutableLiveData()
    var isLoading = MutableLiveData<Boolean>()
    var actualUserEmail: String = ""
    private val salud : MutableLiveData<Int> = MutableLiveData()
    var pension : MutableLiveData<Int> = MutableLiveData()
    var riesgos : MutableLiveData<Int> = MutableLiveData()
    var total : MutableLiveData<Int> = MutableLiveData()
    var resultList: MutableLiveData<List<Int>> = MutableLiveData()


    fun refresh() {
        getData()
    }

    fun uploadMessage(message: String, contexto: Context) {
        firestoreService.sendMessage(message, getEmail(), contexto)
    }

    fun getEmail(): String {
        actualUserEmail = (FirebaseAuth.getInstance().currentUser?.email ?: "Anonimo")
        return actualUserEmail
    }

    private fun getData() {
        firestoreService.getMessages(object : Callback<List<Mensaje>> {
            override fun onSuccess(response: List<Mensaje>?) {
                listMessages.postValue(response!!)
                processFinished()
            }

            override fun onFailure(exception: Exception) {
                processFinished()
            }
        })
    }

    private fun processFinished() {
        isLoading.value = true
    }

    fun getDrops(): List<MutableList<String>> {
        return firestoreService.getEAA()
    }

    fun getSalud(): LiveData<Int> {
        return salud
    }
    fun getPension(): LiveData<Int> {
        return pension
    }
    fun getRisk(): LiveData<Int> {
        return riesgos
    }

    fun hacerCalculo(ingresos: Int, riesgo: Int) {
        var base = ingresos
        val risk =
            when (riesgo) {
                1 -> {
                    0.522
                }
                2 -> {
                    1.044
                }
                3 -> {
                    2.436
                }
                4 -> {
                    4.35
                }
                5 -> {
                    6.96
                }
                else -> {
                    0.0
                }
            }

        base = if (base * 0.4 <= SALARIO_MINIMO) {
            SALARIO_MINIMO
        } else {
            (base * 0.4).toInt()
        }
        salud.postValue((base * 0.12).toInt())
        pension.postValue((base * 0.16).toInt())
        riesgos.postValue((base * risk).toInt())
    }
}