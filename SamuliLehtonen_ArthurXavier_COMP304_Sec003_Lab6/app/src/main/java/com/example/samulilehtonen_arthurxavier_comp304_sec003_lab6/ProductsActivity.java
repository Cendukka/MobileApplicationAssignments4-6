package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProductsActivity extends AppCompatActivity {

    ImageButton cellphoneButton, laptopButton, tvButton, tabletButton, cartButton;
    TextView amountProducts;

    //variables will store amount of each product
    int amountCellphone, amountLaptop, amountTV, amountTablet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);

        cellphoneButton = (ImageButton)findViewById(R.id.iBtnCellphone);
        laptopButton = (ImageButton)findViewById(R.id.iBtnCellphone);
        tvButton = (ImageButton)findViewById(R.id.iBtnCellphone);
        tabletButton = (ImageButton)findViewById(R.id.iBtnCellphone);
        cartButton = (ImageButton)findViewById(R.id.iBtnCart);
        amountProducts = (TextView) findViewById(R.id.txtProducts);

        cartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

        cellphoneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProductsActivity.this, InfoActivity.class);
                startActivity(intent);
            }
        });

    }

    public void buyCellphone(View view){

    }

}
