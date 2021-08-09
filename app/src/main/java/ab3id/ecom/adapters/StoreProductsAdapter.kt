package ab3id.ecom.adapters

import ab3id.ecom.R
import ab3id.ecom.data.api.ApiCalls
import ab3id.ecom.data.api.ApiClient
import ab3id.ecom.models.StoreProduct
import ab3id.ecom.utils.customToast
import ab3id.ecom.utils.getUserID
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreProductsAdapter(val productsList:ArrayList<StoreProduct>) :
    RecyclerView.Adapter<StoreProductsAdapter.ViewHolder>() {
    lateinit var thisContext:Context
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.storeproduct_item, parent, false)
        thisContext = v.context
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(product: StoreProduct) {
            val tvTitle = itemView.findViewById<TextView>(R.id.product_title)
            val tvProce  = itemView.findViewById<TextView>(R.id.product_price)
            val image = itemView.findViewById<ImageView>(R.id.product_image)
            val cartIcon = itemView.findViewById<ImageView>(R.id.cartIcon)
            val tvStoreLocation = itemView.findViewById<TextView>(R.id.store_location);
            cartIcon.setOnClickListener {
                saveCartItem(product.title,product.price)
            }
            tvTitle.text = product.title
            tvProce.text = product.price
            image.load(product.img){
                crossfade(true)
                placeholder(R.drawable.female_top)

            }
            tvStoreLocation.setOnClickListener {
                val bundle = bundleOf("store_name" to product.storeName,"lat" to product.lat,"lng" to product.lng)
                run {
                    Navigation.findNavController(it).navigate(R.id.goToMapsAction,bundle)
                }
            }
        }

        fun saveCartItem(title:String,price:String){
            itemView.context.customToast("Adding to cart")
            val apiCalls: ApiCalls? = ApiClient().getClient()?.create(ApiCalls::class.java)
            val call: Call<String> = apiCalls!!.createCartItem(itemView.context.getUserID(),title,price)
            call.enqueue(object : Callback<String> {
                override fun onResponse(
                        call: Call<String?>,
                        response: Response<String>?
                ) {
                    if (response!!.isSuccessful) {
                        itemView.context.customToast("Item Added To Cart")
                    }
                }

                override fun onFailure(call: Call<String>?, t: Throwable) {
                    itemView.context.customToast("Failed to save cart item")
                }


            })


        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productsList[position])
    }

    override fun getItemCount(): Int {
       return productsList.size
    }


}