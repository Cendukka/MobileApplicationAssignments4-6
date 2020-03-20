package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

public class  UpdateInfoActivity extends AppCompatActivity {

    //Declarations
    int updPatient;

    EditText updPatientId, updFirstName, updLastName, updDept, updNurseId, updRoom;
    ImageButton updatePatientButton, retrievePatientButton;
    List<Patient> patients;

    public static MedicalDatabase medicalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_info);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        updPatientId = (EditText) findViewById(R.id.txtUpdtPatientID);
        updFirstName = (EditText) findViewById(R.id.txtUpdtPatientFirstName);
        updLastName = (EditText) findViewById(R.id.txtUpdtPatientLastName);
        updDept = (EditText) findViewById(R.id.txtUpdtPatientDept);
        updNurseId = (EditText) findViewById(R.id.txtUpdtNurseId);
        updRoom = (EditText) findViewById(R.id.txtUpdtPatientRoom);
        retrievePatientButton = (ImageButton) findViewById(R.id.btnGetPatient);
        updatePatientButton = (ImageButton) findViewById(R.id.btnUpdtPatient);

        //Assign Database
        medicalDatabase = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "MedicalDB").allowMainThreadQueries().build();
    }

    //This method will display the information for a given patient ID
    @SuppressLint("SetTextI18n")
    public void retrievePatient(View view){

        //to check if the given ID is assigned to a patient
        boolean patientExists = false;

        List<Patient> patients = medicalDatabase.medicalAppDao().getPatients();

        //Look for a patient in the list of patients
        for(Patient patient : patients){
            //condition to display the info if id is found
            if (patient.getId() == Integer.parseInt(updPatientId.getText().toString())){
                System.out.println("LOL");
                updPatient = patient.getId();

                updFirstName.setText(patient.getFirst_name());
                updLastName.setText(patient.getLast_name());
                updDept.setText(patient.getDepartment());
                updNurseId.setText(Integer.toString(patient.getNurse_id()));
                updRoom.setText(Integer.toString(patient.getRoom()));

                patientExists = true;
                break;
            }
        }

        //If patient does not exist, display the message
        if(!patientExists){
            Toast.makeText(getApplicationContext(), "Patient ID not found!", Toast.LENGTH_LONG).show();
        }
    }

    //Change the patient info
    public void updatePatientInfo(View view){
        Patient patient = new Patient();

        //Assign action to the given ID
        patient.setId(updPatient);

        //Check if all the fields are filled and then update info
        if(!updFirstName.equals("") && !updLastName.equals("") && !updDept.equals("") && !updNurseId.equals("") && !updRoom.equals("")){
            patient.setFirst_name(updFirstName.getText().toString());
            patient.setLast_name(updLastName.getText().toString());
            patient.setDepartment(updDept.getText().toString());
            patient.setNurse_id(Integer.parseInt(updNurseId.getText().toString()));
            patient.setRoom(Integer.parseInt(updRoom.getText().toString()));
            //updates patients information
            medicalDatabase.medicalAppDao().updatePatient(patient);

            //Clear the fields
            updPatientId.getText().clear();
            updFirstName.getText().clear();
            updLastName.getText().clear();
            updDept.getText().clear();
            updNurseId.getText().clear();
            updRoom.getText().clear();

            Toast.makeText(getApplicationContext(), "The Information was updated!", Toast.LENGTH_LONG).show();
        } else {
            //If all the fields are not filled, display the message
            Toast.makeText(getApplicationContext(), "All fields must be filled!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
