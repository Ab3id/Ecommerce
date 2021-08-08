package ab3id.ecom.adapters

import ab3id.ecom.R
import ab3id.ecom.models.StoreProduct
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load

class AdminProductsAdapter (val productsList:ArrayList<StoreProduct>) :
RecyclerView.Adapter<AdminProductsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AdminProductsAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.admin_product_item, parent, false)
        return ViewHolder(v)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvTitle: TextView = itemView.findViewById(R.id.adminProdTitle);
        val tvStore: TextView = itemView.findViewById(R.id.storeName);
        val tvAdminPrice: TextView = itemView.findViewById(R.id.tvAdminPrice);
        val tvLatLng: TextView = itemView.findViewById(R.id.tvLatLng);
        val img: ImageView = itemView.findViewById(R.id.adminProductImage);

        fun bindItems(product: StoreProduct) {
            tvTitle.text = product.title
            tvStore.text = product.storeName
            tvAdminPrice.text = product.price
            tvLatLng.text = "Store Coordinates : ${product.lat} ${product.lng}"
            img.load(product.img){
                crossfade(true)
                placeholder(R.drawable.female_top)

            }
        }}

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productsList[position])
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}