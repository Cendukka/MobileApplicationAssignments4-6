package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    EditText customerName, customerPhone, customerEmail;
    TextView productsResume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        customerName = (EditText) findViewById(R.id.txtName);
        customerPhone = (EditText) findViewById(R.id.txtPhone);
        customerEmail = (EditText) findViewById(R.id.txtEmail);
        productsResume = (TextView) findViewById(R.id.txtProductResume);

    }
}
