package com.example.termproject;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    private EditText usernameInput;
    private EditText passwordInput;
    private Button loginButton;
    private Button registrationButton;
    public DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DatabaseHelper(this);

        usernameInput = findViewById(R.id.usernameInput);
        passwordInput = findViewById(R.id.passwordInput);
        loginButton = findViewById(R.id.loginButton);
        registrationButton = findViewById(R.id.registerButton);


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String username = usernameInput.getText().toString();
                String password = passwordInput.getText().toString();

                boolean userFound =  db.userValidation(username, password);

                if(userFound == true)
                {
                    Toast.makeText(MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(".ThirdActivity");
                    i.putExtra("UserID", db.getUserID(username, password));
                    startActivity(i);
                }
                else
                {
                    Toast.makeText(MainActivity.this, "Unsuccessful Login", Toast.LENGTH_SHORT).show();
                }


            }
        });

        registrationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(".SecondActivity");
                startActivityForResult(i, 1);
            }
        });


    }



    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode == 1)
        {
            Bundle inputs = data.getExtras();
            long id = db.insertNewUser(inputs.getString("username"), inputs.getString("password"));
        }

    }



}