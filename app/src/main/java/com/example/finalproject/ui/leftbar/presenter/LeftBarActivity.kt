package com.example.finalproject.ui.leftbar.presenter

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.finalproject.R
import com.example.finalproject.databinding.ActivityLeftBarBinding
import com.example.finalproject.ui.leftbar.viewModel.sharedViewModel
import com.example.finalproject.ui.leftbar.fragments.images.presenter.ImagesFragment

class LeftBarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLeftBarBinding
    private val sharedViewModel : sharedViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLeftBarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val receivedProductPrice = intent.getIntExtra("productPrice", -1)
        sharedViewModel.productPricevalue(receivedProductPrice.toString())


        val receivedProductId = intent.getIntExtra("idProduct", -1)
        sharedViewModel.productIdvalue(receivedProductId)





//        if (savedInstanceState == null) {
//
//            // Instancias de los Fragments
////            val instanceFragmentImg = ImagesFragment.newInstance(receivedProductId)
//            val instanceFragmentComm = CommentsFragment.newInstance(receivedProductId)
////            val instanceFragmentFin = FinancingFragment.newInstance(receivedProductId)
////            val instanceFragmentDesc = DescriptionFragment.newInstance(receivedProductId)
//
//            // Esto añade los Instance Fragments al contenedor
//            supportFragmentManager.beginTransaction()
//                .add(R.id.nav_graph_fragment, instanceFragmentComm)
//
//
//
//                .commit()
//        }
    }
}



//
//            binding.navImagesButton.setOnClickListener {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.nav_graph_fragment, instanceFragmentImg)
//                    .commit()
//            }
//            binding.navCommentsButton.setOnClickListener {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.nav_graph_fragment, instanceFragmentComm)
//                    .commit()
//            }
//            binding.navFinancingButton.setOnClickListener {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.nav_graph_fragment, instanceFragmentFin)
//                    .commit()
//            }
//            binding.navDescriptionButton.setOnClickListener {
//                supportFragmentManager.beginTransaction()
//                    .replace(R.id.nav_graph_fragment, instanceFragmentDesc)
//                    .commit()
//            }

//                .commit()
//        }
//    }
//}


//                    instanceFragmentFin,
//                    instanceFragmentDesc,
//                    instanceFragmentComm