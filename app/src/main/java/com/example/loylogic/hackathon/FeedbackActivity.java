package com.example.loylogic.hackathon;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.loylogic.hackathon.Model.Reaction;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);


        RecyclerView depatrmentRCView = (RecyclerView) findViewById(R.id.reactionList);

        Intent intent = getIntent();
        if (intent.hasExtra("title")) {
            getSupportActionBar().setTitle(intent.getStringExtra("title"));

        }
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<Reaction> ReactionList = new ArrayList<>();
        ReactionList.add(new Reaction("Excellent", "#303F9F", "ic_ratings_excellent_deselected", "ic_excellent", false));
        ReactionList.add(new Reaction("Good", "#303F9F", "ic_ratings_good_deselected", "ic_good", false));
        ReactionList.add(new Reaction("Fair", "#303F9F", "ic_ratings_fair_deselected", "ic_fair", false));
        ReactionList.add(new Reaction("Poor", "#303F9F", "ic_ratings_poor_deselected", "ic_poor", false));
        ReactionList.add(new Reaction("Disgusting", "#303F9F", "ic_ratings_disgusting_deselected", "ic_disgusting", false));

        ReactionAdapter mAdapter = new ReactionAdapter(ReactionList, this, R.layout.reacition_row);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true);
        depatrmentRCView.setLayoutManager(mLayoutManager);
        depatrmentRCView.setItemAnimator(new DefaultItemAnimator());
        DashBoardFragment.SeparatorDecoration decoration = new DashBoardFragment.SeparatorDecoration(this, Color.WHITE, 3.5f);
        depatrmentRCView.addItemDecoration(decoration);
        depatrmentRCView.setAdapter(mAdapter);

        final EditText editText = findViewById(R.id.EditText02);

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//
//                JSONObject jsonObject = null;
//                try {
//                    jsonObject = new JSONObject();
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//                AndroidNetworking.post(" http://services-uat.loyrewards.com/loyconnect/loyconnect/programService/creditPoints")
//
//                            .addJSONObjectBody(jsonObject)
//                        .setTag("test")
//                        .setPriority(Priority.MEDIUM)
//                        .build()
//                        .getAsJSONObject(new JSONObjectRequestListener() {
//                            @Override
//                            public void onResponse(JSONObject response) {
//                                // do anything with response
//                                showCOnfirmation();
//                            }
//
//                            @Override
//                            public void onError(ANError error) {
//                                // handle error
//                            }
//                        });

                LayoutInflater li = LayoutInflater.from(FeedbackActivity.this);

                View promptsView = li.inflate(R.layout.feedback_confirmation, null);

                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                        FeedbackActivity.this);

                // set prompts.xml to alertdialog builder
                alertDialogBuilder.setView(promptsView);


                // set dialog message
                alertDialogBuilder
                        .setCancelable(true)
                        .setPositiveButton("",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        // get user input and set it to result
                                        // edit text
//                                        result.setText(userInput.getText());
                                    }
                                })
                        .setNegativeButton("",
                                new DialogInterface.OnClickListener() {
                                    public void onClick(DialogInterface dialog, int id) {
                                        dialog.cancel();
                                        onBackPressed();
                                    }
                                });

                // create alert dialog
                AlertDialog alertDialog = alertDialogBuilder.create();

                // show it
                alertDialog.setOnCancelListener(new DialogInterface.OnCancelListener() {
                    @Override
                    public void onCancel(DialogInterface dialogInterface) {
                        onBackPressed();
                    }
                });
                alertDialog.show();
                new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected Void doInBackground(Void... voids) {
                        try {
                            String body = editText.getText().toString();
                            MailService mailer = new MailService("404eureka@gmail.com", "jyotisakhare13@gmail.com", "User feedback", body, body);
                            mailer.sendAuthenticated();
                        } catch (Exception e) {
                            Log.e("TAG", "Failed sending email.", e);
                        }
                        return null;
                    }
                }.execute();


            }
        });
    }




    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
