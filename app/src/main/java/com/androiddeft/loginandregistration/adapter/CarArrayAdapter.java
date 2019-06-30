package com.androiddeft.loginandregistration.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.androiddeft.loginandregistration.R;
import com.androiddeft.loginandregistration.model.Car;
import com.squareup.picasso.Picasso;


import java.util.ArrayList;

/**
 * Created by stcp on 18/6/2019.
 */

public class CarArrayAdapter  extends ArrayAdapter<Car> {
    private Context context;
    private int listView_row;
    private ArrayList<Car> cars;


    public CarArrayAdapter(@NonNull Context context, int listView_row,@NonNull ArrayList<Car> wheel) {
        super(context, listView_row,wheel);

        this.context=context;
        this.listView_row=listView_row;
        this.cars=wheel;

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;

        CarHolder carHolder=null;

        LayoutInflater layoutInflater=
                (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if(row==null) {
            row= layoutInflater.inflate(R.layout.row,parent,false);

            carHolder=new CarHolder();

            carHolder.flag_ImageView=row.findViewById(R.id.flag_imageView);
            carHolder.name_TextView=row.findViewById(R.id.name_TextView);
            carHolder.style_TextView=row.findViewById(R.id.style_TextView);
            carHolder.rating_TextView=row.findViewById(R.id.rating_TextView);

            carHolder.flag_ImageView=row.findViewById(R.id.flag_imageView);
            carHolder.name_TextView=row.findViewById(R.id.name_TextView);
            carHolder.style_TextView=row.findViewById(R.id.style_TextView);
            carHolder.rating_TextView=row.findViewById(R.id.rating_TextView);



            row.setTag(carHolder);
        }else  {
            carHolder=(CarHolder) row.getTag();

        }
        Car car =cars.get(position);


        Picasso
                .with(context)
                .load(car.img)
                .into(carHolder.flag_ImageView);

        carHolder.name_TextView.setText(car.name);
        carHolder.style_TextView.setText(car.style);
        carHolder.rating_TextView.setText(car.rating);

        return row;
    }


    private class CarHolder {
        ImageView flag_ImageView;
        TextView name_TextView;
        TextView rating_TextView;
        TextView style_TextView;







    }


}