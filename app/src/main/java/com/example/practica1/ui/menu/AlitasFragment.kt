package com.example.practica1.ui.menu

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
 * Use the [AlitasFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AlitasFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

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
        var view = inflater.inflate(R.layout.fragment_alitas, container, false)

        val card1 = view.findViewById<MaterialCardView>(R.id.card1)
        val card2 = view.findViewById<MaterialCardView>(R.id.card2)
        val card3 = view.findViewById<MaterialCardView>(R.id.card3)


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

        // TODO: Checkable de los productos
        card1.setOnLongClickListener {
            card1.isChecked = !card1.isChecked
            true
        }
        card2.setOnLongClickListener {
            card2.isChecked = !card2.isChecked
            true
        }
        card3.setOnLongClickListener {
            card3.isChecked = !card3.isChecked
            true
        }

        return view;
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment AlitasFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AlitasFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}