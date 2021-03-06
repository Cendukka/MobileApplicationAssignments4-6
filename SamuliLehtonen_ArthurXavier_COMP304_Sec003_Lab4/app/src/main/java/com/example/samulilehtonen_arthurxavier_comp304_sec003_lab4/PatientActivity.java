package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import static java.lang.Integer.parseInt;

public class PatientActivity extends AppCompatActivity {

    //Declarations
    EditText patientId, firstName, lastName, department, nurseId, room;
    ImageButton registerPatientButton;

    public static MedicalDatabase medicalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient);

        assert getSupportActionBar() != null;   //null check
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        patientId = (EditText) findViewById(R.id.txtPatientId);
        firstName = (EditText) findViewById(R.id.txtPatientFirstName);
        lastName = (EditText) findViewById(R.id.txtPatientLastName);
        department = (EditText) findViewById(R.id.txtPatientDept);
        nurseId = (EditText) findViewById(R.id.txtPatientNurseId);
        room = (EditText) findViewById(R.id.txtPatientRoom);
        registerPatientButton = (ImageButton) findViewById(R.id.btnPatientRegister);

        //Assign Database
        medicalDatabase = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "MedicalDB").allowMainThreadQueries().build();
    }

    //Method to register a patient
    public void registerPatient(View view){
        //Get values of the EditText fields
        String strPatientId = patientId.getText().toString();
        String strFirstName = firstName.getText().toString();
        String strLastName = lastName.getText().toString();
        String strDept = department.getText().toString();
        String strNurseId = nurseId.getText().toString();
        String strRoom = room.getText().toString();

        //Bool for checking if patientId is taken
        boolean patientIdTaken = false;
        boolean nurseIdValid = false;

        //Check all fields are filled
        if (!strPatientId.equals("") && !strFirstName.equals("") && !strLastName.equals("") && !strDept.equals("") && !strNurseId.equals("") && !strRoom.equals("")) {
            int tempPatientId = parseInt(strPatientId);
            int tempNurseId = parseInt(strNurseId);

            List<Patient> patients = medicalDatabase.medicalAppDao().getPatients();
            List<Nurse> nurses = medicalDatabase.medicalAppDao().getNurses();
            //check that patient ID is unique
            for (Patient pat : patients) {
                int usedPatientID = pat.getId();
                if (tempPatientId == usedPatientID) {
                    patientIdTaken = true;
                    break;
                }
            }
            //check that the nurse id is valid
            for (Nurse nur : nurses) {
                int usedNurseID = nur.getId();
                if (tempNurseId == usedNurseID) {
                    nurseIdValid = true;
                    break;
                }
            }
            //If given patient id is unique and nurseid is valid
            if (!patientIdTaken) {
                if (nurseIdValid) {
                    //create patient object
                    Patient patientObj = new Patient();

                    patientObj.setId(tempPatientId);
                    patientObj.setFirst_name(strFirstName);
                    patientObj.setLast_name(strLastName);
                    patientObj.setDepartment(strDept);
                    patientObj.setNurse_id(parseInt(strNurseId));
                    patientObj.setRoom(parseInt(strRoom));

                    //push it in the database
                    medicalDatabase.medicalAppDao().addPatient(patientObj);
                    Toast.makeText(getApplicationContext(), "Patient Added successfully", Toast.LENGTH_LONG).show();

                    //clear the register textFields
                    patientId.getText().clear();
                    firstName.getText().clear();
                    lastName.getText().clear();
                    department.getText().clear();
                    nurseId.getText().clear();
                    room.getText().clear();

                } else {
                    Toast.makeText(getApplicationContext(), "Nurse ID is not valid!", Toast.LENGTH_LONG).show();
                    nurseId.getText().clear();
                }
            } else {
                Toast.makeText(getApplicationContext(), "Patient ID already taken!", Toast.LENGTH_LONG).show();
                patientId.getText().clear();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_LONG).show();
        }
    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
