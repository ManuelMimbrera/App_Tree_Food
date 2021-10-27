package com.example.practica1.ui.menu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.navigation.findNavController
import com.example.practica1.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [MenuFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MenuFragment : Fragment() {
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
        var view = inflater.inflate(R.layout.fragment_menu, container, false)


        var card = view.findViewById<CardView>(R.id.card)
        var card2 = view.findViewById<CardView>(R.id.card2)
        var card3 = view.findViewById<CardView>(R.id.card3)
        var card4 = view.findViewById<CardView>(R.id.card4)
        var card5 = view.findViewById<CardView>(R.id.card5)
        var card6 = view.findViewById<CardView>(R.id.card6)

        card.setOnClickListener {
            view.findNavController().navigate(R.id.nav_burger)
        }
        card2.setOnClickListener {
            view.findNavController().navigate(R.id.nav_hotdogs)
        }
        card3.setOnClickListener {
            view.findNavController().navigate(R.id.nav_papas)
        }
        card4.setOnClickListener {
            view.findNavController().navigate(R.id.nav_alitas)
        }
        card5.setOnClickListener {
            view.findNavController().navigate(R.id.nav_burritos)
        }
        card6.setOnClickListener {
            view.findNavController().navigate(R.id.nav_bebidas)
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
         * @return A new instance of fragment MenuFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            MenuFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}