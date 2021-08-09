package ab3id.ecom.ui.fragments.cart

import ab3id.ecom.models.CartItem
import ab3id.ecom.models.StoreProduct
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {
    var productList = MutableLiveData<ArrayList<CartItem>>()

    fun add(products: ArrayList<CartItem>){
//        newlist.add(product)
        productList.value=products
    }
}