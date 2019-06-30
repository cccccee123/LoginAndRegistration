package com.androiddeft.loginandregistration;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.androiddeft.loginandregistration.adapter.CarArrayAdapter;
import com.androiddeft.loginandregistration.model.Car;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Cardetail extends AppCompatActivity {
    private Button back;
    private ImageView flag_imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cardetail);

        back = findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Cardetail.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        flag_imageView=findViewById(R.id.flag_imageView);
        String url="https://i.imgur.com/GXs6de6.png";

        Picasso.with(this).load(url).into(flag_imageView);
    }
}
