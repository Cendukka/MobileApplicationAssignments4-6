package com.example.jsontest;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        String jsonStr = getListData();
        try{
            ArrayList<HashMap<String, String>> userList = new ArrayList<>();
            ListView lv = (ListView) findViewById(R.id.user_list);
            JSONObject jObj = new JSONObject(jsonStr);
            JSONArray jsonArry = jObj.getJSONArray("users");
            for(int i=0;i<jsonArry.length();i++){
                HashMap<String,String> hashMapUser = new HashMap<>();
                JSONObject obj = jsonArry.getJSONObject(i);
                hashMapUser.put("name",obj.getString("name"));
                hashMapUser.put("designation",obj.getString("designation"));
                hashMapUser.put("location",obj.getString("location"));
                userList.add(hashMapUser);
            }
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, userList, R.layout.listrow,
                    new String[]{"name","designation","location"}, new int[]{R.id.name, R.id.designation, R.id.location});
            lv.setAdapter(adapter);
        }
        catch (JSONException ex){
            Log.e("JsonParser Example","unexpected JSON exception", ex);
        }
    }
    private String getListData() {
        String jsonStr = "{ \"users\" :[" +
                "{\"name\":\"Ritesh Kumar\",\"designation\":\"Team Leader\",\"location\":\"Brampton\"}" +
                ",{\"name\":\"Frank Lee\",\"designation\":\"Developer\",\"location\":\"Markham\"}" +
                ",{\"name\":\"Arthur Young\",\"designation\":\"Charted Accountant\",\"location\":\"Toronto\"}] }";
        return jsonStr;
    }
}