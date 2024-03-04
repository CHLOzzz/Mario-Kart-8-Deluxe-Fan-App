package org.idkproductions.mariokart8deluxefanapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.activity.OnBackPressedDispatcher;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
    private final OnBackPressedDispatcher dispatcher = getOnBackPressedDispatcher();

    /**
     * TODO
     *
     * @param table TODO
     * @param data TODO
     */
    @SuppressLint("SetTextI18n")
    private void occupyTableLayoutInGameStats(TableLayout table, ArrayList<StatHolders> data) {
        // Declare temp variables
        ImageView image;
        TableRow row;
        TextView text;
        StatHolders entity;

        // Initialize String array to allow for looping through occupying first row
        String[] strings = {" Driver ", " Icon ", " Speed ", " Acceleration ", " Weight ", " Handling ", " Traction ", " Stat Total "};

        // Setup first row of TableLayout
        row = new TableRow(this); // Initialize new TableRow to add
        for(int i = 0; i < strings.length; i++) {
            text = new TextView(this);       // [1] Initialize new TextView to add to current TableRow
            text.setText(strings[i]);        // [2] Setup TextView
            text.setGravity(Gravity.CENTER); // [2]
            row.addView(text);               // [3] Add TextView to TableRow
        }
        table.addView(row); // Add TableRow to TableLayout

        // Loop through occupying each row
        for(int i = 0; i < data.size(); i++) {
            entity = data.get(i); // Temporarily store entity for reduced indexing
            row = new TableRow(this); // Initialize new TableRow to add
            text = new TextView(this); // [1]
            text.setText(entity.getName()+" ");  // [2]
            //text.setGravity(Gravity.CENTER); // [2]
            row.addView(text); // [3]
            image = new ImageView(this); // Initialize new ImageView to add to current TableRow
            image.setImageBitmap(entity.getIcon()); // Setup ImageView
            row.addView(image); // Add ImageView to current TableRow
            text = new TextView(this);                             // [1]
            text.setText(String.valueOf(entity.getInGameSpeed())); // [2]
            text.setGravity(Gravity.CENTER);                       // [2]
            row.addView(text);                                     // [3]
            text = new TextView(this);                                    // [1]
            text.setText(String.valueOf(entity.getInGameAcceleration())); // [2]
            text.setGravity(Gravity.CENTER);                              // [2]
            row.addView(text);                                            // [3]
            text = new TextView(this);                              // [1]
            text.setText(String.valueOf(entity.getInGameWeight())); // [2]
            text.setGravity(Gravity.CENTER);                        // [2]
            row.addView(text);                                      // [3]
            text = new TextView(this);                                // [1]
            text.setText(String.valueOf(entity.getInGameHandling())); // [2]
            text.setGravity(Gravity.CENTER);                          // [2]
            row.addView(text);                                        // [3]
            text = new TextView(this);                                // [1]
            text.setText(String.valueOf(entity.getInGameTraction())); // [2]
            text.setGravity(Gravity.CENTER);                          // [2]
            row.addView(text);                                        // [3]
            text = new TextView(this);                                 // [1]
            text.setText(String.valueOf(entity.getInGameStatTotal())); // [2]
            text.setGravity(Gravity.CENTER);                           // [2]
            row.addView(text);                                         // [3]

            // Set (starting from index 1) even rows background color to make reading table easier
            if(i%2 == 0) {
                row.setBackgroundColor(Color.CYAN);
            }

            table.addView(row); // Add TableRow to TableLayout
        }
    }

    /**
     * TODO
     */
    @SuppressLint("SetTextI18n")
    private void occupyTableLayoutAllStats(TableLayout table, ArrayList<StatHolders> data) {
        // Declare temp variables
        ImageView image;
        TableRow row;
        TextView text;
        StatHolders entity;

        // Initialize String array to allow for looping through occupying first row
        String[] strings = {" Driver ", " Icon ", " Weight ", " Acceleration ", " On-Road Traction ", " Off-Road Traction ", " Mini-Turbo ", " Ground Speed ", " Water Speed ", " Anti-Gravity Speed ", " Air Speed ", " Ground Handling ", " Water Handling ", " Anti-Gravity Handling ", " Air Handling ", " Invincibility ", " Stat Total "};

        // Setup first row of TableLayout
        row = new TableRow(this); // Initialize new TableRow to add
        for(int i = 0; i < strings.length; i++) {
            text = new TextView(this);       // [1] Initialize new TextView to add to current TableRow
            text.setText(strings[i]);        // [2] Setup TextView
            text.setGravity(Gravity.CENTER); // [2]
            row.addView(text);               // [3] Add TextView to TableRow
        }
        table.addView(row); // Add TableRow to TableLayout

        // Loop through occupying each row
        for(int i = 0; i < data.size(); i++) {
            entity = data.get(i); // Temporarily store entity for reduced indexing
            row = new TableRow(this); // Initialize new TableRow to add
            text = new TextView(this);           // [1]
            text.setText(entity.getName()+" ");  // [2]
            row.addView(text);                   // [3]
            image = new ImageView(this); // Initialize new ImageView to add to current TableRow
            image.setImageBitmap(entity.getIcon()); // Setup ImageView
            row.addView(image); // Add ImageView to current TableRow
            text = new TextView(this);                           // [1]
            text.setText(String.valueOf(entity.getWeight())); // [2]
            text.setGravity(Gravity.CENTER);                     // [2]
            row.addView(text);                                   // [3]
            text = new TextView(this);                             // [1]
            text.setText(String.valueOf(entity.getAcceleration())); // [2]
            text.setGravity(Gravity.CENTER);                       // [2]
            row.addView(text);                                     // [3]
            text = new TextView(this);                            // [1]
            text.setText(String.valueOf(entity.getOnRoadTraction())); // [2]
            text.setGravity(Gravity.CENTER);                      // [2]
            row.addView(text);                                    // [3]
            text = new TextView(this);                                  // [1]
            text.setText(String.valueOf(entity.getOffRoadTraction())); // [2]
            text.setGravity(Gravity.CENTER);                            // [2]
            row.addView(text);                                          // [3]
            text = new TextView(this);                          // [1]
            text.setText(String.valueOf(entity.getMiniTurbo())); // [2]
            text.setGravity(Gravity.CENTER);                    // [2]
            row.addView(text);                                  // [3]
            text = new TextView(this);                              // [1]
            text.setText(String.valueOf(entity.getGroundSpeed())); // [2]
            text.setGravity(Gravity.CENTER);                        // [2]
            row.addView(text);                                      // [3]
            text = new TextView(this);                        // [1]
            text.setText(String.valueOf(entity.getWaterSpeed())); // [2]
            text.setGravity(Gravity.CENTER);                  // [2]
            row.addView(text);                                // [3]
            text = new TextView(this);                                // [1]
            text.setText(String.valueOf(entity.getAntiGravitySpeed())); // [2]
            text.setGravity(Gravity.CENTER);                          // [2]
            row.addView(text);                                        // [3]
            text = new TextView(this);                               // [1]
            text.setText(String.valueOf(entity.getAirSpeed())); // [2]
            text.setGravity(Gravity.CENTER);                         // [2]
            row.addView(text);                                       // [3]
            text = new TextView(this);                                     // [1]
            text.setText(String.valueOf(entity.getGroundHandling())); // [2]
            text.setGravity(Gravity.CENTER);                               // [2]
            row.addView(text);                                             // [3]
            text = new TextView(this);                             // [1]
            text.setText(String.valueOf(entity.getWaterHandling())); // [2]
            text.setGravity(Gravity.CENTER);                       // [2]
            row.addView(text);                                     // [3]
            text = new TextView(this);                                // [1]
            text.setText(String.valueOf(entity.getAntiGravityHandling())); // [2]
            text.setGravity(Gravity.CENTER);                          // [2]
            row.addView(text);                                        // [3]
            text = new TextView(this);                                 // [1]
            text.setText(String.valueOf(entity.getAirHandling())); // [2]
            text.setGravity(Gravity.CENTER);                           // [2]
            row.addView(text);                                         // [3]
            text = new TextView(this);                               // [1]
            text.setText(String.valueOf(entity.getInvincibility())); // [2]
            text.setGravity(Gravity.CENTER);                         // [2]
            row.addView(text);                                       // [3]
            text = new TextView(this);                           // [1]
            text.setText(String.valueOf(entity.getStatTotal())); // [2]
            text.setGravity(Gravity.CENTER);                     // [2]
            row.addView(text);                                   // [3]

            // Set (starting from index 1) even rows background color to make reading table easier
            if(i%2 == 0) {
                row.setBackgroundColor(Color.CYAN);
            }

            table.addView(row); // Add TableRow to TableLayout
        }
    }

    /**
     * TODO
     */
    private void entityTableLayout(Button entityButton, ScrollView scrollView, TableLayout[] tableArray) {
        // First remove ScrollView children; will be replaced
        scrollView.removeAllViews();

        // If "All Stats" Switch toggled to false
        if(!allStatsBool) {
            if(chooseEntityButtonText.equals("Drivers")) {
                chooseEntityButtonText = "Karts";
                scrollView.addView(tableArray[1]);
            } else if(chooseEntityButtonText.equals("Karts")) {
                chooseEntityButtonText = "Tires";
                scrollView.addView(tableArray[2]);
            } else if(chooseEntityButtonText.equals("Tires")) {
                chooseEntityButtonText = "Gliders";
                scrollView.addView(tableArray[3]);
            } else if(chooseEntityButtonText.equals("Gliders")) {
                chooseEntityButtonText = "Drivers";
                scrollView.addView(tableArray[0]);
            } else {
                // UNEXPECTED: THROW EXCEPTION
                throw new RuntimeException();
            }
        // If "All Stats" Switch toggled to true
        } else {
            if(chooseEntityButtonText.equals("Drivers")) {
                chooseEntityButtonText = "Karts";
                scrollView.addView(tableArray[5]);
            } else if(chooseEntityButtonText.equals("Karts")) {
                chooseEntityButtonText = "Tires";
                scrollView.addView(tableArray[6]);
            } else if(chooseEntityButtonText.equals("Tires")) {
                chooseEntityButtonText = "Gliders";
                scrollView.addView(tableArray[7]);
            } else if(chooseEntityButtonText.equals("Gliders")) {
                chooseEntityButtonText = "Drivers";
                scrollView.addView(tableArray[4]);
            } else {
                // UNEXPECTED: THROW EXCEPTION
                throw new RuntimeException();
            }
        }

        // Update Button text
        entityButton.setText(chooseEntityButtonText);
    }

    /**
     * TODO
     */
    private void switchStatsTable(boolean allStats, ScrollView scrollView, TableLayout[] tableArray) {
        // Set identifying boolean
        allStatsBool = allStats;

        // First remove ScrollView children; will be replaced
        scrollView.removeAllViews();

        // Replace with appropriate TableLayout
        // If "All Stats" Switch toggled to false
        if(!allStatsBool) {
            if(chooseEntityButtonText.equals("Drivers")) {
                scrollView.addView(tableArray[0]);
            } else if(chooseEntityButtonText.equals("Karts")) {
                scrollView.addView(tableArray[1]);
            } else if(chooseEntityButtonText.equals("Tires")) {
                scrollView.addView(tableArray[2]);
            } else if(chooseEntityButtonText.equals("Gliders")) {
                scrollView.addView(tableArray[3]);
            } else {
                // UNEXPECTED: THROW EXCEPTION
                throw new RuntimeException();
            }
            // If "All Stats" Switch toggled to true
        } else {
            if(chooseEntityButtonText.equals("Drivers")) {
                scrollView.addView(tableArray[4]);
            } else if(chooseEntityButtonText.equals("Karts")) {
                scrollView.addView(tableArray[5]);
            } else if(chooseEntityButtonText.equals("Tires")) {
                scrollView.addView(tableArray[6]);
            } else if(chooseEntityButtonText.equals("Gliders")) {
                scrollView.addView(tableArray[7]);
            } else {
                // UNEXPECTED: THROW EXCEPTION
                throw new RuntimeException();
            }
        }
    }

    // Initialize variables to keep track of app state
    String chooseEntityButtonText = "Drivers";
    boolean allStatsBool = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Draw XML layout on activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Initialize application class containing pre-built data
        ThisApplication appContext = (ThisApplication) getApplicationContext();

        // Pull data
        final ArrayList<StatHolders> allDrivers = appContext.getAllDrivers();
        final ArrayList<StatHolders> allKarts = appContext.getAllKarts();
        final ArrayList<StatHolders> allTires = appContext.getAllTires();
        final ArrayList<StatHolders> allGliders = appContext.getAllGliders();

        // Initialize TableLayouts
        TableLayout driverInGameStats = new TableLayout(this);
        TableLayout driverAllStats = new TableLayout(this);
        TableLayout kartInGameStats = new TableLayout(this);
        TableLayout kartAllStats = new TableLayout(this);
        TableLayout tireInGameStats = new TableLayout(this);
        TableLayout tireAllStats = new TableLayout(this);
        TableLayout gliderInGameStats = new TableLayout(this);
        TableLayout gliderAllStats = new TableLayout(this);

        // Initialize ScrollView
        ScrollView scrollView = (ScrollView) findViewById(R.id.stats_scroll_view);

        // Occupy TableLayouts with respective data
        // In Game stats
        occupyTableLayoutInGameStats(driverInGameStats, allDrivers);
        occupyTableLayoutInGameStats(kartInGameStats, allKarts);
        occupyTableLayoutInGameStats(tireInGameStats, allTires);
        occupyTableLayoutInGameStats(gliderInGameStats, allGliders);
        // All stats
        occupyTableLayoutAllStats(driverAllStats, allDrivers);
        occupyTableLayoutAllStats(kartAllStats, allKarts);
        occupyTableLayoutAllStats(tireAllStats, allTires);
        occupyTableLayoutAllStats(gliderAllStats, allGliders);

        // Assign TableLayouts to array to make passing to functions more compact
        TableLayout[] tableArray = {driverInGameStats, kartInGameStats, tireInGameStats, gliderInGameStats, driverAllStats, kartAllStats, tireAllStats, gliderAllStats};

        // Initialize Buttons
        Button backButton = findViewById(R.id.stats_back_button);
        Button chooseEntityButton = findViewById(R.id.choose_entity_button);

        // Initialize Switches
        Switch allStatsSwitch = (Switch) findViewById(R.id.switch_all_stats);

        // Set initial TableLayout & Button text
        scrollView.addView(driverInGameStats);
        chooseEntityButton.setText(chooseEntityButtonText);

        // Set back Button listener
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(StatsActivity.this, HomeActivity.class));
            finish();
        });

        // Set entity Button listener
        chooseEntityButton.setOnClickListener(v -> {
            entityTableLayout(chooseEntityButton, scrollView, tableArray);
        });

        // Set Switch listeners
        allStatsSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                switchStatsTable(b, scrollView, tableArray);
            }
        });

        // Set Android back button listener
        dispatcher.addCallback(new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                startActivity(new Intent(StatsActivity.this, HomeActivity.class));
                finish();
            }
        });
    }
}