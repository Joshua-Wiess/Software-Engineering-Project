package com.example.termproject;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.content.Intent;
import android.net.Uri;
import android.text.SpannableStringBuilder;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


public class FourthActivity extends Activity{

    String[] breakfastFoodsArray = new String[10];
    String[] lunchFoodsArray = new String[10];
    String[] dinnerFoodsArray = new String[10];
    TextView breakfastOptions;
    TextView lunchOptions;
    TextView dinnerOptions;
    ImageView breakfastLeft;
    ImageView breakfastRight;
    ImageView lunchLeft;
    ImageView lunchRight;
    ImageView dinnerLeft;
    ImageView dinnerRight;

    DatabaseHelper db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four);

        ArrayList<String> breakfastFoods = new ArrayList<>(Arrays.asList("Breakfast burrito", "Banana bread", "Boiled Egg"));
        Map<String, Integer> imageFiles = new HashMap<>();
        imageFiles.put("Breakfast burrito", R.drawable.breakfast_burrito);
        imageFiles.put("Banana bread", R.drawable.banana_bread);
        imageFiles.put("Boiled Egg", R.drawable.boiled_egg);
        imageFiles.put("Avocado", R.drawable.avocado);
        imageFiles.put("Farro", R.drawable.farro);
        imageFiles.put("Veggie Burger", R.drawable.veggie_burger);
        imageFiles.put("Chicken Parmesan", R.drawable.chicken_parmesan);
        imageFiles.put("Lasagna", R.drawable.lasagna);
        imageFiles.put("Pizza", R.drawable.pizza);
        ArrayList<String> lunchFoods = new ArrayList<>(Arrays.asList("Avocado", "Farro", "Veggie Burger"));
        ArrayList<String> dinnerFoods = new ArrayList<>(Arrays.asList("Chicken Parmesan", "Lasagna", "Pizza"));

        breakfastOptions = findViewById(R.id.potentialBreakfast);
        breakfastLeft = findViewById(R.id.breakfastLeft);
        breakfastRight = findViewById(R.id.breakfastRight);
        lunchLeft = findViewById(R.id.lunchLeft);
        lunchRight = findViewById(R.id.lunchRight);
        dinnerLeft = findViewById(R.id.dinnerLeft);
        dinnerRight = findViewById(R.id.dinnerRight);
        lunchOptions = findViewById(R.id.potentialLunch);
        dinnerOptions = findViewById(R.id.potentialDinner);
        db = new DatabaseHelper(this);
        Intent recievedIntent = getIntent();
        int userID = recievedIntent.getIntExtra("UserID", -1);
        Cursor c = db.getLikedFoods(userID);
        int breakfastCounter = 0;
        int lunchCounter = 0;
        int dinnerCounter = 0;


        while (c.moveToNext() && breakfastCounter < 10 && lunchCounter < 10 && dinnerCounter < 10) {

            if (c.getString(1).equals("breakfast")) {
                breakfastFoodsArray[breakfastCounter] = c.getString(0);
                breakfastCounter++;

            } else if (c.getString(1).equals("lunch")) {

                lunchFoodsArray[lunchCounter] = c.getString(0);
                lunchCounter++;

            } else if (c.getString(1).equals("dinner")) {

                dinnerFoodsArray[dinnerCounter] = c.getString(0);
                dinnerCounter++;

            }
        }


        Integer breakfastLeftID = null;
        Integer breakfastRightID = null;
        Integer lunchLeftID = null;
        Integer lunchRightID = null;
        Integer dinnerLeftID = null;
        Integer dinnerRightID = null;

        if (breakfastFoodsArray.length > 0 && breakfastFoodsArray[0] != null) {
            breakfastLeftID = imageFiles.get((String)breakfastFoodsArray[0]);
            breakfastLeft.setImageResource(breakfastLeftID);
            breakfastLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FourthActivity.this, breakfastFoodsArray[0], Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (breakfastFoodsArray.length > 1 && breakfastFoodsArray[1] != null){
            breakfastRightID = imageFiles.get((String)breakfastFoodsArray[1]);
            breakfastRight.setImageResource(breakfastRightID);
            breakfastRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FourthActivity.this, breakfastFoodsArray[1], Toast.LENGTH_SHORT).show();

                }
            });
         }
        if (lunchFoodsArray.length > 0 && lunchFoodsArray[0] != null) {
            lunchLeftID = imageFiles.get((String)lunchFoodsArray[0]);
            lunchLeft.setImageResource(lunchLeftID);
            lunchLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FourthActivity.this, lunchFoodsArray[0], Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (lunchFoodsArray.length > 1 && lunchFoodsArray[1] != null) {
            lunchRightID = imageFiles.get((String)lunchFoodsArray[1]);
            lunchRight.setImageResource(lunchRightID);
            lunchRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FourthActivity.this, lunchFoodsArray[1], Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (dinnerFoodsArray.length > 0 && dinnerFoodsArray[0] != null) {
            dinnerLeftID = imageFiles.get((String)dinnerFoodsArray[0]);
            dinnerLeft.setImageResource(dinnerLeftID);
            dinnerLeft.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FourthActivity.this, dinnerFoodsArray[0], Toast.LENGTH_SHORT).show();

                }
            });
        }
        if (dinnerFoodsArray.length > 1 && dinnerFoodsArray[1] != null) {
            dinnerRightID = imageFiles.get((String)dinnerFoodsArray[1]);
            dinnerRight.setImageResource(dinnerRightID);
            dinnerRight.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(FourthActivity.this, dinnerFoodsArray[1], Toast.LENGTH_SHORT).show();

                }
            });
        }



    }

}
