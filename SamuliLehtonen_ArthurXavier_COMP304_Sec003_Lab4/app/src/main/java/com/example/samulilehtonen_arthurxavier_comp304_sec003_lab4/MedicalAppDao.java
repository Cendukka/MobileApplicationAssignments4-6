package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab4;
/*
* This is Data access object,
*   which hold functions which activities can use to communicate with database.
 */

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface MedicalAppDao {
    @Insert
    public void addPatient(Patient patient);
    @Update
    public void updatePatient(Patient patient);
    @Insert
    public void addTest(Test test);
    @Insert
    public void addNurse(Nurse nurse);

    @Query("select * from nurse")
    public List<Nurse> getNurses();

    @Query("select * from patient")
    public List<Patient> getPatients();

    @Query("select * from test")
    public List<Test> getTests();

}
