package com.example.termproject;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.app.Activity;
import android.app.Activity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Dictionary;
import android.widget.Toast;
import android.widget.ImageView;


import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;
import java.util.ArrayList;

public class ThirdActivity extends Activity{

    private Button breakfastButton;
    private Button lunchButton;
    private Button dinnerButton;

    private ImageView topImage;

    private ImageView middleImage;

    private ImageView bottomImage;
    private Dictionary<String, String[]> nutritionDictionary = new Hashtable<>();

    private DatabaseHelper db;
    private TextView hiddenTextMealType;
    private TextView hiddenTextFood;
    private Button mealGeneration;


    public String foodNameAdjuster(String drawnName)
    {
     String adjustedName;
     adjustedName = drawnName.replace(" ", "_");
     adjustedName = adjustedName.toLowerCase();

     return adjustedName;
    }

    public String[] getNutritionalInformation(String key)
    {
        return nutritionDictionary.get(key);
    }

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        db = new DatabaseHelper(this);

        breakfastButton = findViewById(R.id.breakfastButton);
        lunchButton = findViewById(R.id.lunchButton);
        dinnerButton = findViewById(R.id.dinnerButton);
        hiddenTextMealType = findViewById(R.id.hiddenTextView);
        hiddenTextFood = findViewById(R.id.hiddenFood);
        mealGeneration = findViewById(R.id.mealGeneration);
        topImage = findViewById(R.id.topMeal);
        bottomImage = findViewById(R.id.bottomMeal);
        middleImage = findViewById(R.id.middleMeal);
        Random randomFood = new Random();
        Intent recievedIntent = getIntent();
        int userID = recievedIntent.getIntExtra("UserID", -1);
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
        boolean isDisliked;
        boolean isLiked;

        for(int i = 0; i<breakfastFoods.size(); i++)
        {
            isDisliked = db.isDislikedFood(userID, breakfastFoods.get(i));
            isLiked = db.isLikedFood(userID, breakfastFoods.get(i));
            if (isDisliked || isLiked) {
                breakfastFoods.remove(i);
                i--;
            }
        }


        for(int i = 0; i<lunchFoods.size(); i++)
        {
            isDisliked = db.isDislikedFood(userID, lunchFoods.get(i));
            isLiked = db.isLikedFood(userID, lunchFoods.get(i));
            if (isDisliked || isLiked) {
                lunchFoods.remove(i);
                i--;
            }
        }

        for(int i = 0 ; i<dinnerFoods.size(); i++)
        {
            isDisliked = db.isDislikedFood(userID, dinnerFoods.get(i));
            isLiked = db.isLikedFood(userID, dinnerFoods.get(i));
            if (isDisliked || isLiked) {
                dinnerFoods.remove(i);
                i--;
            }
        }
        nutritionDictionary.put("Breakfast burrito", new String[] {"305", "18.57g", "267mg", "558mg", "17.96g", "16.19g"});
        nutritionDictionary.put("Banana bread", new String[] {"158", "4.82g", "20mg", "68mg", "27.03g", "2.29g"});
        nutritionDictionary.put("Boiled Egg", new String[] {"77", "5.28g", "211mg", "139mg", "0.56g", "6.26g"});
        nutritionDictionary.put("Avocado", new String[] {"160", "14.66g", "0mg", "7mg", "8.53g", "2g"});
        nutritionDictionary.put("Farro", new String[] {"200", "1.00g", "0mg", "20mg", "44.00g", "5.00g"});
        nutritionDictionary.put("Veggie Burger", new String[] {"470", "12.00g", "0mg", "1080mg", "63.00g", "28.00g"});
        nutritionDictionary.put("Chicken Parmesan", new String[] {"254", "12.38g", "108mg", "615mg", "12.18g", "22.83g"});
        nutritionDictionary.put("Lasagna", new String[] {"336", "12.38g", "49mg", "744mg", "35.43g", "20.52g"});
        nutritionDictionary.put("Pizza", new String[] {"237", "10.1g", "21mg", "462mg", "26.08g", "10.6g"});

        breakfastButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topFood;
                String middleFood;
                String bottomFood;
                if(breakfastFoods.size() >= 1) {
                    topFood = breakfastFoods.get(0);
                    topImage.setTag(topFood);
                    Integer topFoodID = imageFiles.get(topFood);
                    topImage.setImageResource(topFoodID);
                } else {
                    topImage.setImageDrawable(null);
                }
                if(breakfastFoods.size() >= 2) {
                    middleFood = breakfastFoods.get(1);
                    middleImage.setTag(middleFood);
                    Integer middleFoodID = imageFiles.get(middleFood);
                    middleImage.setImageResource(middleFoodID);
                }
                else {
                    middleImage.setImageDrawable(null);
                }
                if(breakfastFoods.size() >= 3) {
                    bottomFood = breakfastFoods.get(2);
                    bottomImage.setTag(bottomFood);
                    Integer bottomFoodID = imageFiles.get(bottomFood);
                    bottomImage.setImageResource(bottomFoodID);
                }
                else {
                    bottomImage.setImageDrawable(null);
                }
                hiddenTextMealType.setText("breakfast");
            }
        });

        lunchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topFood;
                String middleFood;
                String bottomFood;
                if(lunchFoods.size() >= 1) {
                    topFood = lunchFoods.get(0);
                    topImage.setTag(topFood);
                    Integer topFoodID = imageFiles.get(topFood);
                    topImage.setImageResource(topFoodID);
                }
                else {
                    topImage.setImageDrawable(null);
                }
                if(lunchFoods.size() >= 2) {
                    middleFood = lunchFoods.get(1);
                    middleImage.setTag(middleFood);
                    Integer middleFoodID = imageFiles.get(middleFood);
                    middleImage.setImageResource(middleFoodID);
                } else {
                    middleImage.setImageDrawable(null);
                }
                if(lunchFoods.size() >= 3) {
                    bottomFood = lunchFoods.get(2);
                    bottomImage.setTag(bottomFood);
                    Integer bottomFoodID = imageFiles.get(bottomFood);
                    bottomImage.setImageResource(bottomFoodID);
                } else {
                    bottomImage.setImageDrawable(null);
                }
                hiddenTextMealType.setText("lunch");
            }
        });

        dinnerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topFood;
                String middleFood;
                String bottomFood;
                if(dinnerFoods.size() >= 1) {
                    topFood = dinnerFoods.get(0);
                    topImage.setTag(topFood);
                    Integer topFoodID = imageFiles.get(topFood);
                    topImage.setImageResource(topFoodID);
                } else {
                    topImage.setImageDrawable(null);
                }
                if(dinnerFoods.size() >= 2) {
                    middleFood = dinnerFoods.get(1);
                    middleImage.setTag(middleFood);
                    Integer middleFoodID = imageFiles.get(middleFood);
                    middleImage.setImageResource(middleFoodID);
                } else {
                    middleImage.setImageDrawable(null);
                }
                if(dinnerFoods.size() >= 3) {
                    bottomFood = dinnerFoods.get(2);
                    bottomImage.setTag(bottomFood);
                    Integer bottomFoodID = imageFiles.get(bottomFood);
                    bottomImage.setImageResource(bottomFoodID);
                } else {
                    bottomImage.setImageDrawable(null);
                }
                hiddenTextMealType.setText("dinner");


            }
        });

        mealGeneration.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(".FourthActivity");
                i.putExtra("UserID", userID);
                startActivity(i);

            }
        });

        topImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String topFoodName = (String) topImage.getTag();
                String[] currentFoodNutInfo = nutritionDictionary.get(topFoodName);
                String nutritionalString = topFoodName + "\nCalories: " + currentFoodNutInfo[0] + "\nTotal Fat: " + currentFoodNutInfo[1] + "\nCholesterol: " + currentFoodNutInfo[2] + "\nSodium: " + currentFoodNutInfo[3] + "\nTotal Carbohydrate: " + currentFoodNutInfo[4] + "\nProtein: " + currentFoodNutInfo[5];
                new AlertDialog.Builder(ThirdActivity.this).setTitle(topFoodName + " Nutritional Information").setMessage(nutritionalString).setPositiveButton("Like", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.insertLikedFood(hiddenTextMealType.getText().toString(), topFoodName, userID);
                        Toast.makeText(ThirdActivity.this, topFoodName + " liked", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Dislike", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.insertDislikedFood(hiddenTextMealType.getText().toString(), topFoodName, userID);
                        Toast.makeText(ThirdActivity.this, topFoodName + " disliked", Toast.LENGTH_SHORT).show();
                    }
                }).show();

            }
        });

        middleImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String middleFoodName = (String) middleImage.getTag();
                String[] currentFoodNutInfo = nutritionDictionary.get(middleFoodName);
                String nutritionalString = middleFoodName + "\nCalories: " + currentFoodNutInfo[0] + "\nTotal Fat: " + currentFoodNutInfo[1] + "\nCholesterol: " + currentFoodNutInfo[2] + "\nSodium: " + currentFoodNutInfo[3] + "\nTotal Carbohydrate: " + currentFoodNutInfo[4] + "\nProtein: " + currentFoodNutInfo[5];
                new AlertDialog.Builder(ThirdActivity.this).setTitle(middleFoodName + " Nutritional Information").setMessage(nutritionalString).setPositiveButton("Like", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.insertLikedFood(hiddenTextMealType.getText().toString(), middleFoodName, userID);
                        Toast.makeText(ThirdActivity.this, middleFoodName + " liked", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Dislike", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.insertDislikedFood(hiddenTextMealType.getText().toString(), middleFoodName, userID);
                        Toast.makeText(ThirdActivity.this, middleFoodName + " disliked", Toast.LENGTH_SHORT).show();
                    }
                }).show();

            }
        });

        bottomImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bottomFoodName = (String) bottomImage.getTag();
                String[] currentFoodNutInfo = nutritionDictionary.get(bottomFoodName);
                String nutritionalString = bottomFoodName + "\nCalories: " + currentFoodNutInfo[0] + "\nTotal Fat: " + currentFoodNutInfo[1] + "\nCholesterol: " + currentFoodNutInfo[2] + "\nSodium: " + currentFoodNutInfo[3] + "\nTotal Carbohydrate: " + currentFoodNutInfo[4] + "\nProtein: " + currentFoodNutInfo[5];
                new AlertDialog.Builder(ThirdActivity.this).setTitle(bottomFoodName + " Nutritional Information").setMessage(nutritionalString).setPositiveButton("Like", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.insertLikedFood(hiddenTextMealType.getText().toString(), bottomFoodName, userID);
                        Toast.makeText(ThirdActivity.this, bottomFoodName + " liked", Toast.LENGTH_SHORT).show();
                    }
                }).setNegativeButton("Dislike", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        db.insertDislikedFood(hiddenTextMealType.getText().toString(), bottomFoodName, userID);
                        Toast.makeText(ThirdActivity.this, bottomFoodName + " disliked", Toast.LENGTH_SHORT).show();
                    }
                }).show();

            }
        });





    }


}
