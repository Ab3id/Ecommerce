package ab3id.ecom.adapters

import ab3id.ecom.R
import ab3id.ecom.models.StoreProduct
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class StoreProductsAdapter(val productsList:ArrayList<StoreProduct>) :
    RecyclerView.Adapter<StoreProductsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StoreProductsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.storeproduct_item, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(product: StoreProduct) {
            val tvTitle = itemView.findViewById<TextView>(R.id.product_title)
            val tvProce  = itemView.findViewById<TextView>(R.id.product_price)
            val image = itemView.findViewById<ImageView>(R.id.product_image)
            tvTitle.text = product.title
            tvProce.text = product.price
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productsList[position])
    }

    override fun getItemCount(): Int {
       return productsList.size
    }
}