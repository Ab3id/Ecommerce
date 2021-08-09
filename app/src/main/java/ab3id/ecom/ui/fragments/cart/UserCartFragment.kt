package ab3id.ecom.ui.fragments.cart

import ab3id.ecom.R
import ab3id.ecom.adapters.AdminProductsAdapter
import ab3id.ecom.adapters.UserCartAdapter
import ab3id.ecom.data.api.ApiCalls
import ab3id.ecom.data.api.ApiClient
import ab3id.ecom.models.CartItem
import ab3id.ecom.models.StoreProduct
import ab3id.ecom.ui.fragments.shopAdmin.AdminViewModel
import ab3id.ecom.utils.customToast
import ab3id.ecom.utils.getUserID
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserCartFragment : Fragment() {

    private lateinit var cartViewModel: CartViewModel
    private lateinit var productsRecycler: RecyclerView

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        cartViewModel = ViewModelProvider(this).get(CartViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_user_cart, container, false)
        val viewManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false);
        productsRecycler = root.findViewById(R.id.cartRV);
        productsRecycler.layoutManager = viewManager;
        cartViewModel.productList.observe(viewLifecycleOwner, Observer {
            productsRecycler.adapter = UserCartAdapter(it)
        })

        loadData()
        return root;

    }

    private fun loadData(){
        var storeProducts: ArrayList<CartItem>
        val apiCalls: ApiCalls? = ApiClient().getClient()?.create(ApiCalls::class.java)
        val call: Call<ArrayList<CartItem>> = apiCalls!!.getAllCartItems(context?.getUserID())
        call.enqueue(object : Callback<ArrayList<CartItem>?> {
            override fun onResponse(
                    call: Call<ArrayList<CartItem>?>,
                    response: Response<ArrayList<CartItem>?>
            ) {
                storeProducts = response.body()!!
                cartViewModel.add(storeProducts)
                productsRecycler.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<CartItem>?>, t: Throwable) {
                productsRecycler.visibility = View.GONE
            }

        })


    }
}