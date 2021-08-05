package ab3id.ecom.ui.fragments.home

import ab3id.ecom.R
import ab3id.ecom.adapters.StoreProductsAdapter
import ab3id.ecom.models.StoreProduct
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView


class HomeFragment : Fragment() {

    private lateinit var homeViewModel: HomeViewModel
    private lateinit var productsRecycler:RecyclerView


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
        productsRecycler.layoutManager = viewManager
        homeViewModel.productList.observe(viewLifecycleOwner, Observer {
            productsRecycler.adapter = StoreProductsAdapter(it)
        })

        addData()
        return root
    }



    fun addData(){
        val storeProducts = ArrayList<StoreProduct>()
        storeProducts.add(StoreProduct("","Organic Cotton","TSH 8500"))
        storeProducts.add(StoreProduct("","Cotton Shirt","TSH 9500"))
        storeProducts.add(StoreProduct("","Armani Shirt","TSH 1500"))
        storeProducts.add(StoreProduct("","Gucci Shirt","TSH 10500"))
        homeViewModel.add(storeProducts)
        productsRecycler.adapter?.notifyDataSetChanged()

    }
}