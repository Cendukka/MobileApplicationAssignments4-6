package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class ProductsActivity extends AppCompatActivity {

    ImageButton cartButton;
    EditText inputCellphone, inputLaptop, inputTV, inputTablet;

    public static String resume = "Purchase Resume:\n";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        inputCellphone = (EditText) findViewById(R.id.editCellphone);
        inputLaptop = (EditText) findViewById(R.id.editLaptop);
        inputTV = (EditText) findViewById(R.id.editTV);
        inputTablet = (EditText) findViewById(R.id.editTablet);
        cartButton = (ImageButton)findViewById(R.id.iBtnCart);

        inputCellphone.setText("0");
        inputLaptop.setText("0");
        inputTV.setText("0");
        inputTablet.setText("0");

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(ProductsActivity.this);
                builder.setTitle("Purchase")
                        .setMessage("Are you sure, you want to finish your purchase ?")
                        .setCancelable(false)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if(Integer.parseInt(inputCellphone.getText().toString()) > 0){
                                    resume += "Cellphone: " + inputCellphone.getText().toString() + "\n";
                                }
                                if(Integer.parseInt(inputLaptop.getText().toString()) > 0){
                                    resume += "Laptop: " + inputLaptop.getText().toString() + "\n";
                                }
                                if(Integer.parseInt(inputTV.getText().toString()) > 0){
                                    resume += "TV: " + inputTV.getText().toString() + "\n";
                                }
                                if(Integer.parseInt(inputTablet.getText().toString()) > 0){
                                    resume += "Tablet: " + inputTablet.getText().toString();
                                }

                                Intent intent = new Intent(ProductsActivity.this, InfoActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("No", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ProductsActivity.this,"Selected Option: No",Toast.LENGTH_SHORT).show();
                            }
                        });
                //Creating dialog box
                AlertDialog dialog  = builder.create();
                dialog.show();
            }
        });
    }
}
