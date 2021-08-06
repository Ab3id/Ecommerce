package ab3id.ecom.data.api

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
        @Field("price") price: String?
    ): Call<StoreProduct>


    @GET("Product/index.php")
    fun getAllProducts():Call<ArrayList<StoreProduct>>

}