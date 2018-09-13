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

    private void displayMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void onSearch(View v)
    {
        Log.i("BUTTON","Search button was clicked");

        //JSON object
        String strJson;
        strJson = "{\"Employee\" :[";
        strJson += "{\"id\":\"01\", \"name\":\"Abdulla\", \"salary\":200000},";
        strJson += "{\"id\":\"02\", \"name\":\"Mohammed\", \"salary\":230000},";
        strJson += "{\"id\":\"03\", \"name\":\"Salem\", \"salary\":100000},";
        strJson += "{\"id\":\"04\", \"name\":\"Khalid\", \"salary\":150000},";
        strJson += "{\"id\":\"05\", \"name\":\"Hamad\", \"salary\":250000}";
        strJson += "]}";

        //Reads the ID input
        EditText etID = findViewById(R.id.etID);
        String ID = etID.getText().toString();
        Log.i("STRING", "ID is " + ID);

        //Reads the name input
        EditText etName = findViewById(R.id.etName);
        String name = etName.getText().toString();
        Log.i("STRING", "Name is " + name);

        //Reads the salary input
        EditText etMinSalary = findViewById(R.id.etMinSalary);
        EditText etMaxSalary = findViewById(R.id.etMaxSalary);
        String minSalary = etMinSalary.getText().toString();
        String maxSalary = etMaxSalary.getText().toString();

        //If min salary is filled, but max salary is not filled (and vice versa), then error msg will be displayed.
        if ((minSalary.matches("") && !maxSalary.matches("")) || (!minSalary.matches("") && maxSalary.matches("")))
        {
            displayMessage("Please fill in both min and max salary.");
            return;
        }

        //A string array.
        String [] employees = new String[1];

        try
        {
            //Reads the JSON array.
            JSONObject jRootObject = new JSONObject(strJson);
            JSONArray jArray = jRootObject.getJSONArray("Employee");

            for (int i = 0; i < jArray.length(); i++)
            {
                //Gets the JSON object at index position.
                JSONObject jObject = jArray.getJSONObject(i);

                //Gets the ID
                String strId = jObject.getString("id");
                Log.i("STRING", "strId is " + strId);

                //Gets the name.
                String jName = jObject.getString("name");
                Log.i("STRING", "jName is " + jName);

                //Gets the salary
                String strSalary = jObject.getString("salary");
                Log.i("STRING", "salary is " + strSalary);

                if (ID.equals(strId)) {
                    Log.i("IF", ID + " is equal to " + strId);
                    employees[0] = "id: " + strId + ", Name: " + jName + ", Salary: " + strSalary;
                }
            }
            //Check if the array contains a value or not.
            Log.i("STRING", "employees[0] contains: " + employees[0]);
            Log.i("ARRAY", "Employee contains " + employees.length + " value(s)");
            if (employees[0] != null)
            {
                //The listview displays the results.
                ListView listView = findViewById(R.id.list);
                Log.i("READ", "ListView has been read");
                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
                Log.i("DECLARATION", "myAdapter has been declared");
                listView.setAdapter(myAdapter);
                Log.i("SET", "myAdapter has been set");
            }
            else
            {
                displayMessage("No results were found.");
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
