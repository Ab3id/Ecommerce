package ab3id.ecom.data.api

import ab3id.ecom.models.CartItem
import ab3id.ecom.models.StoreProduct
import ab3id.ecom.models.UserAccount
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiCalls {
    @POST("User/register.php")
    @FormUrlEncoded
    fun userRegister(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<UserAccount>

    @FormUrlEncoded
    @POST("User/login.php")
    fun userLogin(
        @Field("username") username: String,
        @Field("password") password: String
    ): Call<UserAccount>

    @FormUrlEncoded
    @POST("Product/create.php")
    fun adminCreateProduct(
        @Field("img") img: String?,
        @Field("title") title: String?,
        @Field("price") price: String?,
        @Field("store_name") store_name: String?,
        @Field("lat") lat: String?,
        @Field("lng") lng: String?
    ): Call<StoreProduct>

    @FormUrlEncoded
    @POST("Cart/create.php")
    fun createCartItem( @Field("userid") id: Int?, @Field("title") title: String?, @Field("price") price: String?):Call<String>

    @FormUrlEncoded
    @POST("Cart/index.php")
    fun getAllCartItems(@Field("uid") id: Int?):Call<ArrayList<CartItem>>


    @GET("Product/index.php")
    fun getAllProducts():Call<ArrayList<StoreProduct>>

}