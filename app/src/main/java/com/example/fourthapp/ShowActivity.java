package com.example.fourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowActivity extends AppCompatActivity {

    ListView lv;
    ArrayAdapter aa;
    ArrayList<Number> al;
    Button btnBack;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        btnBack = findViewById(R.id.btnBack);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShowActivity.this, MainActivity.class);
                startActivity(i);
            }
        });

        lv = findViewById(R.id.lv);

        DBHelper dbh = new DBHelper(ShowActivity.this);
        al = dbh.getAllNumber();
        dbh.close();

        aa = new NumberArrayAdapter(this, R.layout.row, al);
        lv.setAdapter(aa);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Number selectedNumber= al.get(position);

                Intent intent = new Intent(ShowActivity.this, SecondShowActivity.class);
                Number currNumber = new Number(selectedNumber.getId(), selectedNumber.getPushupnumber());
                intent.putExtra("number", currNumber);
                startActivity(intent);
            }
        });
    }


}
