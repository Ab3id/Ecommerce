package ab3id.ecom.models

import com.google.gson.annotations.SerializedName

data class StoreProduct(
    @SerializedName("img") val img:String,
    @SerializedName("title") val title:String,
    @SerializedName("price") val price:String,
    @SerializedName("store_title") val storeName:String,
    @SerializedName("store_lat") val lat:String,
    @SerializedName("store_lng") val lng:String)