package org.idkproductions.mariokart8deluxefanapp;

import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

/**
 * Home Activity - meant to be the first thing that loads when a user opens the application.
 */
public class HomeActivity extends AppCompatActivity {
    /**
     * Function runs whenever HomeActivity is created, either a back button from another activity or
     * on app launch.
     *
     * @param savedInstanceState the saved state of the app before having been interrupted
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Draw XML layout on activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Initialize buttons
        Button randButton = findViewById(R.id.random_button);
        Button statButton = findViewById(R.id.stats_button);

        // Initialize background & set image
        ImageView background = findViewById(R.id.backgroundImageView);
        background.setImageBitmap(BitmapFactory.decodeStream(getResources().openRawResource(R.raw.vertical_mk8_background)));

        // Set button listeners
        randButton.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, RandomActivity.class));
            finish();
        });
        statButton.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, StatsActivity.class));
            finish();
        });
    }
}