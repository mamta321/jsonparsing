package com.kath.jsonparsing;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.view.menu.MenuView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

public class Recycler_view_adapter extends RecyclerView.Adapter<Recycler_view_adapter.MyViewHolder>{


    Context context;
    List<MenuModel>menuModels;

    public Recycler_view_adapter(Context context, List<MenuModel> menuModels) {
        this.context = context;
        this.menuModels = menuModels;
    }

    @NonNull
    @Override
    public Recycler_view_adapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_view_item,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull Recycler_view_adapter.MyViewHolder holder, int position) {
          final MenuModel menuModel=menuModels.get(position);
          holder.text_item_price.setText(menuModel.getItem_Price());
        holder.text_item_name.setText(menuModel.getItem_Name());
        holder.text_item_desc.setText(menuModel.getItem_Desc());
        //loading image in image view
        Glide.with(context)
                .load("http://vedisapp.berlin-webdesign-agentur.de/Image/"+menuModel.getImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(holder.item_image);


        //click listener in recycler view
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(MainActivity.this,"you clicked"+menuModel.getItem_Name());
                Toast.makeText(context, "you clicked"+menuModel.getItem_Name(), Toast.LENGTH_SHORT).show();
                System.out.println("Itemname"+menuModel.getItem_Name());
                Intent intent=new Intent(context,SecondActivity.class);
                intent.putExtra("name ",menuModel.getItem_Name());
                intent.putExtra("price ",menuModel.getItem_Price());


                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return menuModels.size();
    }
    class MyViewHolder extends RecyclerView.ViewHolder{

        TextView text_item_name,text_item_desc,text_item_price;
        ImageView item_image;
        public MyViewHolder(View itemView) {
            super(itemView);
           text_item_name= itemView.findViewById(R.id.item_name);
          text_item_desc= itemView.findViewById(R.id.item_desc);
          text_item_price= itemView.findViewById(R.id.item_price);
           item_image= itemView.findViewById(R.id.item_image);

        }
    }
}
