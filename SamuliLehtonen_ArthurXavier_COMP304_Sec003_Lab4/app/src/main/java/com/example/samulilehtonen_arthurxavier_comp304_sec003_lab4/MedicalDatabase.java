package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab4;

import androidx.room.Database;
import androidx.room.RoomDatabase;


@Database(entities = {Patient.class, Test.class, Nurse.class}, version = 1,exportSchema = false)
public abstract class MedicalDatabase extends RoomDatabase {
    public abstract MedicalAppDao medicalAppDao();
}
