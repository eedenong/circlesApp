package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class ExpenditureAllocation extends AppCompatActivity {
    EditText foodInput;
    EditText transportInput;
    EditText billsInput;
    EditText miscInput;
    Button updateBudget;
    private ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expenditure_allocation);
        foodInput = (EditText) findViewById(R.id.foodInput);
        foodInput.setHint(FoodFragment.foodBudgetDollar + "");
        transportInput = (EditText) findViewById(R.id.transportInput);
        transportInput.setHint(TransportFragment.transportBudgetDollar + "");
        billsInput = (EditText) findViewById(R.id.billsInput);
        billsInput.setHint(BillFragment.billsBudgetDollar + "");
        miscInput = (EditText) findViewById(R.id.miscInput);
        miscInput.setHint(MiscFragment.miscBudgetDollar + "");

        updateBudget = (Button)findViewById(R.id.updateBudget);
        updateBudget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (foodInput.getText().toString().isEmpty() || transportInput.getText().toString().isEmpty() || billsInput.getText().toString().isEmpty() || miscInput.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(),"Please fill in all sections", Toast.LENGTH_SHORT).show();
                } else {
                    FoodFragment.foodBudgetDollar = Float.parseFloat(foodInput.getText().toString());
                    TransportFragment.transportBudgetDollar = Float.parseFloat(transportInput.getText().toString());
                    BillFragment.billsBudgetDollar = Float.parseFloat(billsInput.getText().toString());
                    MiscFragment.miscBudgetDollar = Float.parseFloat(miscInput.getText().toString());

                    Toast.makeText(getApplicationContext(),"Budget update successful",Toast.LENGTH_SHORT).show();

                }
            }
        });

        btnBack = (ImageButton) findViewById(R.id.btnBack2);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ExpenditureAllocation.this, SecondActivitymodded.class));
            }
        });

    }
    }
