package ab3id.ecom.adapters

import ab3id.ecom.R
import ab3id.ecom.models.StoreProduct
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation

class StoreProductsAdapter(val productsList:ArrayList<StoreProduct>) :
    RecyclerView.Adapter<StoreProductsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.storeproduct_item, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(product: StoreProduct) {
            val tvTitle = itemView.findViewById<TextView>(R.id.product_title)
            val tvProce  = itemView.findViewById<TextView>(R.id.product_price)
            val image = itemView.findViewById<ImageView>(R.id.product_image)
            val tvStoreLocation = itemView.findViewById<TextView>(R.id.store_location);
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
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productsList[position])
    }

    override fun getItemCount(): Int {
       return productsList.size
    }
}