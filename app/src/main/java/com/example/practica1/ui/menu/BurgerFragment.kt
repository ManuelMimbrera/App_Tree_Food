package com.example.practica1.ui.menu

import android.icu.number.IntegerWidth
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.example.practica1.R
import com.google.android.material.card.MaterialCardView

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BurgerFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BurgerFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    var contar: Int = 0;
    private lateinit var sumar: Button
    private lateinit var restar: Button
    private lateinit var resultado: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_burger, container, false)


        var cardse = view.findViewById<MaterialCardView>(R.id.cardse)
        var cardhaw = view.findViewById<MaterialCardView>(R.id.cardhaw)
        var cardmons = view.findViewById<MaterialCardView>(R.id.cardmons)

        // TODO: Checkable de los productos
        cardse.setOnLongClickListener {
            cardse.isChecked = !cardse.isChecked
            true
        }
        cardhaw.setOnLongClickListener {
            cardhaw.isChecked = !cardhaw.isChecked
            true
        }
        cardmons.setOnLongClickListener {
            cardmons.isChecked = !cardmons.isChecked
            true
        }

        //Implementación del contador
        var contar = 0
        var sumar = view.findViewById<Button>(R.id.max)
        var restar = view.findViewById<Button>(R.id.min)
        restar.setEnabled(false)
        var resultado = view.findViewById<TextView>(R.id.res)
        resultado.addTextChangedListener(object : TextWatcher {

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                if(s.toString().equals("0")){
                    restar.setEnabled(false);
                }else{
                    restar.setEnabled(true);
                }
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
            override fun afterTextChanged(s: Editable?) {
                if(s.toString().equals("0")){
                    restar.setEnabled(false);
                }else{
                    restar.setEnabled(true);
                }
            }
        });
        sumar.setOnClickListener{
            contar++
            resultado.text = "$contar"
        }
        restar.setOnClickListener{
            contar--
            resultado.text = "$contar"
        }
        resultado.text = "$contar"

        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BurgerFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            BurgerFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}