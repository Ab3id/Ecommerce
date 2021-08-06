package ab3id.ecom.models

import com.google.gson.annotations.SerializedName

data class StoreProduct(
    @SerializedName("img") val img:String,
    @SerializedName("title") val title:String,
    @SerializedName("price") val price:String)