package com.example.myapplication;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class ManualAdd extends Activity implements AdapterView.OnItemSelectedListener {
    DatabaseReference reff;
    User user;
    private FirebaseAuth firebaseAuth;
    EditText date;
    EditText price;
    EditText name;
    Button submit;
    String userid;
    private ImageButton btnBack;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_add);
        // Spinner element
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("bills");
        categories.add("food");
        categories.add("transport");
        categories.add("misc");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);

        user = new User();
        String Username = firebaseAuth.getInstance().getCurrentUser().getUid();
        final String userid;
        if(Username != null) {
            userid = Username;
        }
        else{
            userid = "-";
        }


        date = (EditText)findViewById(R.id.etDate);
        name = (EditText)findViewById(R.id.etName);
        price = (EditText)findViewById(R.id.etPrice);
        submit = (Button)findViewById(R.id.buttonSubmit);
        btnBack = (ImageButton)findViewById(R.id.btnBack10);
        reff = FirebaseDatabase.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.getText().toString().isEmpty() || name.getText().toString().isEmpty() || price.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please fill in all sections", Toast.LENGTH_SHORT).show();

                }
                else {
                    user.setDate(date.getText().toString().trim());
                    user.setCategory(spinner.getSelectedItem().toString().trim());
                    user.setProductName(name.getText().toString().trim());
                    user.setPrice("$"+price.getText().toString().trim());
                    reff.child(userid).child(spinner.getSelectedItem().toString().trim()).child(name.getText().toString().trim()).setValue(user);
                    Toast.makeText(getApplicationContext(),"Manual entry successful!", Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ManualAdd.this, SecondActivitymodded.class));
            }
        });


    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        String item = parent.getItemAtPosition(position).toString();
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(18);
        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        //
    }
}
