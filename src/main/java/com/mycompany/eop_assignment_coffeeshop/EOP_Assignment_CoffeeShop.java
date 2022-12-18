/*
 * Group: I Love Luqman
TOPIC: COFFEE SHOP MANAGEMENT SYSTEM
 */
package com.mycompany.eop_assignment_coffeeshop;
import java.util.Scanner;
import java.io.*;
public class EOP_Assignment_CoffeeShop {
static String beverage[][] = new String[2][999];  //Beverage Name and Price.
static String order[][] = new String[3][999];   //customer name, order contents and total price
//static String customer[]; //store name of customer (depreciated)
static String staff[] = {"", "Luqman", "Syahir", "Muazzam", "Mirza", "Khaleel"}; //store name of staff
static int drinkID = 1;
static int drinkOrderStore = 0;
static String drinkOrderString = "";
static String name, price, userInput;
static String tempBeverageName;
static int tempBeveragePrice;
static int tempTotalPrice = 0;
static int orderID = 1;
    public static void main(String[] args) throws IOException{
        int x, z = 0;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Your Staff ID for Authentication:");
        int staffID = input.nextInt();
        while(z != 1){
            if(staff[staffID] != null){
                z = 1;
                System.out.println("Authentication Complete! You are: "+staff[staffID] );                    
            }
            if(z == 0){
                System.out.println("Authentication Failed! Please try again and input your ID again!");  
                z = 2;
            }
        }
        System.out.println("Coffee Shop Menu Management System. What would you like to do today?");
        do{
            System.out.println("1 = Add Beverage | 2 = Remove Beverage | 3 = Edit Beverage | 4 = Search Beverage | \n 5 = Make An Order | 6 = Display Order | 7 = Print Order as Text File| 8 = Exit Program");
                do{
                    System.out.println("Insert a value between 1 to 8.");
                    x = input.nextInt();
                    input.nextLine();
                }while(!(x >= 1 && x <= 8));
                switch(x){
                    case 1: System.out.println("Provide name of beverage.");
                            name = input.nextLine(); 
                            System.out.println("Provide price of beverage.");
                            price = input.nextLine();
                            addBeverage(name, price);
                            break;
                    case 2: System.out.println("Provide ID or Name of beverage to delete:");
                            userInput = input.nextLine();
                            if (Character.isDigit(userInput.charAt(0)) == true){
                                removeBeverage(Integer.parseInt(userInput));
                             }
                            else{
                                removeBeverage(userInput);
                             }
                            break;
                    case 3: System.out.println("Provide ID or Name of beverage to edit:");
                            userInput = input.nextLine();
                            if (Character.isDigit(userInput.charAt(0)) == true){
                                editBeverage(Integer.parseInt(userInput));
                            }
                            else{
                                editBeverage(userInput);
                            }
                            break;
                    case 4: System.out.println("Provide ID or Name of beverage to search:");
                            userInput = input.nextLine();
                            if (Character.isDigit(userInput.charAt(0)) == true){
                                searchBeverage(Integer.parseInt(userInput));
                            }
                            else{
                                searchBeverage(userInput);
                            }
                            break;
                    case 5: System.out.println("Insert the name of customer, then the beverage ID. Press Enter for every drink. \n When you're done, end with -1");
                            String custName = input.nextLine();
                            /*do{ 
                                drinkOrderStore = input.nextInt();
                                drinkOrderString += Integer.toString(drinkOrderStore);                               
                            }while(drinkOrderStore != -1);*/
                            makeOrder(custName);
                            break;
                    case 6: System.out.println("Provide Order ID to display:");
                            checkOrder(input.nextInt());
                            break;
                    case 7: System.out.println("Provide Order ID to print receipt into file");
                            printOrder(input.nextInt());
                            break;
        }
    }while(!(x==8));
    System.out.println("Program is exited by user");
}
    static void addBeverage(String name, String price){
        beverage[0][drinkID] = name;
        beverage[1][drinkID] = price;
        System.out.println("Drink is given an ID. ID of drink is:" + drinkID);
        drinkID++;
        
    }
    static void removeBeverage(int ID){
        System.out.println("Drink to be removed is " + beverage[0][ID]);
        beverage[0][ID] = null;
        beverage[1][ID] = null;
        System.out.println("Drink successfully removed");        

    }
    static void removeBeverage(String name){
        int ID = searchBeverage(name);
        System.out.println("Drink to be removed is " + beverage[0][ID]);
        beverage[0][ID] = null;
        beverage[1][ID] = null;
        System.out.println("Drink successfully removed");         
        
    }    
    static int searchBeverage(String name){
        int i;
        System.out.println("Searching beverage with specified name...");
        for(i = 0; i < 999; i++){
            if(beverage[0][i] != null){
                if(beverage[0][i].equals(name) == true){
                System.out.println("Drink found! Details:");
                System.out.println("Name:" + beverage[0][i]);
                System.out.println("Price:" + beverage[1][i]);
                break;  
            }
        }
        }
    return i;
    }
    static int searchBeverage(int ID){ //overloading searchBeverage
        Scanner input = new Scanner(System.in);
        System.out.println("Searching beverage with specified ID...");
        while(beverage[0][ID] == null){
        System.out.println("Beverage does not exist. Try again:");
        ID = input.nextInt();
        }
        System.out.println("Drink found! Details:");
        System.out.println("Name:" + beverage[0][ID]);
        System.out.println("Price:" + beverage[1][ID]);
        tempBeverageName = beverage[0][ID];
        tempBeveragePrice = Integer.parseInt(beverage[1][ID]);
        return ID;
    }
    static void makeOrder(String custName) { //make an order
        tempTotalPrice = 0;
        Scanner input = new Scanner(System.in);
        order[0][orderID] = custName;
        int drinkInput = 0;
        order[1][orderID] = " ";
        drinkInput = input.nextInt();
        while(drinkInput != -1){
            order[1][orderID] += ("ID: " + searchBeverage(drinkInput) + " Beverage: " + tempBeverageName);
            tempTotalPrice += tempBeveragePrice;
            drinkInput = input.nextInt();
        }
        order[2][orderID] = Integer.toString(tempTotalPrice);
        System.out.println("Order successfully stored and can be printed into a file. Order ID is"+orderID);   
        orderID++;        
    }
    static String checkOrder(int ID){
        System.out.println("Name of customer:"+ order[0][ID]);
        System.out.println("Order contents:");
        System.out.println(order[1][ID]);
        System.out.println("Total price = RM" + order[2][ID]);
        return "Name of customer:"+ order[0][ID]+"\n"+"Order contents: "+order[1][ID]+"\n"+"Total price = RM" + order[2][ID];
    }
            
    static void printOrder(int orderIDPrint) throws IOException{ //print order using File I/O
        try{
        String nameOfFile = "Receipt for Order #"+Integer.toString(orderIDPrint);
        BufferedWriter file = new BufferedWriter(new FileWriter(nameOfFile));
        file.write(checkOrder(orderIDPrint));
        file.close();
        System.out.println(nameOfFile+" successfully printed!");
        }
        catch(IOException e) {
        e.printStackTrace();
        }
  }
    static void editBeverage(int ID){
        Scanner input = new Scanner(System.in);
        System.out.println("The beverage that you wish to edit is:" + beverage[0][ID]);        
        System.out.println("Insert new name of beverage:");
        beverage[0][ID] = input.nextLine();
        System.out.println("Insert new price of beverage:");        
        beverage[1][ID] = input.nextLine();
        System.out.println("Your edit is a success!");         
    }
    static void editBeverage(String name){
        Scanner input = new Scanner(System.in);
        int ID = searchBeverage(name);
        System.out.println("Insert new name of beverage:");
        beverage[0][ID] = input.nextLine();
        System.out.println("Insert new price of beverage:");        
        beverage[1][ID] = input.nextLine();
        System.out.println("Your edit is a success!");        
    }
}