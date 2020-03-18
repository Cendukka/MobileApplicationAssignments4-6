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

    //Declarations
    EditText testId, patientId, nurseId, testName, sugarLevel, temperature;
    CheckBox bpl, bph, flu;
    ImageButton registerTestButton;

    boolean bplChecked, bphChecked, fluChecked;

    public static MedicalDatabase medicalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //Initializations
        testId = (EditText) findViewById(R.id.txtTestId);
        patientId = (EditText) findViewById(R.id.txtTestPatientId);
        nurseId = (EditText) findViewById(R.id.txtTestNurseId);
        testName = (EditText) findViewById(R.id.txtTestName);
        sugarLevel = (EditText) findViewById(R.id.txtTestSugarLevel);
        temperature = (EditText) findViewById(R.id.txtTestTemperature);
        bpl = (CheckBox) findViewById(R.id.cboxTestBPL);
        bph = (CheckBox) findViewById(R.id.cboxTestBPH);
        flu = (CheckBox) findViewById(R.id.cboxTestFLU);
        registerTestButton = (ImageButton) findViewById(R.id.btnTestRegister);

        //Assign Database
        medicalDatabase = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "MedicalDB").allowMainThreadQueries().build();
    }

    //Methods to assign whether exams were taken or not
    public void onBplChecked(View view){
        bplChecked = true;
    }
    public void onBphChecked(View view){
        bphChecked = true;
    }
    public void onFluChecked(View view){
        fluChecked = true;
    }

    //Method to register a test
    public void registerTestInfo(View view){
        //Get values of the EditText fields
        String strTestId = testId.getText().toString();
        String strPatientId = patientId.getText().toString();
        String strNurseId = nurseId.getText().toString();
        String strTestName = testName.getText().toString();
        String strSugarLevel = sugarLevel.getText().toString();
        String strTemperature = temperature.getText().toString();

        //Bool for checking if testId is taken
        boolean testIdTaken = false;
        //Check all fields are filled
        if (!strTestId.equals("") && !strPatientId.equals("") && !strNurseId.equals("") && !strTestName.equals("") && !strSugarLevel.equals("") && !strTemperature.equals("")) {
            int tempTestId = parseInt(strTestId);
            //check that test ID is unique
            List<Test> tests = medicalDatabase.medicalAppDao().getTests();
            for (Test test : tests) {
                int usedID = test.getId();
                System.out.println(usedID);
                System.out.println(strTestId);
                if (tempTestId == usedID) {
                    System.out.println(testIdTaken);
                    testIdTaken = true;
                    break;
                }
            }
            System.out.println(testIdTaken);
            //If given test id is unique
            if (!testIdTaken) {
                //create test object
                Test testObj = new Test();

                testObj.setId(tempTestId);
                testObj.setPatient_id(strPatientId);
                testObj.setNurse_id(parseInt(strNurseId));
                testObj.setTest_name(strTestName);
                testObj.setSugar_level(parseInt(strSugarLevel));
                testObj.setTemperature(parseInt(strTemperature));

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
                testId.getText().clear();
                patientId.getText().clear();
                nurseId.getText().clear();
                testName.getText().clear();
                sugarLevel.getText().clear();
                temperature.getText().clear();

            } else {
                Toast.makeText(getApplicationContext(), "Test ID already taken!", Toast.LENGTH_LONG).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_LONG).show();
        }
    }
}
