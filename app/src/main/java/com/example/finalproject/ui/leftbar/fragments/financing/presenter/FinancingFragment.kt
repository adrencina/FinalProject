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
import com.example.finalproject.data.repository.PaymentRepository
import com.example.finalproject.databinding.FragmentFinancingBinding
import com.example.finalproject.ui.leftbar.fragments.financing.adacter.FinancingAdapter
import com.example.finalproject.ui.leftbar.fragments.financing.viewModel.FinancingViewModel
import com.example.finalproject.ui.leftbar.fragments.financing.viewModel.FinancingViewModelFactory

class FinancingFragment : Fragment() {
    private lateinit var binding: FragmentFinancingBinding
    private val financingAdapter = FinancingAdapter(emptyList())
    private lateinit var financingViewModel: FinancingViewModel

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
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        val repository = PaymentRepository(this.requireContext())
        financingViewModel =
            ViewModelProvider(this, FinancingViewModelFactory(repository))[FinancingViewModel::class.java]


        setupRecyclerViews()
        observeViewModel()

        financingViewModel.fetchPaymentMethods()



        binding.tvDescriptionFragment.setOnClickListener {
            findNavController().navigate(R.id.action_financingFragment_to_descriptionFragment)
        }
        binding.tvImagesFragment.setOnClickListener {
            findNavController().navigate(R.id.action_financingFragment_to_imagesFragment)
        }
        binding.tvCommentsFragment.setOnClickListener {
            findNavController().navigate(R.id.action_financingFragment_to_commentsFragment)
        }
    }

    // Config RV
    private fun setupRecyclerViews() {

        binding.rvFinancingItems.apply {
            layoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, false)
            adapter = financingAdapter
        }

    }

    // Observamos el VM
    private fun observeViewModel() {
        financingViewModel.paymentMethods.observe(viewLifecycleOwner,Observer { paymentMetho ->
            if (paymentMetho.isNotEmpty()) {
                financingAdapter.updateData(paymentMetho)
                Log.d("FragmentFinancing", "Productos mostrados")
            } else {
                Log.d("FragmentFinancing", "No se encontraron productos")
            }
        })

    }

}