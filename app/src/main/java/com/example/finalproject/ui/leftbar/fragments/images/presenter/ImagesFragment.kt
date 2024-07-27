package com.example.finalproject.ui.leftbar.fragments.images.presenter

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.finalproject.R
import com.example.finalproject.databinding.FragmentImagesBinding

class ImagesFragment : Fragment() {
    private lateinit var binding: FragmentImagesBinding



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

        binding.tvDescriptionFragment.setOnClickListener {
            findNavController().navigate(R.id.action_imagesFragment_to_descriptionFragment)
        }
        binding.tvFinancingFragment.setOnClickListener {
            findNavController().navigate(R.id.action_imagesFragment_to_financingFragment)
        }
        binding.tvCommentsFragment.setOnClickListener {
            findNavController().navigate(R.id.action_imagesFragment_to_commentsFragment)
//            fragmentManager?.beginTransaction()?.replace(R.id.nav_graph_fragment, CommentsFragment())?.commit()
        }
        binding.tvImagesFragment.setOnClickListener {

//            fragmentManager?.beginTransaction()?.replace(R.id.nav_graph_fragment, CommentsFragment())?.commit()
        }
    }
}