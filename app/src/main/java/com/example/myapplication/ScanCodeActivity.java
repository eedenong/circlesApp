package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.zxing.Result;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import java.util.Date;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class ScanCodeActivity extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    DatabaseReference reff;
    User user;
    ZXingScannerView ScannerView;
    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ScannerView = new ZXingScannerView(this);
        setContentView(ScannerView);

    }

    @Override
        public void handleResult (Result result) {

        //SecondActivitymodded.resultTextView.setText(result.getText());
        String currentString = result.getText();
        String[] separated = currentString.split(":");
        user = new User();
        String Username = firebaseAuth.getInstance().getCurrentUser().getUid();
        String date = separated[0];
        String category = separated[1];
        String productName = separated[2]; // this will contain product name
        String price = separated[3];
        String userid;
        if(Username != null) {
             userid = Username;
        }
        else{
            userid = "-";
        }
        reff = FirebaseDatabase.getInstance().getReference();
        user.setDate(date);
        user.setCategory(category);
        user.setProductName(productName);
        user.setPrice(price);
        reff.child(userid).child(category).child(productName).setValue(user);
        Toast.makeText(ScanCodeActivity.this,"Scanned",Toast.LENGTH_LONG).show();
        onBackPressed();

    }

    @Override
    protected void onPause() {
        super.onPause();

        ScannerView.stopCamera();
    }

    @Override
    protected void onResume() {
        super.onResume();

        ScannerView.setResultHandler(this);
        ScannerView.startCamera();
    }
}
