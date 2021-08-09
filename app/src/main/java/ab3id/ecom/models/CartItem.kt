package ab3id.ecom.models

import com.google.gson.annotations.SerializedName

data class CartItem (@SerializedName("id") val id: Int,
                     @SerializedName("title") val title: String,
                     @SerializedName("price") val price: String)