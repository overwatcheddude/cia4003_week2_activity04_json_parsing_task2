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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resetList();
    }

    private void displayMessage(String msg)
    {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void onResetList(View v)
    {
        resetList();

        displayMessage("The list has been reset");
    }

    private void resetList()
    {
        String strJson = employeeJSON();
        String [] employees = new String[5];

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

                employees[i] = "id: " + strId + ", Name: " + name + ", Salary: " + strSalary;
            }
            //Sets the adapter based on the above results.
            //Displaying the contents of the array into the ListView
            setListView(employees);
            /*
            TODO: ADD ON ITEM CLICK LISTENER
            */
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private String employeeJSON()
    {
        String strJson;
        strJson = "{\"Employee\" :[";
        strJson += "{\"id\":\"01\", \"name\":\"Abdulla\", \"salary\":200000},";
        strJson += "{\"id\":\"02\", \"name\":\"Mohammed\", \"salary\":230000},";
        strJson += "{\"id\":\"03\", \"name\":\"Salem\", \"salary\":100000},";
        strJson += "{\"id\":\"04\", \"name\":\"Khalid\", \"salary\":150000},";
        strJson += "{\"id\":\"05\", \"name\":\"Hamad\", \"salary\":250000}";
        strJson += "]}";

        return  strJson;
    }

    private void setListView(String[] myStringArray)
    {
        //The listview displays the results.
        ListView listView = findViewById(R.id.list);
        Log.i("READ", "ListView has been read");
        ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, myStringArray);
        Log.i("DECLARATION", "myAdapter has been declared");
        listView.setAdapter(myAdapter);
        Log.i("SET", "myAdapter has been set");
    }

    public void onIdSearch(View v)
    {
        String strJson = employeeJSON();

        EditText etID = findViewById(R.id.etID);
        String ID = etID.getText().toString();

        String [] employees = new String[1];

        try
        {
            JSONObject jRootObject = new JSONObject(strJson);
            JSONArray jArray = jRootObject.getJSONArray("Employee");

            for (int i = 0; i < jArray.length(); i++)
            {
                JSONObject jObject = jArray.getJSONObject(i);

                String strId = jObject.getString("id");
                String jName = jObject.getString("name");
                String strSalary = jObject.getString("salary");

                if (ID.equals(strId)) {
                    employees[0] = "id: " + strId + ", Name: " + jName + ", Salary: " + strSalary;
                }
            }
            if (employees[0] != null)
            {
                setListView(employees);
            }
            else
            {
                displayMessage("No results were found for id: " + ID);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void onNameSearch(View v)
    {
        String strJson = employeeJSON();

        EditText etName = findViewById(R.id.etName);
        String name = etName.getText().toString();

        String [] employees = new String[1];

        try
        {
            JSONObject jRootObject = new JSONObject(strJson);
            JSONArray jArray = jRootObject.getJSONArray("Employee");

            for (int i = 0; i < jArray.length(); i++)
            {
                JSONObject jObject = jArray.getJSONObject(i);

                String strId = jObject.getString("id");
                String jName = jObject.getString("name");
                String strSalary = jObject.getString("salary");

                if (jName.contains(name)) {
                    employees[0] = "id: " + strId + ", Name: " + jName + ", Salary: " + strSalary;
                }
            }
            if (employees[0] != null)
            {
                setListView(employees);
            }
            else
            {
                displayMessage("No results were found for name: " + name);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }

    public void onSalarySearch(View v)
    {
        String strJson = employeeJSON();

        //Reads the salary input
        EditText etMinSalary = findViewById(R.id.etMinSalary);
        EditText etMaxSalary = findViewById(R.id.etMaxSalary);
        String strMinSalary = etMinSalary.getText().toString();
        String strMaxSalary = etMaxSalary.getText().toString();

        //If min salary is filled, but max salary is not filled (and vice versa), then error msg will be displayed.
        if ((strMinSalary.matches("") && !strMaxSalary.matches("")) || (!strMinSalary.matches("") && strMaxSalary.matches("")))
        {
            displayMessage("Please fill in both min and max salary.");
            return;
        }

        //Converts salaries from string to int
        Double minSalary = Double.parseDouble(strMinSalary);
        Double maxSalary = Double.parseDouble(strMaxSalary);

        ArrayList<String> employees = new ArrayList<>();

        try
        {
            JSONObject jRootObject = new JSONObject(strJson);
            JSONArray jArray = jRootObject.getJSONArray("Employee");

            int j = 0;
            for (int i = 0; i < jArray.length(); i++)
            {
                JSONObject jObject = jArray.getJSONObject(i);

                String strId = jObject.getString("id");
                String jName = jObject.getString("name");
                String strSalary = jObject.getString("salary");
                Double salary = Double.parseDouble(strSalary);

                if (salary >= minSalary && salary <= maxSalary) {
                    employees.add("id: " + strId + ", Name: " + jName + ", Salary: " + strSalary);
                    Log.i("ARRAY", "employee[j] contains " + "id: " + strId + ", Name: " + jName + ", Salary: " + strSalary);
                    j++;
                }
            }
            if (!employees.isEmpty())
            {
                //The listview displays the results.
                ListView listView = findViewById(R.id.list);
                ArrayAdapter<String> myAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, employees);
                listView.setAdapter(myAdapter);
            }
            else
            {
                displayMessage("No results were found for min: " + strMinSalary + " and max: " + strMaxSalary);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
}
/*
//This is the debug version of onIdSearch.
public void onIdSearch(View v)
    {
        Log.i("BUTTON","ID search button was clicked");

        //JSON object
        String strJson = employeeJSON();

        //Reads the ID input
        EditText etID = findViewById(R.id.etID);
        String ID = etID.getText().toString();
        Log.i("STRING", "ID is " + ID);

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
                setListView(employees);
            }
            else
            {
                displayMessage("No results were found for id: " + ID);
            }
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
    }
 */