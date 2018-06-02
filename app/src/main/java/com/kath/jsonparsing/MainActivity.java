package com.kath.jsonparsing;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
      RecyclerView recycleView;
      ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recycleView=findViewById(R.id.recycleView);
        progressDialog=new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("loading....");
        progressDialog.setCancelable(false);
        progressDialog.show();


        getMenuJson();

    }
    public void getMenuJson(){
        RetrofitApiInterface retrofitApiInterface = RetroFitClient.getRetrofit().create(RetrofitApiInterface.class);
        Call<List<MenuModel>> modelClassCall= retrofitApiInterface.getMenu();
   modelClassCall.enqueue(new Callback<List<MenuModel>>() {
       @Override
       public void onResponse(Call<List<MenuModel>> call, Response<List<MenuModel>> response) {
          Recycler_view_adapter recycler_view_adapter=new Recycler_view_adapter(MainActivity.this,response.body());
         RecyclerView.LayoutManager layoutManager=new GridLayoutManager(MainActivity.this,2);
         // LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(MainActivity.this, LinearLayoutManager.HORIZONTAL, false);
         // recycleView.setLayoutManager(horizontalLayoutManager);
       recycleView.setHasFixedSize(true);
       recycleView.setLayoutManager(layoutManager);
       recycleView.setItemAnimator(new DefaultItemAnimator());
       recycleView.setAdapter(recycler_view_adapter);
       recycler_view_adapter.notifyDataSetChanged();
       if (progressDialog.isShowing()){
           progressDialog.dismiss();
       }
       }

       @Override
       public void onFailure(Call<List<MenuModel>> call, Throwable throwable) {

       }
   });
    }
}
