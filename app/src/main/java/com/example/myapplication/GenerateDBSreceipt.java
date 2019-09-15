package com.example.myapplication;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class    GenerateDBSreceipt {
    public static int counter = 1;
    DatabaseReference reff;
    User user;
    private FirebaseAuth firebaseAuth;

    void generate() {
        user = new User();
        String Username = firebaseAuth.getInstance().getCurrentUser().getUid();
        final String userid;
        if (Username != null) {
            userid = Username;
        } else {
            userid = "-";
        }
       reff = FirebaseDatabase.getInstance().getReference();
        user.setDate("15/7/19");
        user.setCategory("transport");
        user.setProductName("Comfort Delgro Fare");
        user.setPrice("$15.20");
        reff.child(userid).child("transport").child("Comfort Delgro Fare").setValue(user);
    }

    void generate2 () {
        user = new User();
        String Username = firebaseAuth.getInstance().getCurrentUser().getUid();
        final String userid;
        if (Username != null) {
            userid = Username;
        } else {
            userid = "-";
        }
        reff = FirebaseDatabase.getInstance().getReference();
        user.setDate("17/7/19");
        user.setCategory("misc");
        user.setProductName("Striped T-Shirt");
        user.setPrice("$10.00");
        reff.child(userid).child("misc").child("Striped T-Shirt").setValue(user);
    }

    void generate3 (){
        user = new User();
        String Username = firebaseAuth.getInstance().getCurrentUser().getUid();
        final String userid;
        if (Username != null) {
            userid = Username;
        } else {
            userid = "-";
        }
        reff = FirebaseDatabase.getInstance().getReference();
        user.setDate("20/7/19");
        user.setCategory("misc");
        user.setProductName("Lipstick");
        user.setPrice("$21.60");
        reff.child(userid).child("misc").child("Lipstick").setValue(user);
    }
    void generate4 (){
        user = new User();
        String Username = firebaseAuth.getInstance().getCurrentUser().getUid();
        final String userid;
        if (Username != null) {
            userid = Username;
        } else {
            userid = "-";
        }
        reff = FirebaseDatabase.getInstance().getReference();
        user.setDate("26/7/19");
        user.setCategory("food");
        user.setProductName("Katong Laksa");
        user.setPrice("$4.60");
        reff.child(userid).child("food").child("Katong Laksa").setValue(user);
    }






}
