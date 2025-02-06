package com.creativeitinstitute.databasestart;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        MyDBHelper myDBHelper = new MyDBHelper(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        try {
            //MyDBHelper myDBHelper = new MyDBHelper(this);
            myDBHelper.addContact("Samin", "568031201");
            myDBHelper.addContact("Tuli", "568031202");
            myDBHelper.addContact("Minhaz", "568031203");
            myDBHelper.addContact("Mostabi", "568031204");
            myDBHelper.addContact("JAsim", "568031205");
        } catch (Exception e) {
            Log.e("DatabaseError", "Error adding contacts", e);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                Boolean check = preferences.getBoolean("flag", false);

                // Add logging statements here:
                Log.d("MainActivity", "Flag value: " + check);

                Intent intent;
                if (check) {
                    intent = new Intent(MainActivity.this, HomeActivity.class);
                    Log.d("MainActivity", "Launching HomePage");
                } else {
                    intent = new Intent(MainActivity.this, Login.class);
                    Log.d("MainActivity", "Launching Login");
                }
                startActivity(intent);
            }
        }, 3000);
    }


}



/*
Explanation of the Added Code
new Handler().postDelayed(...): This creates a Handler and uses postDelayed() to schedule a Runnable to be executed after a delay of 3000 milliseconds (3 seconds).
Runnable: Inside the Runnable, the following happens:
SharedPreferences: It retrieves shared preferences named "login".
check: It gets a boolean value named "flag" from the preferences, defaulting to false if not found.
Intent: Based on the value of check, it creates an Intent to either HomePage.class (if check is true) or LoginActivity.class (if check is false).
startActivity(): It starts
the activity specified by the Intent. Functionality This code essentially creates a
splash screen or a delayed start for your app. After 3 seconds, it checks a shared preference
flag named "flag". If the flag is true (indicating the user has logged in before), it launches the
HomePage activity. Otherwise, it launches the LoginActivity.





 */