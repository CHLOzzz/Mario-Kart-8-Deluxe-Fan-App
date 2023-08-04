package org.idkproductions.mariokart8deluxefanapp;

import android.graphics.Bitmap;

public class StatHolders {
    // Declare stats each entity contains
    private String name;
    private int weight;
    private int acceleration;
    private int onRoadTraction;
    private int offRoadTraction;
    private int miniTurbo;
    private int groundSpeed;
    private int waterSpeed;
    private int antiGravitySpeed;
    private int airSpeed;
    private int groundHandling;
    private int waterHandling;
    private int antiGravityHandling;
    private int airHandling;
    private int invincibility;
    private Bitmap icon;
    private float inGameSpeed;
    private float inGameAcceleration;
    private float inGameWeight;
    private float inGameHandling;
    private float inGameTraction;
    private float inGameStatTotal;
    private int statTotal;

    /**
     * Special setter method used exclusively by StatsActivity to set in game statistics, otherwise
     * no point constructing data.
     * Each in game stat only draws from one stat "behind the scenes" IE in game speed is ground
     * speed.
     * <p>
     * Mathematically, observe speed. Let [A] be driver's ground speed, [B] be kart's ground speed,
     * [C] be tires' ground speed, [D] be glider's ground speed. In game speed is given as follows
     * in units of full stat bar sections (6 sections per in game stat):
     * ([A] + [B] + [C] + [D] + 3) / 4 == [A]/4 + [B]/4 + [C]/4 + [D]/4 + 3/4
     *                                 == ([A]/4 + 3/16) + ([B]/4 + 3/16) + ([C]/4 + 3/16) + ([D]/4 + 3/16)
     * Hence we can say for a generic entity's speed [X], it will contribute ([X]/4 + 3/16) to the
     * setup's speed.
     * <p>
     * Total yellow bars are summed for inGameStatTotal
     */
    public void setInGameStats() {
        // Define constant for 3/16
        // Java does not handle this with ease WRT the programmer for real for real...
        final float constant = (float) 3 / (float) 16;

        // Set in game stats
        // Speed
        inGameSpeed = (float) groundSpeed / (float) 4;
        inGameSpeed = inGameSpeed + constant;
        inGameStatTotal = inGameSpeed;
        // Acceleration
        inGameAcceleration = (float) acceleration / (float) 4;
        inGameAcceleration = inGameAcceleration + constant;
        inGameStatTotal = inGameStatTotal + inGameAcceleration;
        // Weight
        inGameWeight = (float) weight / (float) 4;
        inGameWeight = inGameWeight + constant;
        inGameStatTotal = inGameStatTotal + inGameWeight;
        // Handling
        inGameHandling = (float) groundHandling / (float) 4;
        inGameHandling = inGameHandling + constant;
        inGameStatTotal = inGameStatTotal + inGameHandling;
        // Traction
        inGameTraction = (float) offRoadTraction / (float) 4;
        inGameTraction = inGameTraction + constant;
        inGameStatTotal = inGameStatTotal + inGameTraction;
    }

    /**
     * Special setter method used exclusively by StatsActivity to set true stat total, otherwise no
     * point constructing data.
     * Simply adding all stats into a single int variable.
     */
    public void setStatTotal() {
        statTotal = weight + acceleration + onRoadTraction + offRoadTraction + miniTurbo;
        statTotal = statTotal + groundSpeed + waterSpeed + antiGravitySpeed + airSpeed;
        statTotal = statTotal + groundHandling + waterHandling + antiGravityHandling + airHandling;
        statTotal = statTotal + invincibility;
    }

    // Setup getter & setter methods for all variables
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(int acceleration) {
        this.acceleration = acceleration;
    }

    public int getOnRoadTraction() {
        return onRoadTraction;
    }

    public void setOnRoadTraction(int onRoadTraction) {
        this.onRoadTraction = onRoadTraction;
    }

    public int getOffRoadTraction() {
        return offRoadTraction;
    }

    public void setOffRoadTraction(int offRoadTraction) {
        this.offRoadTraction = offRoadTraction;
    }

    public int getMiniTurbo() {
        return miniTurbo;
    }

    public void setMiniTurbo(int miniTurbo) {
        this.miniTurbo = miniTurbo;
    }

    public int getGroundSpeed() {
        return groundSpeed;
    }

    public void setGroundSpeed(int groundSpeed) {
        this.groundSpeed = groundSpeed;
    }

    public int getWaterSpeed() {
        return waterSpeed;
    }

    public void setWaterSpeed(int waterSpeed) {
        this.waterSpeed = waterSpeed;
    }

    public int getAntiGravitySpeed() {
        return antiGravitySpeed;
    }

    public void setAntiGravitySpeed(int antiGravitySpeed) {
        this.antiGravitySpeed = antiGravitySpeed;
    }

    public int getAirSpeed() {
        return airSpeed;
    }

    public void setAirSpeed(int airSpeed) {
        this.airSpeed = airSpeed;
    }

    public int getGroundHandling() {
        return groundHandling;
    }

    public void setGroundHandling(int groundHandling) {
        this.groundHandling = groundHandling;
    }

    public int getWaterHandling() {
        return waterHandling;
    }

    public void setWaterHandling(int waterHandling) {
        this.waterHandling = waterHandling;
    }

    public int getAntiGravityHandling() {
        return antiGravityHandling;
    }

    public void setAntiGravityHandling(int antiGravityHandling) {
        this.antiGravityHandling = antiGravityHandling;
    }

    public int getAirHandling() {
        return airHandling;
    }

    public void setAirHandling(int airHandling) {
        this.airHandling = airHandling;
    }

    public int getInvincibility() {
        return invincibility;
    }

    public void setInvincibility(int invincibility) {
        this.invincibility = invincibility;
    }

    public Bitmap getIcon() {
        return icon;
    }

    public void setIcon(Bitmap icon) {
        this.icon = icon;
    }

    public float getInGameSpeed() {
        return inGameSpeed;
    }

    public float getInGameAcceleration() {
        return inGameAcceleration;
    }

    public float getInGameWeight() {
        return inGameWeight;
    }

    public float getInGameHandling() {
        return inGameHandling;
    }

    public float getInGameTraction() {
        return inGameTraction;
    }

    public float getInGameStatTotal() {
        return inGameStatTotal;
    }

    public int getStatTotal() {
        return statTotal;
    }
}
