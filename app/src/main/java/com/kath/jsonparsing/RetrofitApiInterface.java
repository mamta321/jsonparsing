package com.kath.jsonparsing;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RetrofitApiInterface {
   @GET("api.php")
    Call<List<MenuModel>>getMenu();

}
