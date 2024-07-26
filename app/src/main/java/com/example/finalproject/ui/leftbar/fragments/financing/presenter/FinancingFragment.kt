package com.example.finalproject.ui.leftbar.fragments.financing.presenter

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.data.repository.PaymentRepository
import com.example.finalproject.data.service.dto.Utils.ID_PRODUCT
import com.example.finalproject.databinding.FragmentFinancingBinding
import com.example.finalproject.ui.leftbar.fragments.financing.adacter.FinancingAdapter
import com.example.finalproject.ui.leftbar.fragments.financing.viewModel.FinancingViewModel
import com.example.finalproject.ui.leftbar.fragments.financing.viewModel.FinancingViewModelFactory
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState
import com.example.finalproject.ui.leftbar.fragments.description.viewModel.DescriptionViewModel
import com.example.finalproject.ui.leftbar.fragments.description.viewModel.DescriptionViewModelFactory

class FinancingFragment : Fragment() {
    private lateinit var binding: FragmentFinancingBinding
    private val financingAdapter = FinancingAdapter(emptyList())
    private lateinit var financingViewModel: FinancingViewModel
    private lateinit var descriptionViewModel: DescriptionViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFinancingBinding.inflate(inflater, container, false)

        val repositoryF = PaymentRepository(this.requireContext())
        financingViewModel =
            ViewModelProvider(this, FinancingViewModelFactory(repositoryF))[FinancingViewModel::class.java]



        val repositoryP = LeftbarRepository(requireContext())
                descriptionViewModel =
                    ViewModelProvider(
                        this,
                        DescriptionViewModelFactory(repositoryP)
                    )[DescriptionViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToFragment()
        setupRecyclerViews()
        observeViewModelFinancing()
        financingViewModel.fetchPaymentMethods()

        navigateToFragment()

        observeViewModelProducto()
        descriptionViewModel.fetchProductById(ID_PRODUCT ?: 0)



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
        financingViewModel.paymentMethods.observe(viewLifecycleOwner,Observer { paymentMetho ->
            if (paymentMetho.isNotEmpty()) {
                financingAdapter.updateData(paymentMetho)
                Log.d("FragmentFinancing", "Productos mostrados"+paymentMetho.toString())
            } else {
                Log.d("FragmentFinancing", "No se encontraron productos")
            }
        })

    }

    private fun observeViewModelProducto() {
        descriptionViewModel.descriptionState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DescriptionState.Success -> {
                    showDescription(product = state.data)
                }

                is DescriptionState.Error -> {
                    // Manejar el estado de error aquí
                }

                is DescriptionState.Loading -> {
                    // Manejar el estado de carga aquí
                }
            }
        }
    }

    private fun showDescription(product: Product) {
        binding.tvPriceProduct.text = product.price.toString()

    }

}