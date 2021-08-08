package ab3id.ecom.ui.fragments.home

import ab3id.ecom.R
import ab3id.ecom.adapters.StoreProductsAdapter
import ab3id.ecom.data.api.ApiCalls
import ab3id.ecom.data.api.ApiClient
import ab3id.ecom.models.StoreProduct
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.button.MaterialButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var productsRecycler:RecyclerView
    private lateinit var tvNoData:TextView


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)
            val root = inflater.inflate(R.layout.fragment_home, container, false)
        val viewManager = GridLayoutManager(root.context,2)
        productsRecycler = root.findViewById(R.id.rvProducts);
        tvNoData = root.findViewById(R.id.tvNoData);
        productsRecycler.layoutManager = viewManager
        homeViewModel.productList.observe(viewLifecycleOwner, Observer {
            productsRecycler.adapter = StoreProductsAdapter(it)
        })
        val viewShopButton:MaterialButton = root.findViewById(R.id.btnViewShop);
        viewShopButton.setOnClickListener { v ->
            run {
                Navigation.findNavController(v).navigate(R.id.goToMapsAction)
            }
        }

        addData()
        return root
    }



    fun addData(){

        var storeProducts: ArrayList<StoreProduct>
        val apiCalls: ApiCalls? = ApiClient().getClient()?.create(ApiCalls::class.java)
        val call: Call<ArrayList<StoreProduct>> = apiCalls!!.getAllProducts()
        call.enqueue(object  : Callback<ArrayList<StoreProduct>?>{
            override fun onResponse(
                call: Call<ArrayList<StoreProduct>?>,
                response: Response<ArrayList<StoreProduct>?>
            ) {
                storeProducts = response.body()!!
                homeViewModel.add(storeProducts)
                productsRecycler.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<StoreProduct>?>, t: Throwable) {
                productsRecycler.visibility = View.GONE
                tvNoData.visibility = View.VISIBLE
            }



        })


    }
}