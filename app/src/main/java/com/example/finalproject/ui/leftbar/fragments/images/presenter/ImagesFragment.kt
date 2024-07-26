package com.example.finalproject.ui.leftbar.fragments.images.presenter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.databinding.FragmentImagesBinding
import com.example.finalproject.ui.leftbar.fragments.images.state.ImagesState
import com.example.finalproject.ui.leftbar.fragments.images.viewModel.ImagesViewModel
import com.example.finalproject.ui.leftbar.fragments.images.viewModel.ImagesViewModelFactory
import com.squareup.picasso.Picasso

class ImagesFragment : Fragment() {

    private lateinit var binding: FragmentImagesBinding
    private val viewModel: ImagesViewModel by viewModels {
        ImagesViewModelFactory(LeftbarRepository(requireContext()))
    }

    companion object {
        private const val ARG_PRODUCT_ID = "product_id"

        fun newInstance(productId: Int): ImagesFragment {
            val instanceFragmentImg = ImagesFragment()
            val args = Bundle()
            args.putInt(ARG_PRODUCT_ID, productId)
            instanceFragmentImg.arguments = args
            return instanceFragmentImg
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = FragmentImagesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val productId = arguments?.getInt(ARG_PRODUCT_ID, -1) ?: -1

        if (productId != -1) {
            viewModel.fetchProductById(productId)
        }

        // Observa los cambios en el estado
        viewModel.state.observe(viewLifecycleOwner) { state ->
            when (state) {
                is ImagesState.Loading -> showLoading()
                is ImagesState.Success -> showProductDetails(state.product)
                is ImagesState.Error -> showError()
            }
        }
    }

    private fun showLoading() {
//        binding.progressBar.visibility = View.VISIBLE
        binding.ivImgError.visibility = View.GONE
    }

    private fun showProductDetails(product: Product) {
//        binding.progressBar.visibility = View.GONE
        binding.ivImgError.visibility = View.GONE
        binding.tvNameProduct.text = product.name
        binding.tvPriceProduct.text = product.price.toString()

        // Aquí se obtiene la primera imagen de la lista de imágenes
//        val imageUrl = product.images?.firstOrNull()?.link ?: ""

        Picasso.get()
            .load(product.image)
            .centerInside()
            .placeholder(R.drawable.imgerror)
            .error(R.drawable.errornotphoto)
            .into(binding.ivProduct)
        Log.d("ImagesFragment", "Image URL: ${product.image}")
    }

    private fun showError() {
//        binding.progressBar.visibility = View.GONE
        binding.ivImgError.visibility = View.VISIBLE
        binding.ivIcError.visibility = View.VISIBLE
        binding.tvErrorMessage.text = View.VISIBLE.toString()
    }
}