package com.example.jsonsimpleexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {


    String JSON_STRING = "{\"employee\":{\"name\":\"Joe Mathew\",\"salary\":55000, \"title\":\"Professor\"}}";
    String name;
    int salary;
    String title;
    TextView employeeName, employeeSalary, employeeTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // get the reference of TextView's
        employeeName = (TextView) findViewById(R.id.name);
        employeeSalary = (TextView) findViewById(R.id.salary);
        employeeTitle = (TextView) findViewById(R.id.title);

        try {
            // get JSONObject from JSON file
            JSONObject obj = new JSONObject(JSON_STRING);
            // fetch JSONObject named employee
            JSONObject employee = obj.getJSONObject("employee");
            // get employee name, salary and title
            name = employee.getString("name");
            salary = employee.getInt("salary");
            title = employee.getString("title");
            // set employee name, salary and title in TextView's
            employeeName.setText("Name: " + name);
            employeeSalary.setText("Salary: " + salary);
            employeeTitle.setText("Title: " + title);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
