package com.margaret;

import java.util.Scanner;

/**
 * Created by Margaret on 10/11/2015.
 */
public class Drink {

    // data attributes
    private String bev;
    private Integer daySales;
    private Double cost;
    private Double price;

    // constructor called using date from the input file "coffee.txt"
    public Drink(String bev, Double cost, Double price) {
        this.bev = bev;
        this.daySales = 0;  // set daySales to 0 because the input file does not contain this piece of information
        this.cost = cost;
        this.price = price;
    }

    // getters
    public String getBev() {
        return bev;
    }

    public Integer getDaySales() {
        return daySales;
    }

    public Double getCost() {
        return cost;
    }

    public Double getPrice() {
        return price;
    }

    // setters
    public void setBev(String bev) {
        this.bev = bev;
    }

    public void setDaySales (Integer daySales){
        this.daySales = daySales;
    }

    public void setDayCount(Double cost) {
        this.cost = cost;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    // this method name is leftover from the original coffee shop program, it takes in a Scanner from main, gets and tests user input and adds the information to the existing Drink objects in salesToday; it does this as through calls that are incremented through the ArrayList in main

    public Integer numVal(Scanner x) {

        System.out.println("How many cups of " + this.bev + " did you sell today?");

        while (true) {
            String numToTestStr;
            Integer salesFig;

            // get a legal integer that is greater than 0 for sales of a given drink
            try {
                numToTestStr = x.nextLine();
                salesFig = Integer.parseInt(numToTestStr);
                if (salesFig >= 0) {
                    return salesFig; // in Main this value will be written into the daySales var of the appropriate Drink object
                }
                else {
                    System.out.println("That number is less than 0. Please try again.");
                }
            }
            catch (Exception badDouble) {
                System.out.println("You didn't enter a valid number of " + this.bev + " sold. Please enter a whole, non-decimal number greater than 0.");
            }
        }
    } // end doubVal
}
