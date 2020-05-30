package com.example.fourthapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;

public class NumberArrayAdapter extends ArrayAdapter {
    Context context;
    ArrayList<Number> numbers;
    int resource;
    public NumberArrayAdapter(@NonNull Context context, int resource, ArrayList<Number> numbers) {
        super(context, resource, numbers);
        this.context = context;
        this.numbers = numbers;
        this.resource = resource;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row, parent, false);


        TextView tvID = (TextView) rowView.findViewById(R.id.tvID);
        TextView tvNumber = (TextView) rowView.findViewById(R.id.tvNumber);

        Number number = numbers.get(position);

        tvID.setText(number.getId()+ ")");
        tvNumber.setText("Push Up Number : " + number.getPushupnumber() + " ( In One Minute )");

        return rowView;
    }
}
