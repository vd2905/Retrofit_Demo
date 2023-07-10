package Models;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Instance_class
{
    public static Api_Interface Callapi()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://darshanvarsani.000webhostapp.com/MySite/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api_Interface service = retrofit.create(Api_Interface.class);
        return service;
    }
}