package com.example.ss

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//private val total: Int, private val salud: Int, private val riesgos: Int, private val pensiones: Int
class ResultadoFragment() : Fragment() {

    private val fireViewModel: DataViewModel by activityViewModels()
    private lateinit var totalT: TextView
    private lateinit var saludT: TextView
    private lateinit var pensionT: TextView
    private lateinit var riesgosT: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_resultado, container, false)
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        totalT = view.findViewById(R.id.totalValue)
        pensionT = view.findViewById(R.id.pensionValue)
        riesgosT = view.findViewById(R.id.riesgosValue)
        saludT = view.findViewById(R.id.saludValue)

        var total = 0

        fireViewModel.getSalud().observe(viewLifecycleOwner, { s ->
            saludT.text = "$$s"
            total += s
        })
        fireViewModel.getRisk().observe(viewLifecycleOwner, { r ->
            riesgosT.text = "$$r"
            total += r
        })
        fireViewModel.getPension().observe(viewLifecycleOwner, { p ->
            pensionT.text = "$$p"
            total += p
            totalT.text = "$$total"
        })
    }

}