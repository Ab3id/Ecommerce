package ab3id.ecom.models

import com.google.gson.annotations.SerializedName

data class UserAccount(
        @SerializedName("id") val id:Int,
    @SerializedName("username") val username:String,
    @SerializedName("role") val userRole:String)