package ab3id.ecom.ui.fragments.shopAdmin

import ab3id.ecom.R
import ab3id.ecom.adapters.AdminProductsAdapter
import ab3id.ecom.data.api.ApiCalls
import ab3id.ecom.data.api.ApiClient
import ab3id.ecom.models.StoreProduct
import android.app.AlertDialog
import android.os.Bundle
import android.text.InputType
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class AdminFragment  : Fragment() {
    private lateinit var adminViewModel: AdminViewModel
    private lateinit var productsRecycler: RecyclerView
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
         super.onCreateView(inflater, container, savedInstanceState)
        adminViewModel = ViewModelProvider(this).get(AdminViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_shop_admin, container, false)
        val fabAddProduct:FloatingActionButton = root.findViewById(R.id.fabAddProduct);
        productsRecycler = root.findViewById(R.id.adminRecycler);
        val viewManager = LinearLayoutManager(root.context, LinearLayoutManager.VERTICAL, false);
        productsRecycler.layoutManager = viewManager
        adminViewModel.productList.observe(viewLifecycleOwner, Observer {
            productsRecycler.adapter = AdminProductsAdapter(it)
        })
        fabAddProduct.setOnClickListener {
            run {
//                Toast.makeText(root.context, "Admin Add Products", Toast.LENGTH_SHORT).show()
                showdialog()
            }
        }
        loadData()
        return root;
    }

    fun showdialog(){
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle("Add New Product")
        val layout = LinearLayout(context)
        layout.orientation = LinearLayout.VERTICAL
        val inputTitle = EditText(context)
        inputTitle.setHint("Product Title")
        inputTitle.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(inputTitle)
        val inputPrice = EditText(context)
        inputPrice.setHint("Product Price")
        inputPrice.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(inputPrice)
        val inputImgLing = EditText(context)
        inputImgLing.setHint("Product Image Link")
        inputImgLing.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(inputImgLing)
        val inputStoreName = EditText(context)
        inputStoreName.setHint("Store Name")
        inputStoreName.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(inputStoreName)
        val inputStoreLat = EditText(context)
        inputStoreLat.setHint("Store Location Latitude")
        inputStoreLat.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(inputStoreLat)
        val inputStoreLng = EditText(context)
        inputStoreLng.setHint("Store Location Longitude")
        inputStoreLng.inputType = InputType.TYPE_CLASS_TEXT
        layout.addView(inputStoreLng)
        builder.setView(layout)


        builder.setPositiveButton("OK") { dialog, _ ->
            val title = inputTitle.text.toString()
            val price = inputPrice.text.toString()
            val name = inputStoreName.text.toString()
            val lat = inputStoreLat.text.toString()
            val long = inputStoreLng.text.toString()
            val img = inputImgLing.text.toString()

            Toast.makeText(context, "Saving, Please wait", Toast.LENGTH_SHORT).show()
            dialog.cancel()

            saveData(title, img, price, name, lat, long)
        }
        builder.setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }

        builder.show()
    }

    private fun saveData(
        title: String,
        img: String,
        price: String,
        name: String,
        lat: String,
        lng: String
    ){
        val apiCalls: ApiCalls? = ApiClient().getClient()?.create(ApiCalls::class.java)
        val call: Call<StoreProduct> = apiCalls!!.adminCreateProduct(
            img,
            title,
            price,
            name,
            lat,
            lng
        )
        call.enqueue(object : Callback<StoreProduct> {
            override fun onResponse(call: Call<StoreProduct>, response: Response<StoreProduct>) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Item Saved!", Toast.LENGTH_SHORT).show()
                    loadData()
                }
            }

            override fun onFailure(call: Call<StoreProduct>, t: Throwable) {
                Toast.makeText(context, "An Error Occured", Toast.LENGTH_SHORT).show()
            }

        })
    }


    private fun loadData(){

        var storeProducts: ArrayList<StoreProduct>
        val apiCalls: ApiCalls? = ApiClient().getClient()?.create(ApiCalls::class.java)
        val call: Call<ArrayList<StoreProduct>> = apiCalls!!.getAllProducts()
        call.enqueue(object : Callback<ArrayList<StoreProduct>?> {
            override fun onResponse(
                call: Call<ArrayList<StoreProduct>?>,
                response: Response<ArrayList<StoreProduct>?>
            ) {
                storeProducts = response.body()!!
                adminViewModel.add(storeProducts)
                productsRecycler.adapter?.notifyDataSetChanged()
            }

            override fun onFailure(call: Call<ArrayList<StoreProduct>?>, t: Throwable) {
                productsRecycler.visibility = View.GONE
            }

        })


    }
}