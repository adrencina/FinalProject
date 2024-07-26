import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.finalproject.data.dto.response.ProductImage
import com.example.finalproject.databinding.ItemRvImgfragmentBinding
import com.squareup.picasso.Picasso

class ImagesAdapter(private val productImages: List<ProductImage>) :
    RecyclerView.Adapter<ImagesAdapter.ImagesViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ImagesViewHolder {
        val binding = ItemRvImgfragmentBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ImagesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ImagesViewHolder, position: Int) {
        val productImage = productImages[position]
        holder.bind(productImage)
    }

    override fun getItemCount(): Int {
        return productImages.size
    }

    inner class ImagesViewHolder(private val binding: ItemRvImgfragmentBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(productImage: ProductImage) {
            Picasso.get().load(productImage.link).into(binding.ivImage)
        }
    }
}