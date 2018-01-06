package com.example.loylogic.hackathon;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;

import org.json.JSONArray;

public class UIDSignUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uidsign_up);
        getSupportActionBar().hide();
        findViewById(R.id.submitCardNumber).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LayoutInflater li = LayoutInflater.from(UIDSignUpActivity.this);
                View promptsView = li.inflate(R.layout.otp_validation, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        UIDSignUpActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);

                final EditText userInput = (EditText) promptsView
                        .findViewById(R.id.otpNumber);

                // set dialog message
                alertDialogBuilder
                        .setCancelable(false)
                        .setPositiveButton("Submit",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        // get user input and set it to result
                                        // edit text
//                                        result.setText(userInput.getText());

                                        AndroidNetworking.post("https://services-uat.loyrewards.com/404eureka-web-app-1.0-SNAPSHOT/aadhar/getUIDAIData")
                                                .addBodyParameter("uniqueResourceId", "123")
                                                .addBodyParameter("countryCode", "11")
                                            .setContentType("application/json")
                                                .setPriority(Priority.HIGH)
                                                .build()
                                                .getAsJSONArray(new JSONArrayRequestListener() {
                                                    @Override
                                                    public void onResponse(JSONArray response) {
                                                        // do anything with response
                                                        Log.d("tag", "onResponse: "+response.toString());
                                                        Toast.makeText(UIDSignUpActivity.this, "success", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(UIDSignUpActivity.this, MainActivity.class);
                                                        startActivity(intent);
                                                    }
                                                    @Override
                                                    public void onError(ANError error) {
                                                        // handle error
                                                        Log.d("tag", "onResponse: "+error.toString());
//                                                        Toast.makeText(UIDSignUpActivity.this, "error", Toast.LENGTH_SHORT).show();
                                                        Intent intent = new Intent(UIDSignUpActivity.this, MainActivity.class);
                                                        startActivity(intent);

                                                    }
                                                });



                                    }
                                })
                        .setNegativeButton("Cancel",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog,int id) {
                                        dialog.cancel();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }



}
