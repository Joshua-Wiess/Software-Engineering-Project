package com.example.termproject;

import android.app.Activity;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;

public class SecondActivity extends Activity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void onClick(View view)
    {
        EditText registeredUsername = (EditText) findViewById(R.id.registrationUsername);
        EditText registeredPassword = (EditText) findViewById(R.id.registrationPassword);
        Intent data = new Intent();
        data.putExtra("username", registeredUsername.getText().toString());
        data.putExtra("password", registeredPassword.getText().toString());
        setResult(1, data);
        finish();

    }



}
