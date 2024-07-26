import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.ui.leftbar.fragments.description.state.DescriptionState

class LeftbarViewModel(private val repository: LeftbarRepository) : ViewModel()  {


    // LiveData para el producto
    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> get() = _product

    // LiveData para los comentarios
    private val _comments = MutableLiveData<CommentsResponse>()
    val comments: LiveData<CommentsResponse> get() = _comments

    // LiveData para los métodos de pago
    private val _paymentMethods = MutableLiveData<PaymentMethodsResponse>()
    val paymentMethods: LiveData<PaymentMethodsResponse> get() = _paymentMethods

    // Función para obtener un producto por su id
    fun fetchProductById(productId: Int) {
        viewModelScope.launch {
            val fetchedProduct = repository.getProductById(productId)
            _product.postValue(fetchedProduct)
        }
    }


    // Función para obtener todos los productos
    fun fetchAllProducts() {
        viewModelScope.launch {
            val result = repository.getAllProducts()
            // Procesar los productos si es necesario
        }
    }

    // Función para obtener comentarios por id de producto
    fun fetchComments(idProduct: Int) {
        viewModelScope.launch {
            val result = repository.getComments(idProduct)
            _comments.postValue(result)
        }
    }

    // Función para obtener métodos de pago
    fun fetchPaymentMethods() {
        viewModelScope.launch {
            val result = repository.getPaymentMethods()
            _paymentMethods.postValue(result)
        }
    }
}
