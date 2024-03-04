package org.idkproductions.mariokart8deluxefanapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

/**
 * Random Activity - meant to display the result of a randomization. The result is a random driver,
 * kart, tires, and glider.
 */
public class RandomActivity extends AppCompatActivity {
    private final OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();

    /**
     * For each entity (driver, kart, tires, glider), generate a random ArrayList index, and use
     * said index to get the ArrayList<StatHolders> entity.
     *
     * @param allDrivers ArrayList<StatHolders> containing data on all driver entities
     * @param allKarts ArrayList<StatHolders> containing data on all kart entities
     * @param allTires ArrayList<StatHolders> containing data on all tire entities
     * @param allGliders ArrayList<StatHolders> containing data on all glider entities
     * @param driverIco ImageView where the driver's icon will be displayed
     * @param kartIco ImageView where the kart's icon will be displayed
     * @param tireIco ImageView where the tires' icon will be displayed
     * @param gliderIco ImageView where the glider's icon will be displayed
     * @param driverName TextView displaying the driver's name
     * @param kartName TextView displaying the kart's name
     * @param tireName TextView displaying the tires' name
     * @param gliderName TextView displaying the glider's name
     * @param rand Random generator
     */
    private void randomize(ArrayList<StatHolders> allDrivers, ArrayList<StatHolders> allKarts, ArrayList<StatHolders> allTires, ArrayList<StatHolders> allGliders, ImageView driverIco, ImageView kartIco, ImageView tireIco, ImageView gliderIco, TextView driverName, TextView kartName, TextView tireName, TextView gliderName, Random rand) {
        // Declare temp variable
        int i;

        // Display randomly selected driver
        i = rand.nextInt(allDrivers.size());
        driverIco.setImageBitmap(allDrivers.get(i).getIcon());
        driverName.setText(allDrivers.get(i).getName());

        // Display randomly selected kart
        i = rand.nextInt(allKarts.size());
        kartIco.setImageBitmap(allKarts.get(i).getIcon());
        kartName.setText(allKarts.get(i).getName());

        // Display randomly selected tires
        i = rand.nextInt(allTires.size());
        tireIco.setImageBitmap(allTires.get(i).getIcon());
        tireName.setText(allTires.get(i).getName());

        // Display randomly selected glider
        i = rand.nextInt(allGliders.size());
        gliderIco.setImageBitmap(allGliders.get(i).getIcon());
        gliderName.setText(allGliders.get(i).getName());
    }

    /**
     * Function runs whenever HomeActivity is directed to RandomActivity via a Button on
     * HomeActivity.
     *
     * @param savedInstanceState the saved state of the app before having been interrupted
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Draw XML layout on activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        // Load context of class storing all long term variables
        ThisApplication appContext = (ThisApplication) getApplicationContext();

        // Initialize data
        ArrayList<StatHolders> allDrivers = appContext.getAllDrivers();
        ArrayList<StatHolders> allKarts = appContext.getAllKarts();
        ArrayList<StatHolders> allTires = appContext.getAllTires();
        ArrayList<StatHolders> allGliders = appContext.getAllGliders();

        // Initialize ImageViews
        ImageView driverIco = findViewById(R.id.driverImageView);
        ImageView kartIco = findViewById(R.id.kartImageView);
        ImageView tireIco = findViewById(R.id.tireImageView);
        ImageView gliderIco = findViewById(R.id.gliderImageView);

        // Initialize TextViews
        TextView driverName = findViewById(R.id.driverTextView);
        TextView kartName = findViewById(R.id.kartTextView);
        TextView tireName = findViewById(R.id.tireTextView);
        TextView gliderName = findViewById(R.id.gliderTextView);

        // Initialize Buttons
        Button backButton = findViewById(R.id.rand_back_button);
        Button rerollButton = findViewById(R.id.reroll_rand_button);

        // Initialize RNG seed
        final Random rand = new Random();

        // Perform first randomization
        randomize(allDrivers, allKarts, allTires, allGliders, driverIco, kartIco, tireIco, gliderIco, driverName, kartName, tireName, gliderName, rand);

        // Setup Button listeners
        // On-screen back button
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(RandomActivity.this, HomeActivity.class));
            finish();
        });
        // Randomizer reroll
        rerollButton.setOnClickListener(v -> {
            randomize(allDrivers, allKarts, allTires, allGliders, driverIco, kartIco, tireIco, gliderIco, driverName, kartName, tireName, gliderName, rand);
        });
        // Android back button
        dispatcher.addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(RandomActivity.this, HomeActivity.class));
                finish();
            }
        });
    }
}