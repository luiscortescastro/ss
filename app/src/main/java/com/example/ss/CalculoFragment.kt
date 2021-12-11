package com.example.ss

import android.content.Context
import android.os.Bundle
import android.text.InputType
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.core.content.ContextCompat.getSystemService
import androidx.core.content.ContextCompat.getSystemService







class CalculoFragment : Fragment() {

    private val fireViewModel : DataViewModel by activityViewModels()
    private lateinit var calcular: Button
    private lateinit var ingresos: TextView
    private lateinit var riesgo: AutoCompleteTextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_calculo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fireViewModel.refresh()
        setArrays(view)

        ingresos = view.findViewById(R.id.ingresos)
        //ingresos.inputType = InputType.TYPE_CLASS_NUMBER

        riesgo = view.findViewById(R.id.dropLevel)
        riesgo.inputType = InputType.TYPE_CLASS_NUMBER
        riesgo.showSoftInputOnFocus = false


        calcular = view.findViewById(R.id.calcularButton)
        calcular.setOnClickListener {
            if (ingresos.text.toString() != "") {
                fireViewModel.hacerCalculo(ingresos.text.toString().toInt(), riesgo.text.toString().toInt())
                view.findNavController().navigate(R.id.calToRes)
            } else {
                Toast.makeText(requireContext(), "Ingresa un valor", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun setArrays(myView: View) {

        val drops = fireViewModel.getDrops()

        val epsArray = ArrayAdapter(requireContext(), R.layout.dropdown_item, drops[0])

        val epsArrayAdapt = myView.findViewById<AutoCompleteTextView>(R.id.dropEPS)
        epsArrayAdapt.setAdapter(epsArray)

        val arlArray = ArrayAdapter(requireContext(), R.layout.dropdown_item, drops[1])
        val arlArrayAdapt = myView.findViewById<AutoCompleteTextView>(R.id.dropARL)
        arlArrayAdapt.setAdapter(arlArray)

        val afpArray = ArrayAdapter(requireContext(), R.layout.dropdown_item, drops[2])
        val afpArrayAdapt = myView.findViewById<AutoCompleteTextView>(R.id.dropAFP)
        afpArrayAdapt.setAdapter(afpArray)


        val risks = resources.getStringArray(R.array.risk_array)
        val riskArray = ArrayAdapter(requireContext(), R.layout.dropdown_item, risks)
        val riskArrayAdapt = myView.findViewById<AutoCompleteTextView>(R.id.dropLevel)
        riskArrayAdapt.setAdapter(riskArray)
    }
}