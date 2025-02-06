package com.creativeitinstitute.databasestart;

import android.os.Bundle;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {
    Button loginbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        loginbtn = findViewById(R.id.login) ;// Assuming "login" is the ID of your login button in the layout
        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Perform login logic here (e.g., validate credentials)

                // If login is successful:
                SharedPreferences preferences = getSharedPreferences("login", MODE_PRIVATE);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putBoolean("flag", true);
                editor.apply();

                Intent intent = new Intent(Login.this, HomeActivity.class); // Replace HomePage.class with your home activity
                startActivity(intent);
            }
        });
    }
}


/*

Explanation of Changes
findViewById(): The loginbtn is initialized using findViewById() to get a reference to the login button in your layout. Make sure the ID you use in findViewById() matches the ID of your button in the XML layout.
setOnClickListener(): An OnClickListener is attached to the loginbtn. When the button is clicked:
Login Logic: You would typically place your login logic here (e.g., validating username and password).
Shared Preferences: If the login is successful, you set a shared preference flag named "flag" to true to indicate the user is logged in.
Intent: You create an Intent to launch your home activity (replace HomePage.class with the actual class name of your home activity).
startActivity(): You start the home activity using the Intent. Important Considerations
Login Logic: You need to replace the comment // Perform login logic here with your actual login validation code. This might involve checking credentials against a database or an API.
Home Activity: Make sure you replace HomePage.class with the correct class name of your home activity.
Error Handling: You should add error handling to your login logic to display messages to the user if the login fails. I hope this helps! Let me know if you have any other questions.
 */