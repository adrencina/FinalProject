package com.example.finalproject.ui.leftbar.fragments.description.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.databinding.FragmentDescriptionBinding
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState
import com.example.finalproject.ui.leftbar.fragments.description.viewModel.DescriptionViewModel
import com.example.finalproject.ui.leftbar.fragments.description.viewModel.DescriptionViewModelFactory
import com.example.finalproject.ui.leftbar.viewModel.SharedFragViewModel

class DescriptionFragment : Fragment() {

    private lateinit var binding: FragmentDescriptionBinding
    private val sharedViewModel: SharedFragViewModel by activityViewModels()
    private val viewModel: DescriptionViewModel by viewModels {
        DescriptionViewModelFactory(LeftbarRepository(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflar el layout para este fragmento
        binding = FragmentDescriptionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Observar cambios en el ID del producto en el ViewModel compartido
        sharedViewModel.productId.observe(viewLifecycleOwner) { id ->
            Log.d("DescriptionFragment", "productId observado: $id")
            if (id != -1) {
                viewModel.fetchProductById(id)
            }
        }

        // Observar cambios en el precio del producto en el ViewModel compartido

        observeViewModel()
        // Configurar la navegación entre fragmentos
        setupNavigation()

        // Configurar el botón de regreso a HomeActivity

    }

    private fun setupNavigation() {
        binding.tvImagesFragment.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_imagesFragment)
        }
        binding.tvFinancingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_financingFragment)
        }
        binding.tvCommentsFragment.setOnClickListener {
            findNavController().navigate(R.id.action_descriptionFragment_to_commentsFragment)
        }
    }

    private fun observeViewModel() {
        viewModel.descriptionState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is DescriptionState.Loading -> showLoading()
                is DescriptionState.Success -> showDescription(state.product)
                is DescriptionState.Error -> showError(state.message)
            }
        }
    }

    private fun showLoading() {
        Log.d("DescriptionFragment", "Cargando...")
        // Manejar el estado de carga aquí
    }

    private fun showDescription(product: Product) {
        Log.d("DescriptionFragment", "Descripción mostrada: $product")
        binding.tvDescription.text = product.description


    }

    private fun showError(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        Log.e("DescriptionFragment", message)
    }
}
