package com.example.over.activity04_json_parsing_task2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private void displayMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String strJson;
        String [] employees;

        //Calls the JSON object.
        strJson = "{\"Employee\" :[";
        strJson += "{\"id\":\"01\", \"name\":\"Abdulla\", \"salary\":200000},";
        strJson += "{\"id\":\"02\", \"name\":\"Mohammed\", \"salary\":230000},";
        strJson += "{\"id\":\"03\", \"name\":\"Salem\", \"salary\":100000},";
        strJson += "{\"id\":\"04\", \"name\":\"Khalid\", \"salary\":150000},";
        strJson += "{\"id\":\"05\", \"name\":\"Hamad\", \"salary\":250000}";
        strJson += "]}";

        employees = new String[5];

        try {
            JSONObject jRootObject = new JSONObject(strJson);
            JSONArray jArray = jRootObject.getJSONArray("Employee");
            for (int i = 0; i < jArray.length(); i++)
            {
                // Get the JSONObject from the array at position i
                JSONObject jObject = jArray.getJSONObject(i);
                // Get the id from the jsonObject
                String strId = jObject.getString("id");

                //get the name from the jsonObject
                String name = jObject.getString("name");

                // get the salary from the jsonObject
                String strSalary = jObject.getString("salary");
                double salary = Double.parseDouble(strSalary);

                employees[i] = "id: " + strId + ", Name: " + name + ", Salary: " + salary;
            }
            //Sets the adapter based on the above results.
            //Displaying the contents of the array into the ListView
            ListView listView = findViewById(R.id.list);
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
            listView.setAdapter(adapter);
            /*
            TODO: ADD ON ITEM CLICK LISTENER
            */
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void onSearch(View v)
    {
        Log.i("BUTTON","Search button was clicked");

        String strJson;
        strJson = "{\"Employee\" :[";
        strJson += "{\"id\":\"01\", \"name\":\"Abdulla\", \"salary\":200000},";
        strJson += "{\"id\":\"02\", \"name\":\"Mohammed\", \"salary\":230000},";
        strJson += "{\"id\":\"03\", \"name\":\"Salem\", \"salary\":100000},";
        strJson += "{\"id\":\"04\", \"name\":\"Khalid\", \"salary\":150000},";
        strJson += "{\"id\":\"05\", \"name\":\"Hamad\", \"salary\":250000}";
        strJson += "]}";

        EditText etID = findViewById(R.id.etID);
        String ID = etID.getText().toString();
        Log.i("STRING", "ID is " + ID);

        String [] employees = new String[1];

        try
        {
            JSONObject jRootObject = new JSONObject(strJson);
            JSONArray jArray = jRootObject.getJSONArray("Employee");
            for (int i = 0; i < jArray.length(); i++)
            {
                JSONObject jObject = jArray.getJSONObject(i);

                String strId = jObject.getString("id");
                Log.i("STRING", "strId is " + strId);

                String name = jObject.getString("name");
                String strSalary = jObject.getString("salary");
                double salary = Double.parseDouble(strSalary);

                if (ID.equals(strId)) {
                    Log.i("IF", ID + " is equal to " + strId);
                    employees[0] = "id: " + strId + ", Name: " + name + ", Salary: " + salary;
                    Log.i("STRING", "employees[0] contains: " + employees[0]);
                }
            }
            ListView listView = findViewById(R.id.list);
            Log.i("READ", "ListView has been read");
            ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
            Log.i("DECLARATION", "myAdapter has been declared");
            listView.setAdapter(myAdapter);
            Log.i("SET", "myAdapter has been set");
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
