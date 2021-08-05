package ab3id.ecom.ui.fragments.home

import ab3id.ecom.models.StoreProduct
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.GridLayoutManager

class HomeViewModel : ViewModel() {


//    private val _text = MutableLiveData<String>().apply {
//        value = "This is home Fragment"
//    }
//    val text: LiveData<String> = _text

    var productList = MutableLiveData<ArrayList<StoreProduct>>()
//    var newlist = arrayListOf<StoreProduct>()

    fun add(products: ArrayList<StoreProduct>){
//        newlist.add(product)
        productList.value=products
    }




}