package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import static java.lang.Integer.parseInt;

public class TestActivity extends AppCompatActivity {

    EditText testId, patientId, nurseId, testName, sugarLevel, temperature;
    CheckBox bpl, bph, flu;
    ImageButton registerTestButton;

    boolean bplChecked, bphChecked, fluChecked;

    public static MedicalDatabase medicalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        testId = (EditText) findViewById(R.id.testId);
        patientId = (EditText) findViewById(R.id.testPatientId);
        nurseId = (EditText) findViewById(R.id.testNurseId);
        testName = (EditText) findViewById(R.id.testName);
        sugarLevel = (EditText) findViewById(R.id.testSugarLevel);
        temperature = (EditText) findViewById(R.id.testTemperature);
        registerTestButton = (ImageButton) findViewById(R.id.registerTestInfo);
        bpl = (CheckBox) findViewById(R.id.testBPL);
        bph = (CheckBox) findViewById(R.id.testBPH);
        flu = (CheckBox) findViewById(R.id.testFLU);

        //Assign Database
        medicalDatabase = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "MedicalDB").allowMainThreadQueries().build();
    }

    public void onBplChecked(View view){
        bplChecked = true;
    }
    public void onBphChecked(View view){
        bphChecked = true;
    }
    public void onFluChecked(View view){
        fluChecked = true;
    }

    public void enterTestInformation(View view){
        //Get values of the EditText fields
        String testId = this.testId.getText().toString();
        String patientId = this.patientId.getText().toString();
        String nurseId = this.nurseId.getText().toString();
        String testName = this.testName.getText().toString();
        String sugarLevel = this.sugarLevel.getText().toString();
        String temperature = this.temperature.getText().toString();

        //Bool for checking if testId is taken
        boolean testIdTaken = false;
        //Check all fields are filled
        if (!testId.equals("") && !patientId.equals("") && !nurseId.equals("") && !testName.equals("") && !sugarLevel.equals("") && !temperature.equals("")) {
            int tempTestId = parseInt(testId);
            //check that test ID is unique
            List<Test> tests = medicalDatabase.medicalAppDao().getTests();
            for (Test test : tests) {
                int usedID = test.getId();
                System.out.println(usedID);
                System.out.println(nurseId);
                if (tempTestId == usedID) {
                    System.out.println(testIdTaken);
                    testIdTaken = true;
                    break;
                }
            }
            System.out.println(testIdTaken);
            //If given nurse id is unique
            if (!testIdTaken) {
                //create nurse object
                Test testObj = new Test();
                testObj.setId(tempTestId);
                testObj.setPatient_id(patientId);
                testObj.setNurse_id(nurseId);
                testObj.setTest_name(testName);
                testObj.setSugar_level(parseInt(sugarLevel));
                testObj.setTemperature(parseInt(temperature));

                if(bplChecked){
                    testObj.setBpl(true);
                }
                if(bphChecked){
                    testObj.setBph(true);
                }
                if(fluChecked){
                    testObj.setFlu(true);
                }

                //push it in the database
                medicalDatabase.medicalAppDao().addTest(testObj);
                Toast.makeText(getApplicationContext(), "Test Added successfully", Toast.LENGTH_LONG).show();

                //clear the register textFields
                this.testId.getText().clear();
                this.patientId.getText().clear();
                this.nurseId.getText().clear();
                this.testName.getText().clear();
                this.sugarLevel.getText().clear();
                this.temperature.getText().clear();

            } else {
                Toast.makeText(getApplicationContext(), "NurseID already taken!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_LONG).show();
        }
    }
}
