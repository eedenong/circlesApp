package com.example.myapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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

public class manualAddition extends Fragment implements AdapterView.OnItemSelectedListener  {
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
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final Spinner spinner = (Spinner) view.findViewById(R.id.spinner);

        // Spinner click listener
        //spinner.setOnItemSelectedListener(this);

        // Spinner Drop down elements
        List<String> categories = new ArrayList<String>();
        categories.add("bills");
        categories.add("food");
        categories.add("transport");
        categories.add("misc");

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item, categories);

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


        date = (EditText)view.findViewById(R.id.etDate);
        name = (EditText)view.findViewById(R.id.etName);
        price = (EditText)view.findViewById(R.id.etPrice);
        submit = (Button)view.findViewById(R.id.buttonSubmit);
        reff = FirebaseDatabase.getInstance().getReference();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (date.getText().toString().isEmpty() || name.getText().toString().isEmpty() || price.getText().toString().isEmpty()) {
                    Toast.makeText(getActivity(),"Please fill in all sections", Toast.LENGTH_SHORT).show();

                }
                else {
                    user.setDate(date.getText().toString().trim());
                    user.setCategory(spinner.getSelectedItem().toString().trim());
                    user.setProductName(name.getText().toString().trim());
                    user.setPrice("$"+price.getText().toString().trim());
                    reff.child(userid).child(spinner.getSelectedItem().toString().trim()).child(name.getText().toString().trim()).setValue(user);
                    Toast.makeText(getActivity(),"Manual entry successful!", Toast.LENGTH_SHORT).show();

                }
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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_manual_addition, container, false);
    }
}
