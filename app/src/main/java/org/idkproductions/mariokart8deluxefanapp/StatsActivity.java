package org.idkproductions.mariokart8deluxefanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class StatsActivity extends AppCompatActivity {
    /**
     * Assign all data from a particular .csv raw resources to variables to work with.
     * This function uses the assignDataHelper function to assign Bitmap.
     *
     * @param inputStream raw .csv resource converted to an input stream
     * @return ArrayList<StatHolders> from helper function
     */
    private ArrayList<StatHolders> assignData(InputStream inputStream) {
        // Initialize important variables
        ArrayList<StatHolders> toReturn = new ArrayList<>();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        // Declare temp variable
        String line;
        String[] lineArray;

        // Read information from passed in CSV file
        try {
            // First line is meant for user readability; throw it away
            reader.readLine();

            // Actually read in data; loop through while next line isn't empty
            while((line = reader.readLine()) != null) {
                // Initialize new object containing all stats
                StatHolders entityToAdd = new StatHolders();

                // Separate the line String into an array
                lineArray = line.split(",");

                // Assign appropriate data from line to new object
                entityToAdd.setName(lineArray[1]);
                entityToAdd.setWeight(Integer.parseInt(lineArray[2]));
                entityToAdd.setAcceleration(Integer.parseInt(lineArray[3]));
                entityToAdd.setOnRoadTraction(Integer.parseInt(lineArray[4]));
                entityToAdd.setOffRoadTraction(Integer.parseInt(lineArray[5]));
                entityToAdd.setMiniTurbo(Integer.parseInt(lineArray[6]));
                entityToAdd.setGroundSpeed(Integer.parseInt(lineArray[7]));
                entityToAdd.setWaterSpeed(Integer.parseInt(lineArray[8]));
                entityToAdd.setAntiGravitySpeed(Integer.parseInt(lineArray[9]));
                entityToAdd.setAirSpeed(Integer.parseInt(lineArray[10]));
                entityToAdd.setGroundHandling(Integer.parseInt(lineArray[11]));
                entityToAdd.setWaterHandling(Integer.parseInt(lineArray[12]));
                entityToAdd.setAntiGravityHandling(Integer.parseInt(lineArray[13]));
                entityToAdd.setAirHandling(Integer.parseInt(lineArray[14]));
                entityToAdd.setInvincibility(Integer.parseInt(lineArray[15]));

                // Special lines exclusive to StatsActivity
                // Set stat totals
                entityToAdd.setStatTotal();
                entityToAdd.setInGameStats();

                // Add Object after helperAssignData to ArrayList to return
                toReturn.add(helperAssignData(entityToAdd));
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }

    /**
     * Helper method to assignData.
     * Takes provided StatHolders Object, and sets Bitmap variable per each StatHolders Object
     * by processing the name of the entity.
     *
     * @param toReturn StatHolders Object without Bitmap variable set
     * @return ... with Bitmap variable set
     */
    @SuppressLint("DiscouragedApi")
    private StatHolders helperAssignData(StatHolders toReturn) {
        // Initialize variable
        String name = toReturn.getName().replaceAll(" ","_").replaceAll("[-,().]","").toLowerCase();

        // Handle exception due to R.raw._____ naming rule
        // So far, just the 300 SL Roadster kart since it starts with a number
        if(name.equals("300_sl_roadster")) {
            name = "sl_300_roadster";
        }

        // Initialize Bitmap icon variable for easier to read code
        Bitmap icon = BitmapFactory.decodeStream(getResources().openRawResource(getResources().getIdentifier(name, "raw", getPackageName())));

        // Set icon
        toReturn.setIcon(icon);

        return toReturn;
    }

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
        String[] strings = {" Driver ", " Icon ", " Mini-Turbo ", " Ground Speed ", " Water Speed ", " Anti-Gravity Speed ", " Air Speed ", " Acceleration ", " Weight ", " Ground Handling ", " Water Handling ", " Anti-Gravity Handling ", " Air Handling ", " On-Road Traction ", " Off-Road Traction ", " Invincibility ", " Stat Total "};

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
            text.setText(String.valueOf(entity.getMiniTurbo())); // [2]
            text.setGravity(Gravity.CENTER);                     // [2]
            row.addView(text);                                   // [3]
            text = new TextView(this);                             // [1]
            text.setText(String.valueOf(entity.getGroundSpeed())); // [2]
            text.setGravity(Gravity.CENTER);                       // [2]
            row.addView(text);                                     // [3]
            text = new TextView(this);                            // [1]
            text.setText(String.valueOf(entity.getWaterSpeed())); // [2]
            text.setGravity(Gravity.CENTER);                      // [2]
            row.addView(text);                                    // [3]
            text = new TextView(this);                                  // [1]
            text.setText(String.valueOf(entity.getAntiGravitySpeed())); // [2]
            text.setGravity(Gravity.CENTER);                            // [2]
            row.addView(text);                                          // [3]
            text = new TextView(this);                          // [1]
            text.setText(String.valueOf(entity.getAirSpeed())); // [2]
            text.setGravity(Gravity.CENTER);                    // [2]
            row.addView(text);                                  // [3]
            text = new TextView(this);                              // [1]
            text.setText(String.valueOf(entity.getAcceleration())); // [2]
            text.setGravity(Gravity.CENTER);                        // [2]
            row.addView(text);                                      // [3]
            text = new TextView(this);                        // [1]
            text.setText(String.valueOf(entity.getWeight())); // [2]
            text.setGravity(Gravity.CENTER);                  // [2]
            row.addView(text);                                // [3]
            text = new TextView(this);                                // [1]
            text.setText(String.valueOf(entity.getGroundHandling())); // [2]
            text.setGravity(Gravity.CENTER);                          // [2]
            row.addView(text);                                        // [3]
            text = new TextView(this);                               // [1]
            text.setText(String.valueOf(entity.getWaterHandling())); // [2]
            text.setGravity(Gravity.CENTER);                         // [2]
            row.addView(text);                                       // [3]
            text = new TextView(this);                                     // [1]
            text.setText(String.valueOf(entity.getAntiGravityHandling())); // [2]
            text.setGravity(Gravity.CENTER);                               // [2]
            row.addView(text);                                             // [3]
            text = new TextView(this);                             // [1]
            text.setText(String.valueOf(entity.getAirHandling())); // [2]
            text.setGravity(Gravity.CENTER);                       // [2]
            row.addView(text);                                     // [3]
            text = new TextView(this);                                // [1]
            text.setText(String.valueOf(entity.getOnRoadTraction())); // [2]
            text.setGravity(Gravity.CENTER);                          // [2]
            row.addView(text);                                        // [3]
            text = new TextView(this);                                 // [1]
            text.setText(String.valueOf(entity.getOffRoadTraction())); // [2]
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

        // Initialize data
        final ArrayList<StatHolders> allDrivers = assignData(getResources().openRawResource(R.raw.drivers));
        final ArrayList<StatHolders> allKarts = assignData(getResources().openRawResource(R.raw.karts));
        final ArrayList<StatHolders> allTires = assignData(getResources().openRawResource(R.raw.tires));
        final ArrayList<StatHolders> allGliders = assignData(getResources().openRawResource(R.raw.gliders));

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
    }
}