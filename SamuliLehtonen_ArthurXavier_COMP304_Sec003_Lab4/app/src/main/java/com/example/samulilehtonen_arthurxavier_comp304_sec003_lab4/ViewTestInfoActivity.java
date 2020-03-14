package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

public class ViewTestInfoActivity extends AppCompatActivity {

    EditText patientId;
    TextView displayTestInfo;
    ImageButton retrieveTestButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);

        patientId = (EditText) findViewById(R.id.testInfoPatientID);
        displayTestInfo = (TextView) findViewById(R.id.testInformation);
        retrieveTestButton = (ImageButton) findViewById(R.id.retrieveTestInfo);

    }
}
