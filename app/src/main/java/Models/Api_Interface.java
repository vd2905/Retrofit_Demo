package Models;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api_Interface
{
    @FormUrlEncoded
    @POST("Register.php")
    Call<Register_Class> registerUser(@Field("name") String name, @Field("email") String email, @Field("password") String password);

    @FormUrlEncoded
    @POST("login.php")
    Call<Login_Class> loginUser(@Field("email") String email,@Field("password") String password);

    @FormUrlEncoded
    @POST("addProduct.php")
    Call<Add_Product_Class> addproduct(@Field("sellerid") int sellerid, @Field("name") String name, @Field("stock") String stock, @Field("price") String price, @Field("category") String category, @Field("productimage") String imagedata);

    @FormUrlEncoded
    @POST("viewProduct.php")
    Call<View_Product_Class> viewproduct(@Field("sellerid") int sellerid);

    @FormUrlEncoded
    @POST("updateproduct.php")
    Call<Register_Class> updateproduct(@Field("name") String name, @Field("stock") String stock, @Field("price") String price, @Field("category") String category,@Field("id") String id);

    @FormUrlEncoded
    @POST("deleteproduct.php")
    Call<Register_Class> deleteproduct(@Field("id") int id);
}