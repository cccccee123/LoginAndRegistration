package com.androiddeft.loginandregistration;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.androiddeft.loginandregistration.adapter.CarArrayAdapter;
import com.androiddeft.loginandregistration.model.Car;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 private Button back;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonStr_imgs = readAssetsFile("cars.json");
        System.out.print(jsonStr_imgs);

  back=findViewById(R.id.back);
  back.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
          Intent i = new Intent(MainActivity.this, DashboardActivity.class);
          startActivity(i);
          finish();
      }
  });

        ArrayList<Car> carArrayList =jsonToArrayList_car(jsonStr_imgs);

        for (int i = 0; i< carArrayList.size(); i++) {
            System.out.println(i+ ">" + carArrayList.get(i));
        }

        CarArrayAdapter carArrayAdapter=
                new CarArrayAdapter(
                        this,
                        R.layout.row,
                        carArrayList
                );

        ListView listView=findViewById(R.id.listView);
        listView.setAdapter(carArrayAdapter);

    }

    private String readAssetsFile(String fileName) {

        try {
            InputStream inputStream = getAssets().open(fileName);


            int filesize = inputStream.available();
            byte[] bytesBuffer = new byte[filesize];

            inputStream.read(bytesBuffer);
            inputStream.close();

            String text = new String(bytesBuffer);
            return text;

        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }

    }

    private ArrayList<Car> jsonToArrayList_car(String jsonStr_countrys) {

        ArrayList<Car> countriesArrayList = new ArrayList<Car>();


        try {
            JSONObject root= new JSONObject(jsonStr_countrys);

            JSONArray jsonArray= root.getJSONArray("countries");

            for(int i=0; i<jsonArray.length();i++) {
                JSONObject jsonObject=jsonArray.getJSONObject(i);

                String name = jsonObject.getString("name");
                String flagUrl = jsonObject.getString("flag");
                String rating = jsonObject.getString("rating");
                String style = jsonObject.getString("style");








                Car car =new Car(name,flagUrl, rating,style);

                countriesArrayList.add(car);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }


        return countriesArrayList;

    }

}