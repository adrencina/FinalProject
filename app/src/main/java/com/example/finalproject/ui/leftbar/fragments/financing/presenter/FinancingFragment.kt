package com.example.finalproject.ui.leftbar.fragments.financing.presenter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.repository.PaymentRepository
import com.example.finalproject.databinding.FragmentFinancingBinding
import com.example.finalproject.ui.leftbar.fragments.financing.adacter.FinancingAdapter
import com.example.finalproject.ui.leftbar.fragments.financing.viewModel.FinancingViewModel
import com.example.finalproject.ui.leftbar.fragments.financing.viewModel.FinancingViewModelFactory

class FinancingFragment : Fragment() {
    private lateinit var binding: FragmentFinancingBinding
    private val financingAdapter = FinancingAdapter(emptyList())
    private val viewModel : FinancingViewModel by viewModels{
        FinancingViewModelFactory(PaymentRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinancingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToFragment()
        setupRecyclerViews()
        observeViewModelFinancing()
        viewModel.fetchPaymentMethods()

        navigateToFragment()

//        observeViewModelProducto()





    }

    private fun navigateToFragment() {
        binding.tvImagesFragment.setOnClickListener {
            findNavController().navigate(R.id.action_financingFragment_to_imagesFragment)
        }
        binding.tvCommentsFragment.setOnClickListener {
            findNavController().navigate(R.id.action_financingFragment_to_commentsFragment)
        }
        binding.tvDescriptionFragment.setOnClickListener {
            findNavController().navigate(R.id.action_financingFragment_to_descriptionFragment)
        }
    }

    // Config RV
    private fun setupRecyclerViews() {
        binding.rvFinancingItems.apply {
            layoutManager =
                LinearLayoutManager(this@FinancingFragment.context, LinearLayoutManager.VERTICAL, false)
            adapter = financingAdapter
        }
    }

    // Observamos el VM
    private fun observeViewModelFinancing() {
        viewModel.paymentMethods.observe(viewLifecycleOwner) { paymentMetho ->
            if (paymentMetho.isNotEmpty()) {
                financingAdapter.updateData(paymentMetho)
                Log.d("FragmentFinancing", "Productos mostrados" + paymentMetho.toString())
            } else {
                Log.d("FragmentFinancing", "No se encontraron productos")
            }
        }

    }

//    private fun observeViewModelProducto() {
//        viewModel.financingState.observe(viewLifecycleOwner) { state ->
//            when (state) {
//                is FinancingState.Success -> {
//                    showDescription(state.data)
//                }
//
//                is FinancingState.Error -> {
//                }
//
//                is FinancingState.Loading -> {
//                }
//            }
//        }
//    }



}