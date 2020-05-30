package com.example.fourthapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SecondShowActivity extends AppCompatActivity {

    Button btnEmail;
    TextView tv1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_show);

        Intent i = getIntent();

        Number number = (Number) i.getSerializableExtra("type");

        btnEmail = findViewById(R.id.btnEmail);
        tv1 = findViewById(R.id.tv1);

        final Number currNumber = (Number) i.getSerializableExtra("number");
        tv1.setText("Push Up Number : " + currNumber.getPushupnumber() + "\n" + " ( In One Minute )");

        btnEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL,
                        new String[]{"jason_lim@rp.edu.sg"});
                email.putExtra(Intent.EXTRA_SUBJECT,
                        "Project 4 : 18045404");
                email.putExtra(Intent.EXTRA_TEXT,"I Just Do " + currNumber.getPushupnumber() + " Push Up In One Minute !!!");
                email.setType("message/rfc822");


                startActivity(Intent.createChooser(email,
                        "Choose an Email client :"));


            }
        });
    }
}
