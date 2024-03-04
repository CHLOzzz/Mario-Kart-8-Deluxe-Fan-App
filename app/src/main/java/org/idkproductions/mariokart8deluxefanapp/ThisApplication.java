package org.idkproductions.mariokart8deluxefanapp;

import android.app.Application;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class ThisApplication extends Application {
    private ArrayList<StatHolders> allDrivers;
    private ArrayList<StatHolders> allKarts;
    private ArrayList<StatHolders> allTires;
    private ArrayList<StatHolders> allGliders;

    /**
     * Helper method to assign CSV file data to an ArrayList<StatHolders> variable.
     *
     * @param inputStream stream of data from a given .csv file
     * @return fully occupied ArrayList<StatHolders>
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
                // Separate the line String into an array
                lineArray = line.split(",");

                // Construct StatsHolder Object with all data
                StatHolders entityToAdd = new StatHolders(lineArray[1], Integer.parseInt(lineArray[2]), Integer.parseInt(lineArray[3]), Integer.parseInt(lineArray[4]), Integer.parseInt(lineArray[5]), Integer.parseInt(lineArray[6]), Integer.parseInt(lineArray[7]), Integer.parseInt(lineArray[8]), Integer.parseInt(lineArray[9]), Integer.parseInt(lineArray[10]), Integer.parseInt(lineArray[11]), Integer.parseInt(lineArray[12]), Integer.parseInt(lineArray[13]), Integer.parseInt(lineArray[14]), Integer.parseInt(lineArray[15]), this);

                // Add Object after helperAssignData to ArrayList to return
                toReturn.add(entityToAdd);
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return toReturn;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        this.allDrivers = assignData(getResources().openRawResource(R.raw.drivers));
        this.allKarts = assignData(getResources().openRawResource(R.raw.karts));
        this.allTires = assignData(getResources().openRawResource(R.raw.tires));
        this.allGliders = assignData(getResources().openRawResource(R.raw.gliders));
    }

    public ArrayList<StatHolders> getAllDrivers() {
        return this.allDrivers;
    }

    public ArrayList<StatHolders> getAllKarts() {
        return allKarts;
    }

    public ArrayList<StatHolders> getAllGliders() {
        return allGliders;
    }

    public ArrayList<StatHolders> getAllTires() {
        return allTires;
    }
}
