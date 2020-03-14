package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab4;

/*
* Created an entity of Test
*
* */

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Test {



    @PrimaryKey
    @ColumnInfo(name = "test_id")
    private int id;
    private String patient_id;
    private String nurse_id;
    private String test_name;
    private int sugar_level;
    private boolean bpl;
    private boolean bph;
    private boolean flu;
    private int temperature;

    //Getters and setters

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public String getPatient_id() {
        return patient_id;
    }
    public void setPatient_id(String patient_id) {
        this.patient_id = patient_id;
    }

    public String getNurse_id() {
        return nurse_id;
    }
    public void setNurse_id(String nurse_id) {
        this.nurse_id = nurse_id;
    }

    public String getTest_name() {
        return test_name;
    }
    public void setTest_name(String test_name) {
        this.test_name = test_name;
    }

    public int getSugar_level() {
        return sugar_level;
    }
    public void setSugar_level(int sugar_level) {
        this.sugar_level = sugar_level;
    }

    public boolean isBpl() {
        return bpl;
    }
    public void setBpl(boolean bpl) {
        this.bpl = bpl;
    }

    public boolean isBph() {
        return bph;
    }
    public void setBph(boolean bph) {
        this.bph = bph;
    }

    public boolean isFlu() {
        return flu;
    }
    public void setFlu(boolean flu) {
        this.flu = flu;
    }

    public int getTemperature() {
        return temperature;
    }
    public void setTemperature(int temperature) {
        this.temperature = temperature;
    }
}

