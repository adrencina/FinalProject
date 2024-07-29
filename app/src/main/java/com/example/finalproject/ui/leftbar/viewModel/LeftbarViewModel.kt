import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.finalproject.data.dto.response.CommentsResponse
import com.example.finalproject.data.dto.response.PaymentMethodsResponse
import com.example.finalproject.data.dto.response.Product
import com.example.finalproject.data.repository.LeftbarRepository
import com.example.finalproject.ui.leftbar.fragments.comments.state.CommentsState

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

    // LiveData para el estado
    private val _state = MutableLiveData<CommentsState>()
    val state: LiveData<CommentsState> get() = _state



    // Función para obtener todos los productos
    fun fetchAllProducts() {
        viewModelScope.launch {
            val result = repository.getAllProducts()
            // Procesar los productos si es necesario
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
