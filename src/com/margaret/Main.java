package com.margaret;

// For this program I started with my original coffee program from the previous assignment - it was not object oriented, but it helped

import java.util.*;
import java.io.*;

public class Main {

    public static void main(String[] args) {

        // scanner for taking using input
        Scanner z = new Scanner(System.in);

        System.out.println("Coffee Shop Sales Calculator Program");

        // used with test data
//        double totalSales = 0;

        // test data
//        Drink cap = new Drink("Capuccino", 1.56, 3.25);
//        Drink coff = new Drink("Coffee", .56, 1.55);
//        Drink hotChoc = new Drink("HotChocolate", 1.54, 3.50);
//        Drink tea = new Drink("Tea", .98, 2.25);

        // create an ArrayList of Drinks that will eventually have full data for all Drink objects in a given day
        ArrayList<Drink> salesToday = new ArrayList<Drink>();

        // call the accessFile method with an empty ArrayList salesToday
        accessFile(salesToday);

        // now that salesToday has all the file data, add the user data to each object in the ArrayList
        for (Drink myDrink : salesToday) {
            myDrink.setDaySales(myDrink.numVal(z));
        }

        // finally calculate the totals for the day now that salesToday has all information for all Drink objects
        calculateSales (salesToday);

    } // end main fn

    // this program takes an empty ArrayList and fills it with data from the input file "coffee.txt"
    public static void accessFile (ArrayList<Drink> salesToday){

        // catch file IO exceptions
        try {
            // create a buffered file reader
            FileReader reader = new FileReader("coffee.txt");
            BufferedReader bRead = new BufferedReader(reader);

            // read the first line
            String line = bRead.readLine();
            // as long as the first line and any subsequent lines are not null, process them
            while (line != null) {
                Drink newDrink = makeObject(line);  // create an object from the line of text
                salesToday.add(newDrink);  // add the object to the ArrayList
                line = bRead.readLine();  // get next line
            } // end of while
            bRead.close();  // when you get to the end of the file, close it
            }
        // if there is a problem with file IO output an error message
        catch (IOException ioe) {
            System.out.println("A file i/o exception was encountered.");
            ioe.printStackTrace();
            System.out.println(ioe.toString());
        }
    } // end accessFile

    // this method takes a line from the input file "coffee.txt", parses off the information and feeds it to a constructor call for a new object, which is the return statement
    public static Drink makeObject (String fileLine){

        // declare local variables for all the input and object variables
        String bev;
        String costStr;
        Double cost;
        String priceStr;
        Double price;
        int start = 0;
        String rest;

        bev = fileLine.substring(start, fileLine.indexOf(';')); // parse off the beverage name

        rest = fileLine.substring(fileLine.indexOf(';')); // create a string without bev
        costStr = rest.substring(1, rest.indexOf(';',1)); // parse off the cost
        cost = Double.parseDouble(costStr); // and cast the cost as a Double

        priceStr = rest.substring(rest.indexOf(';',1)+1); // parse off the price
        price = Double.parseDouble(priceStr); // cast the price as a double
        return new Drink(bev,cost,price); // return a call to the Drink constructor with the paresed values
    }

    // this Method takes a full ArrayList with all data and calculates sales for each drink for the day and the total sales for the day, then writes all that data to an output file
    private static void calculateSales(ArrayList<Drink> salesToday) {

        String bev;
        Integer daySales;
        Double cost;
        Double price;
        Integer totSold = 0;
        Double totExp = 0.00;
        Double totRev = 0.00;
        Double totProf = 0.00;

        try {
            FileWriter writer = new FileWriter("sales.txt");
            BufferedWriter bWrite = new BufferedWriter(writer);

            for (Drink myDrink : salesToday){
                bev = myDrink.getBev();
                daySales = myDrink.getDaySales();
                totSold += daySales;
                cost = myDrink.getCost();
                price = myDrink.getPrice();
                Double exp = daySales*cost;
                totExp += exp;
                Double rev = daySales*price;
                totRev += rev;
                Double prof = rev - exp;
                totProf += prof;

                bWrite.write("Today's sales of " + bev + ": Sold " + daySales + ", Expenses $" + exp + ", Revenue $" + rev + ", Profit $" + prof + "\n");

            }
            bWrite.write ("Total: Number of drinks sold " + totSold + ". Total expense $" + totExp + ". Total Revenue $" + totRev + ". And the total profit for the day is $" + totProf);
            bWrite.close();
        }
        catch (IOException ioe) {
            System.out.println("A file i/o exception was encountered.");
            ioe.printStackTrace();
            System.out.println(ioe.toString());
        }
    } // end Calculate Sales fn

} // end class main
