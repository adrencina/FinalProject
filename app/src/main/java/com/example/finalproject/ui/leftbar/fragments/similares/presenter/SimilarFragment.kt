package com.example.finalproject.ui.leftbar.fragments.similares.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentSimilarBinding


class SimilarFragment : Fragment() {
    private lateinit var binding: FragmentSimilarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_similar, container, false)
    }

    private fun navigateToFragment() {
        binding.BtnBack.setOnClickListener {
            findNavController().navigate(R.id.action_similarFragment_to_imagesFragment)
        }
    }


}