package com.example.finalproject.ui.leftbar.fragments

import LeftbarViewModel
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.data.service.LeftbarApiService
import com.example.finalproject.data.service.LeftbarApiServiceImp
import com.example.finalproject.data.service.RetrofitInstance
import com.example.finalproject.databinding.FragmentImagesBinding
import com.example.finalproject.ui.leftbar.viewModel.LeftbarViewModelFactory

class ImagesFragment : Fragment() {
    private lateinit var binding: FragmentImagesBinding
    private val viewModel: LeftbarViewModel by viewModels {
        val context = requireContext()
        val apiService = RetrofitInstance.getGeneralRetrofit(context).create(LeftbarApiService::class.java)
        val repository = LeftbarRepository(LeftbarApiServiceImp(apiService))
        LeftbarViewModelFactory(repository)
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observa los cambios en el LiveData del producto
        viewModel.product.observe(viewLifecycleOwner, Observer { product ->
            product?.let {
                binding.tvName.text = it.name ?: "Nombre no disponible"
            }
        })


        val productId = 1
        viewModel.fetchProductById(productId)


        binding.tvDescriptionFragment.setOnClickListener {
            findNavController().navigate(R.id.action_imagesFragment_to_descriptionFragment)
        }
        binding.tvFinancingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_imagesFragment_to_financingFragment)
        }
        binding.tvCommentsFragment.setOnClickListener {
            findNavController().navigate(R.id.action_imagesFragment_to_commentsFragment)
        }
    }
}