package com.example.finalproject.ui.leftbar.fragments.images.presenter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.finalproject.R
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.databinding.FragmentImagesBinding
import com.example.finalproject.ui.leftbar.fragments.images.adapter.ImagesAdapter
import com.example.finalproject.ui.leftbar.fragments.images.state.ImagesState
import com.example.finalproject.ui.leftbar.fragments.images.viewModel.ImagesViewModel
import com.example.finalproject.ui.leftbar.fragments.images.viewModel.ImagesViewModelFactory

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
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

            val productId = arguments?.getInt(ARG_PRODUCT_ID, -1) ?: -1

            if (productId != -1) {
                viewModel.fetchProductById(productId)
                binding.tvDescriptionFragment.setOnClickListener {
                    findNavController().navigate(R.id.action_imagesFragment_to_descriptionFragment)
                }


                navigateToFragment()


                // Observa los cambios en el estado
                viewModel.state.observe(viewLifecycleOwner) { state ->
                    when (state) {
                        is ImagesState.Loading -> showLoading()
                        is ImagesState.Success -> showProductDetails(state.product)
                        is ImagesState.Error -> showError()
                    }
                    binding.tvFinancingFragment.setOnClickListener {
                        findNavController().navigate(R.id.action_imagesFragment_to_financingFragment)
                    }
                }

                private fun showLoading() {
//        binding.progressBar.visibility = View.VISIBLE
//        binding.contentLayout.visibility = View.GONE
                    binding.ivImgError.visibility = View.GONE
                }

                private fun showProductDetails(product: Product) {
                    binding.ivImgError.visibility = View.GONE

                    binding.tvNameProduct.text = product.name
                    binding.tvPriceProduct.text = product.price.toString()


                    val adapter = ImagesAdapter(product.images ?: emptyList())
                    binding.rvImgfragment.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    binding.rvImgfragment.adapter = adapter
                }


                private fun showError() {
                    binding.ivImgError.visibility = View.VISIBLE
                    binding.ivIcError.visibility = View.VISIBLE
                    binding.tvErrorMessage.text = View.VISIBLE.toString()

                }

            }
        }
    }
    }
//
//    private fun navigateToFragment() {
////        binding.tvFinancingFragment.setOnClickListener {
////            findNavController().navigate(R.id.action_imagesFragment_to_financingFragment)
////        }
//        binding.tvCommentsFragment.setOnClickListener {
//            findNavController().navigate(R.id.action_imagesFragment_to_commentsFragment)
////            fragmentManager?.beginTransaction()?.replace(R.id.nav_graph_fragment, CommentsFragment())?.commit()
//        }
//        binding.tvImagesFragment.setOnClickListener {
//
////            fragmentManager?.beginTransaction()?.replace(R.id.nav_graph_fragment, CommentsFragment())?.commit()
//        }
////        binding.tvDescriptionFragment.setOnClickListener {
////            findNavController().navigate(R.id.action_financingFragment_to_descriptionFragment)
////        }
//    }
//}