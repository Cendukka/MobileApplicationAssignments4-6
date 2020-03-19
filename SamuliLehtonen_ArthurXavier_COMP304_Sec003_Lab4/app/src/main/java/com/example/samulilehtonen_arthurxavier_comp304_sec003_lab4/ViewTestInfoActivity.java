package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

public class ViewTestInfoActivity extends AppCompatActivity {

    EditText patientId;
    TextView displayTestInfo;
    ImageButton retrieveTestButton;

    public static MedicalDatabase medicalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_test_info);

        patientId = (EditText) findViewById(R.id.txtTestInfoPatientId);
        displayTestInfo = (TextView) findViewById(R.id.tviewTestInfo);
        retrieveTestButton = (ImageButton) findViewById(R.id.btnTestInfoRetrieve);
        //set textview scrollable
        displayTestInfo.setMovementMethod(new ScrollingMovementMethod());
        //Assign Database
        medicalDatabase = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "MedicalDB").allowMainThreadQueries().build();

    }

    public void retrieveTest(View view){
        String testDisplay = "Patient has no test registered !",
                bph, bpl, flu;

        List<Test> tests = medicalDatabase.medicalAppDao().getTests();

        for(Test test : tests){
            if (test.getPatient_id().equals(patientId.getText().toString())){
                if(test.isBph()){
                    bph = "Yes";
                } else {
                    bph = "No";
                }
                if(test.isBpl()){
                    bpl = "Yes";
                } else {
                    bpl = "No";
                }
                if(test.isFlu()){
                    flu = "Yes";
                } else {
                    flu = "No";
                }
                testDisplay = "Test Name: " + test.getTest_name()
                              + "\nTest ID: " + test.getId()
                              + "\nNurse ID: " + test.getNurse_id()
                              + "\nTemperature: " + test.getTemperature() + " ºC"
                              + "\nSugar Level: " + test.getSugar_level()
                              + "\nBPH: " + bph
                              + "\nBPL: " + bpl
                              + "\nFlu: " + flu + "\n\n";

                displayTestInfo.append("Patient ID: "+ patientId.getText().toString() + "\n" + testDisplay);
            }
        }

    }
}
