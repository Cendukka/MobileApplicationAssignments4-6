package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab4;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.List;

import static java.lang.Integer.parseInt;

public class LoginActivity extends AppCompatActivity {

    EditText nurseIdLogin, nursePasswordLogin;
    ImageButton loginButton;
    EditText nurseIdRegister, nursePasswordRegister, nurseFirstName, nurseLastName, nurseDepartment;
    ImageButton registerButton;

    //Shared preference variables for ID and Password
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final int NURSE_ID = 0;
    public static final String NURSE_PASSWORD = "nursePassword";

    //Loaded shared preference variables
    private int prefVarNurseID = 0;
    private String prefVarNursePassword = "";
    //reference to the database
    public static MedicalDatabase medicalDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //initialize views login
        nurseIdLogin = (EditText) findViewById(R.id.txtLoginNurseId);
        nursePasswordLogin = (EditText) findViewById(R.id.txtLoginPassword);
        loginButton = (ImageButton) findViewById(R.id.btnLogin);

        //initialize views register
        nurseIdRegister = (EditText) findViewById(R.id.txtRegisterNurseId);
        nursePasswordRegister = (EditText) findViewById(R.id.txtRegisterNursePassword);
        nurseFirstName = (EditText) findViewById(R.id.txtRegisterFirstName);
        nurseLastName = (EditText) findViewById(R.id.txtRegisterLastName);
        nurseDepartment = (EditText) findViewById(R.id.txtRegisterDept);
        registerButton = (ImageButton) findViewById(R.id.btnRegister);

        //Assign Database
        medicalDatabase = Room.databaseBuilder(getApplicationContext(), MedicalDatabase.class, "MedicalDB").allowMainThreadQueries().build();

        //Load preferenced variables and assign id to the login
        getPreferenceData();
        nurseIdLogin.setText(Integer.toString(prefVarNurseID));
    }

    @Override
    protected void onStart() {
        super.onStart();
        //Load preferenced variables and assign id to the login
        getPreferenceData();
        nurseIdLogin.setText(Integer.toString(prefVarNurseID));
    }

    //create new use
    public void createNewNurse(View view) {

        //Get values of the EditText fields
        String nurseIdString = nurseIdRegister.getText().toString();
        String nursePassword = nursePasswordRegister.getText().toString();
        String nurseFirstName = this.nurseFirstName.getText().toString();
        String nurseLastName = this.nurseLastName.getText().toString();
        String nurseDepartment = this.nurseDepartment.getText().toString();


        //Bool for checking if nurseid is taken
        boolean nurseIdTaken = false;
        //Check all fields are filled
        if (!nurseIdString.equals("") && !nursePassword.equals("") && !nurseFirstName.equals("") && !nurseLastName.equals("") && !nurseDepartment.equals("")) {
            int nurseId = parseInt(nurseIdString);
            //check that nurse ID is unique
            List<Nurse> nurses = medicalDatabase.medicalAppDao().getNurses();
            for (Nurse nrs : nurses) {
                int usedID = nrs.getId();
                if (nurseId == usedID) {
                    System.out.println(nurseIdTaken);
                    nurseIdTaken = true;
                    break;
                }
            }
            //If given nurse id is unique
            if (!nurseIdTaken) {
                //create nurse object
                Nurse nurse = new Nurse();
                nurse.setId(nurseId);
                nurse.setPassword(nursePassword);
                nurse.setFirst_name(nurseFirstName);
                nurse.setLast_name(nurseLastName);
                nurse.setDepartment(nurseDepartment);
                //push it in the database
                medicalDatabase.medicalAppDao().addNurse(nurse);
                Toast.makeText(getApplicationContext(), "User Added succesfully", Toast.LENGTH_LONG).show();

                //clear the register textFields
                nurseIdRegister.getText().clear();
                nursePasswordRegister.getText().clear();
                this.nurseFirstName.getText().clear();
                this.nurseLastName.getText().clear();
                this.nurseDepartment.getText().clear();
            } else {
                Toast.makeText(getApplicationContext(), "NurseID already taken!", Toast.LENGTH_LONG).show();
                nurseIdRegister.getText().clear();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_LONG).show();
        }

    }
    //this method w
    public void nurseLogin(View view) {
        //Get values of the EditText fields
        String nurseIdString = nurseIdLogin.getText().toString();
        String nursePassword = nursePasswordLogin.getText().toString();

        //Bool for checking if nurseid matches
        boolean nurseIdMatch = false;
        //Check all fields are filled
        if (!nurseIdString.equals("") && !nursePassword.equals("")) {
            int nurseId = parseInt(nurseIdString);
            //check that nurse ID is unique
            List<Nurse> nurses = medicalDatabase.medicalAppDao().getNurses();
            for (Nurse nrs : nurses) {
                int usedID = nrs.getId();
                String usedPass = nrs.getPassword();
                if (nurseId == usedID && nursePassword.equals(usedPass)) {
                    nurseIdMatch = true;
                    break;
                }
            }

            //If given nurse id and password match
            if (nurseIdMatch) {
                //Log in succesfully and save variables
                savePreferenceData(nurseId,nursePassword);

                Toast.makeText(getApplicationContext(), "Logged in succesfully", Toast.LENGTH_LONG).show();
                //clear the register textFields
                nurseIdLogin.getText().clear();
                nursePasswordLogin.getText().clear();

                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
            } else {
                Toast.makeText(getApplicationContext(), "Given ID and password doesn't match", Toast.LENGTH_LONG).show();
                nurseIdLogin.getText().clear();
                nursePasswordLogin.getText().clear();
            }

        } else {
            Toast.makeText(getApplicationContext(), "Fill all fields!", Toast.LENGTH_LONG).show();
        }
    }
    //this method will save the data,
    // that needs to be shared preference variable during successful login
    public void savePreferenceData(int saveNurseId, String saveNursePassword){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putInt(String.valueOf(NURSE_ID),saveNurseId);
        editor.putString(NURSE_PASSWORD,saveNursePassword);
        editor.apply();
    }
    //this method will load the shared preferenced values during onCreate() method
    public void getPreferenceData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        prefVarNurseID = sharedPreferences.getInt(String.valueOf(NURSE_ID),0);
        prefVarNursePassword = sharedPreferences.getString(NURSE_PASSWORD, "");
    }

}
