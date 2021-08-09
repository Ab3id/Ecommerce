package ab3id.ecom.adapters

import ab3id.ecom.R
import ab3id.ecom.models.CartItem
import ab3id.ecom.models.StoreProduct
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UserCartAdapter(val productsList:ArrayList<CartItem>) : RecyclerView.Adapter<UserCartAdapter.ViewHolder>() {
    lateinit var thisContext: Context
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindItems(product: CartItem) {
            val tvTitle = itemView.findViewById<TextView>(R.id.ctitle)
            val tvprice = itemView.findViewById<TextView>(R.id.cprice)
            tvTitle.text = product.title;
            tvprice.text = product.price;
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.cart_item, parent, false)
        thisContext = v.context
        return ViewHolder(v)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(productsList[position])
    }

    override fun getItemCount(): Int {
        return productsList.size
    }
}