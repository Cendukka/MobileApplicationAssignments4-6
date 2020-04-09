package com.example.samulilehtonen_arthurxavier_comp304_sec003_lab6;

import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class InfoActivity extends AppCompatActivity {

    EditText customerName, customerPhone, customerEmail;
    TextView productsResume;
    ImageButton submit;
    IntentFilter intentFilter;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        customerName = (EditText) findViewById(R.id.editName);
        customerPhone = (EditText) findViewById(R.id.editPhone);
        customerEmail = (EditText) findViewById(R.id.editEmail);
        productsResume = (TextView) findViewById(R.id.txtProductResume);
        submit = (ImageButton) findViewById(R.id.btnFinish);

        productsResume.setText(ProductsActivity.resume);

        context = getApplicationContext();
        intentFilter = new IntentFilter();
        intentFilter.addAction("SMS_RECEIVED_ACTION");

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String custName = customerName.getText().toString();
                String cutPhone = customerPhone.getText().toString();
                String custEmail = customerEmail.getText().toString();

                if(!custName.equals("") && !cutPhone.equals("")){
                    String custNameEdit = custName.substring(0, 1).toUpperCase() + custName.substring(1).toLowerCase();
                    String submittedSMS = "Thank you for your purchase, "+custNameEdit+" ! We will inform you when we have sent your ordered items!\n" + ProductsActivity.resume;
                    //String submitedSMS = custName;
                    String submittedEMAIL = "Thank you for your purchase, " + custNameEdit +" ! We will inform you when we have sent your ordered items!";

                    try{
                        sendMsg(submittedSMS, "+15555215554");

                        Intent it = new Intent(Intent.ACTION_SEND);
                        it.putExtra(Intent.EXTRA_EMAIL, new String[]{customerEmail.getText().toString()});
                        it.putExtra(Intent.EXTRA_SUBJECT,"SA Store Purchase!");
                        it.putExtra(Intent.EXTRA_TEXT,submittedEMAIL + "\n\n" + ProductsActivity.resume);
                        it.setType("message/rfc822");
                        startActivity(Intent.createChooser(it,"Choose Mail App"));

                    }catch (Exception e){
                        Toast.makeText(context, "Something went wrong while sending SMS. Check permissions and Try again.", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(context, "Fill all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    void sendMsg(String smsMessage, String smsNumber){
        PendingIntent sentPI = PendingIntent.getBroadcast(this,0, new Intent("Sent"), 0);
        PendingIntent deliveredPI = PendingIntent.getBroadcast(this,0, new Intent("Delivered"), 0);

        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(smsNumber,null, smsMessage, sentPI, deliveredPI);
    }

}
