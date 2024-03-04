package org.idkproductions.mariokart8deluxefanapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

/**
 * Class representing each Mario Kart 8 Deluxe entity in terms of its stats. Drivers, karts, tires,
 * and gliders all have the same variables so no need to Interface.
 */
public class StatHolders {
    // Initialize 3/16 constant Mario Kart uses for stat calculations
    private static final float MKCONSTANT = 3f / 16f;

    // Declare stats each entity contains
    private final String name;
    private final int weight;
    private final int acceleration;
    private final int onRoadTraction;
    private final int offRoadTraction;
    private final int miniTurbo;
    private final int groundSpeed;
    private final int waterSpeed;
    private final int antiGravitySpeed;
    private final int airSpeed;
    private final int groundHandling;
    private final int waterHandling;
    private final int antiGravityHandling;
    private final int airHandling;
    private final int invincibility;
    private final Bitmap icon;
    private final float inGameSpeed;
    private final float inGameAcceleration;
    private final float inGameWeight;
    private final float inGameHandling;
    private final float inGameTraction;
    private final float inGameStatTotal;
    private final int statTotal;

     // Each in game stat only draws from one stat "behind the scenes" IE in game speed is ground
     // speed.

     // Mathematically, observe speed. Let [A] be driver's ground speed, [B] be kart's ground speed,
     // [C] be tires' ground speed, [D] be glider's ground speed. In game speed is given as follows
     // in units of full stat bar sections (6 sections per in game stat):
     // ([A] + [B] + [C] + [D] + 3) / 4 == [A]/4 + [B]/4 + [C]/4 + [D]/4 + 3/4
     //                                 == ([A]/4 + 3/16) + ([B]/4 + 3/16) + ([C]/4 + 3/16) + ([D]/4 + 3/16)
     // Hence we can say for a generic entity's speed [X], it will contribute ([X]/4 + 3/16) to the
     // setup's speed.

     // Total yellow bars are summed for inGameStatTotal

    /**
     * Full constructor given the following inputs:
     * <p>
     * - Name
     * - Weight
     * - Acceleration
     * - On-Road Traction
     * - Off-Road Traction
     * - Mini-Turbo
     * - Ground Speed
     * - Water Speed
     * - Anti-Gravity Speed
     * - Air Speed
     * - Ground Handling
     * - Water Handling
     * - Anti-Gravity Handling
     * - Air Handling
     * - Invincibility
     * <p>
     * No helper methods are used so all non-static instance variables are allowed to be final.
     *
     * @param name name of the entity
     * @param weight "behind the scenes" weight of the entity
     * @param acceleration "behind the scenes" acceleration of the entity
     * @param onRoadTraction "behind the scenes" on-road traction of the entity
     * @param offRoadTraction "behind the scenes" off-road traction of the entity
     * @param miniTurbo "behind the scenes" mini-turbo of the entity
     * @param groundSpeed "behind the scenes" ground speed of the entity
     * @param waterSpeed "behind the scenes" water speed of the entity
     * @param antiGravitySpeed "behind the scenes" anti-gravity speed of the entity
     * @param airSpeed "behind the scenes" air speed of the entity
     * @param groundHandling "behind the scenes" ground handling of the entity
     * @param waterHandling "behind the scenes" water handling of the entity
     * @param antiGravityHandling "behind the scenes" anti-gravity handling of the entity
     * @param airHandling "behind the scenes" air handling of the entity
     * @param invincibility "behind the scenes" invincibility of the entity
     * @param context context of the class storing all long-term variables and having access to "R"
     *                resources
     */
    @SuppressLint("DiscouragedApi")
    public StatHolders(String name, int weight, int acceleration, int onRoadTraction, int offRoadTraction, int miniTurbo, int groundSpeed, int waterSpeed, int antiGravitySpeed, int airSpeed, int groundHandling, int waterHandling, int antiGravityHandling, int airHandling, int invincibility, Application context) {
        // Set all constructor variables
        this.name = name;
        this.weight = weight;
        this.acceleration = acceleration;
        this.onRoadTraction = onRoadTraction;
        this.offRoadTraction = offRoadTraction;
        this.miniTurbo = miniTurbo;
        this.groundSpeed = groundSpeed;
        this.waterSpeed = waterSpeed;
        this.antiGravitySpeed = antiGravitySpeed;
        this.airSpeed = airSpeed;
        this.groundHandling = groundHandling;
        this.waterHandling = waterHandling;
        this.antiGravityHandling = antiGravityHandling;
        this.airHandling = airHandling;
        this.invincibility = invincibility;

        // Set stat total
        this.statTotal = this.weight + this.acceleration + this.onRoadTraction + this.offRoadTraction + this.miniTurbo + this.groundSpeed + this.waterSpeed + this.antiGravitySpeed + this.airSpeed + this.groundHandling + this.waterHandling + this.antiGravityHandling + this.airHandling + this.invincibility;

        // Set in-game stats
        // Speed, Acceleration, Weight, Handling, & Traction
        this.inGameSpeed = ((float) this.groundSpeed / 4f) + MKCONSTANT;
        this.inGameAcceleration = ((float) this.acceleration / 4f) + MKCONSTANT;
        this.inGameWeight = ((float) this.weight / 4f) + MKCONSTANT;
        this.inGameHandling = ((float) this.groundHandling / 4f) + MKCONSTANT;
        this.inGameTraction = ((float) this.offRoadTraction / 4f) + MKCONSTANT;
        // Stat total
        this.inGameStatTotal = this.inGameSpeed + this.inGameAcceleration + this.inGameWeight + this.inGameHandling + this.inGameTraction;

        // Set bitmap
        String fileName = this.name.replaceAll(" ","_").replaceAll("[-,().]","").toLowerCase();
        // Handle exception due to R.raw._____ naming rule
        // So far, just the 300 SL Roadster kart since it starts with a number
        if(fileName.equals("300_sl_roadster")) {
            fileName = "sl_300_roadster";
        }
        // Set icon
        this.icon = BitmapFactory.decodeStream(context.getResources().openRawResource(context.getResources().getIdentifier(fileName, "raw", context.getPackageName())));
    }

    // Setup getter methods for all variables
    public int getAcceleration() {
        return acceleration;
    }

    public int getAirHandling() {
        return airHandling;
    }

    public int getAirSpeed() {
        return airSpeed;
    }

    public int getAntiGravityHandling() {
        return antiGravityHandling;
    }

    public int getAntiGravitySpeed() {
        return antiGravitySpeed;
    }

    public int getGroundHandling() {
        return groundHandling;
    }

    public int getGroundSpeed() {
        return groundSpeed;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public int getInvincibility() {
        return invincibility;
    }

    public float getInGameAcceleration() {
        return inGameAcceleration;
    }

    public float getInGameHandling() {
        return inGameHandling;
    }

    public float getInGameStatTotal() {
        return inGameStatTotal;
    }

    public float getInGameSpeed() {
        return inGameSpeed;
    }

    public float getInGameTraction() {
        return inGameTraction;
    }

    public float getInGameWeight() {
        return inGameWeight;
    }

    public int getMiniTurbo() {
        return miniTurbo;
    }

    public String getName() {
        return name;
    }

    public int getOffRoadTraction() {
        return offRoadTraction;
    }

    public int getOnRoadTraction() {
        return onRoadTraction;
    }

    public int getStatTotal() {
        return statTotal;
    }

    public int getWaterHandling() {
        return waterHandling;
    }

    public int getWaterSpeed() {
        return waterSpeed;
    }

    public int getWeight() {
        return weight;
    }
}
