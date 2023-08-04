package org.idkproductions.mariokart8deluxefanapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;

public class RandomActivity extends AppCompatActivity {
    /**
     * Assign data from a particular .csv raw resources to variables to work with.
     * RandomActivity.java only needs the String name and Bitmap icon variables set.
     * This function uses the assignDataHelper function to assign Bitmap icon variable.
     *
     * @param inputStream raw .csv resource converted to an input stream
     * @return StatHolders Object with String name and Bitmap icon set
     */
    private ArrayList<StatHolders> assignData(InputStream inputStream) {
        // Initialize new Objects
        ArrayList<StatHolders> toReturn = new ArrayList<>();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
        BufferedReader reader = new BufferedReader(inputStreamReader);

        // Declare temp variable
        String line;

        // Read information from passed in CSV file
        try {
            // First line is meant for user readability; throw it away
            reader.readLine();

            // Actually read in data; loop through while next line isn't empty
            while((line = reader.readLine()) != null) {
                // Initialize new object containing all stats
                StatHolders entityToAdd = new StatHolders();

                // Assign name from read .csv file
                entityToAdd.setName(line.split(",")[1]);

                // Add Object after helperAssignData to ArrayList<StatHolders> to return
                toReturn.add(helperAssignData(entityToAdd));
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }

    /**
     * Helper method to assignData.
     * Takes provided StatHolders Object, and sets Bitmap variable per each StatHolders Object by
     * processing the name of the entity.
     *
     * @SuppressLint("DiscouragedApi") Suppresses IDE from discouraging use of getResource().getIdentifier()
     * @param toReturn StatHolders Object without Bitmap icon variable set
     * @return ... with Bitmap icon variable set
     */
    @SuppressLint("DiscouragedApi")
    private StatHolders helperAssignData(StatHolders toReturn) {
        // Initialize String name variable for easier to read code
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
     * @param savedInstanceState ????????????
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Draw XML layout on activity
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        // Initialize data
        ArrayList<StatHolders> allDrivers = assignData(getResources().openRawResource(R.raw.drivers));
        ArrayList<StatHolders> allKarts = assignData(getResources().openRawResource(R.raw.karts));
        ArrayList<StatHolders> allTires = assignData(getResources().openRawResource(R.raw.tires));
        ArrayList<StatHolders> allGliders = assignData(getResources().openRawResource(R.raw.gliders));

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

        // Initialize RNG
        final Random rand = new Random();

        // Perform first randomization
        randomize(allDrivers, allKarts, allTires, allGliders, driverIco, kartIco, tireIco, gliderIco, driverName, kartName, tireName, gliderName, rand);

        // Setup Button listeners
        backButton.setOnClickListener(v -> {
            startActivity(new Intent(RandomActivity.this, HomeActivity.class));
            finish();
        });
        rerollButton.setOnClickListener(v -> {
            randomize(allDrivers, allKarts, allTires, allGliders, driverIco, kartIco, tireIco, gliderIco, driverName, kartName, tireName, gliderName, rand);
        });
    }
}