package ab3id.ecom.ui.fragments.shopAdmin

import ab3id.ecom.models.StoreProduct
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class AdminViewModel : ViewModel() {
    var productList = MutableLiveData<ArrayList<StoreProduct>>()
//    var newlist = arrayListOf<StoreProduct>()

    fun add(products: ArrayList<StoreProduct>){
//        newlist.add(product)
        productList.value=products
    }
}