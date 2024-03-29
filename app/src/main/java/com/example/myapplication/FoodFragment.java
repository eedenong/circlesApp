package com.example.myapplication;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class FoodFragment extends Fragment {

    private ImageButton btnBack;
    private TextView info;
    private TextView billsExpenditureNumber;
    private TextView billsBudgetNumber;
    private TextView resultTv;
    private FirebaseAuth firebaseAuth;
    public static float foodtotal;
    DatabaseReference ref;
    FirebaseDatabase database;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String> adapter;
    User user;
    float foodExpend ;
    public static float foodBudgetDollar = 50;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        btnBack = (ImageButton) view.findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().onBackPressed();
            }
        });


        billsBudgetNumber = (TextView)view.findViewById(R.id.tvBillBudgetNumber);
        billsExpenditureNumber = (TextView)view.findViewById(R.id.tvBillExpenditureNumber);
        resultTv = (TextView)view.findViewById(R.id.tvAnalysis);
        user = new User();
        listView = view.findViewById(R.id.listView);
        database = FirebaseDatabase.getInstance();
        ref = (database).getReference(firebaseAuth.getInstance().getCurrentUser().getUid()).child("food");
        list = new ArrayList<>();
        adapter= new ArrayAdapter<String>(getContext(),R.layout.product_info,R.id.productinfo,list);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot ds: dataSnapshot.getChildren()){

                    user = ds.getValue(User.class);
                    foodExpend += Float.parseFloat(user.getPrice().substring(1));
                    list.add("Item: "+user.getProductName() + "   Price: "+user.getPrice() + "   Date: " + user.getDate());
                }
                listView.setAdapter(adapter);
                String BE = "$" + foodExpend + "";
                billsExpenditureNumber.setText(BE);
                float billsBudget = foodBudgetDollar;
                String BB ="$" + billsBudget + "";
                billsBudgetNumber.setText(BB);
                String result = "";
                DecimalFormat df = new DecimalFormat("##.##");
                df.setRoundingMode(RoundingMode.DOWN);
                if (foodExpend < billsBudget) {
                    result = "You still have "  + df.format((1 - foodExpend/billsBudget)*100 )+ "% of your budget to spend!";
                } else if (foodExpend > billsBudget){
                    result = "You have exceeded your budget by " + df.format(((foodExpend/billsBudget)-1) * 100) + "%!";
                } else {
                    result = "You have used up exactly all your budget!";
                }
                foodtotal = foodExpend;
                resultTv.setText(result);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_food, container, false);
    }

}
