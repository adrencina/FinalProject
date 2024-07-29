package com.example.finalproject.ui.leftbar.fragments.financing.presenter

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.repository.PaymentRepository
import com.example.finalproject.databinding.FragmentFinancingBinding
import com.example.finalproject.ui.home.presenter.HomeActivity
import com.example.finalproject.ui.leftbar.fragments.financing.adacter.FinancingAdapter
import com.example.finalproject.ui.leftbar.fragments.financing.viewModel.FinancingViewModel
import com.example.finalproject.ui.leftbar.fragments.financing.viewModel.FinancingViewModelFactory
import com.example.finalproject.ui.leftbar.viewModel.sharedViewModel

class FinancingFragment : Fragment() {
    private lateinit var binding: FragmentFinancingBinding
    private val financingAdapter = FinancingAdapter(emptyList())
    private val sharedViewModel: sharedViewModel by activityViewModels()

    private val viewModel: FinancingViewModel by viewModels {
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

        setupNavigation()
        setupRecyclerViews()
        observeViewModelFinancing()

        sharedViewModel.productId.observe(viewLifecycleOwner) { id ->
            if (id != -1) {
                viewModel.fetchPaymentMethods()
            }
        }
        sharedViewModel.fragmentTittle.observe(viewLifecycleOwner,{

        })





    }

    private fun setupNavigation(tittle:String) {
        binding.tvImagesFragment.setOnClickListener {

            findNavController().navigate(R.id.action_financingFragment_to_imagesFragment)
        }
        binding.tvCommentsFragment.setOnClickListener {
            sharedViewModel.fragmentTittle(tittle)
            findNavController().navigate(R.id.action_financingFragment_to_commentsFragment)
        }
        binding.tvDescriptionFragment.setOnClickListener {
            sharedViewModel.fragmentTittle(tittle)
            findNavController().navigate(R.id.action_financingFragment_to_descriptionFragment)
        }
    }

    private fun setupRecyclerViews() {
        binding.rvFinancingItems.apply {
            layoutManager = LinearLayoutManager(this@FinancingFragment.context, LinearLayoutManager.VERTICAL, false)
            adapter = financingAdapter
        }
    }

    private fun observeViewModelFinancing() {
        viewModel.paymentMethods.observe(viewLifecycleOwner, Observer { paymentMethods ->
            if (paymentMethods.isNotEmpty()) {
                financingAdapter.updateData(paymentMethods)
                Log.d("FragmentFinancing", "Métodos de pago mostrados: $paymentMethods")
            } else {
                Log.d("FragmentFinancing", "No se encontraron métodos de pago")
            }
        })
    }

    private fun setTitle(){

    }
}
