package com.kath.jsonparsing;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        String itemname= getIntent().getExtras().getString("name");
        String itemprice= getIntent().getExtras().getString("price");
        Toast.makeText(this, " price of "+itemname+" is "+itemprice, Toast.LENGTH_SHORT).show();
    }
}
