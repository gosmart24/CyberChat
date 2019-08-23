package com.cybertech.cyberchat;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SplashActivity extends AppCompatActivity {

    EditText name_ET, username_ET, phone_ET;
    TinyDB tinyDB;
    UserModel model;
    FirebaseDatabase database;
    private boolean accountRegisted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tinyDB = new TinyDB(this);
        try {
            accountRegisted = tinyDB.getBoolean("isRegisted");
            if (accountRegisted) {
                startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                finish();
            } else {
                // load default user home.
                setContentView(R.layout.activity_splash);
                name_ET = findViewById(R.id.editText_name);
                username_ET = findViewById(R.id.editText2_username);
                phone_ET = findViewById(R.id.editText3_phone);

                tinyDB = new TinyDB(SplashActivity.this);
                model = new UserModel();

                database = FirebaseDatabase.getInstance();
            }
        } catch (Exception e) {

        }

    }

    public void onCreateUser(View view) {
        String name = name_ET.getText().toString();
        String username = username_ET.getText().toString();
        String phone = phone_ET.getText().toString();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(username) || TextUtils.isEmpty(phone)){
            // show error here!
            AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Warning!");
            alertDialog.setMessage("Required fields cannot be empty!");
            alertDialog.setCancelable(false);
            alertDialog.setNegativeButton("Close",null);
            alertDialog.show();
        }else {
            // continue with logic of our code.
            model.setName(name);
            model.setUsername(username);
            model.setPhone(phone);

            DatabaseReference databaseReference = database.getReference("Users");
            final DatabaseReference ref = databaseReference.push();

            model.setKey(ref.getKey());
            String token = (tinyDB.getBoolean("tokenReady")) ? tinyDB.getString("token") : "";
            model.setToken(token);
            ref.setValue(model).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Log.i("REG", e.getMessage());
                    Toast.makeText(SplashActivity.this, "Unable to Register User info " + e.getMessage() , Toast.LENGTH_LONG).show();
                }

            }).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    tinyDB.putBoolean("isRegisted",true);
                    Toast.makeText(SplashActivity.this, " User Registration is successful ", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(getApplicationContext(),HomeActivity.class));
                    finish();
                }
            });

        }
           }
}