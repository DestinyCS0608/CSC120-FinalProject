import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Player {

    private String playerName;
    private BobaMenu bobaMenu;
    private Scanner scanner;
    private double totalEarnings;

    // Additional fields to store player's choices
    private String playerBobaFlavor;
    private String playerBobaSize;
    private String playerBobaTopping;

    public Player(String playerName, BobaMenu bobaMenu, Scanner scanner) {
        this.playerName = playerName;
        this.bobaMenu = bobaMenu;
        this.totalEarnings = 0.00;
        this.scanner = scanner;
       
        this.playerBobaFlavor = "";
        this.playerBobaSize = "";
        this.playerBobaTopping = "";
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public boolean serveBoba(Customer customer) {
    
        System.out.print("Are you ready to make the order? (y/n): ");
        String readyToOrder = scanner.nextLine().toLowerCase();

        if (!readyToOrder.equals("y")) {
            System.out.println("\nOkay, take your time!");
            return false; // Player is not ready to make the order
        }

        // Clear the screen
        System.out.print("\033c");

        // Prompt the player for boba choices
        promptForBobaChoices();

        // Calculate total cost using stored choices
        double totalCost = calculateBobaCost(customer);

        // Display total cost and simulate payment
        System.out.println("\nBarista " + playerName+ ": Your total is $" + totalCost);
        String pay = slowWriting("\n... Costumer is paying ...\n");
        System.out.println("\nPayment Recieved! 🧾 💰 💵 💳 ");
        System.out.println("\nBarista " +playerName+ ": Great! Here's your receipt!");

        String taste = slowWriting("\n... Costumer is tasting boba ...");

        // Determine satisfaction based on whether the order is fulfilled correctly
        boolean satisfied = determineSatisfaction(customer);

        if (satisfied) {
            handleSatisfiedCustomer(totalCost);
        } else {
            handleDissatisfiedCustomer(customer, totalCost);
        }

        System.out.println("Total Profit: $" +totalEarnings);
        return satisfied;
    }

    private void promptForBobaChoices() {
        System.out.print("Enter boba flavor: ");
        playerBobaFlavor = scanner.nextLine();
        System.out.print("Enter boba size: ");
        playerBobaSize = scanner.nextLine();
        System.out.print("Enter boba topping: ");
        playerBobaTopping = scanner.nextLine();

        String make = slowWriting("\n.......... Making Boba .........\n" );
    }

    private double calculateBobaCost(Customer customer) {
        double flavorCost = bobaMenu.getFlavorPrice(playerBobaFlavor);
        double sizeCost = bobaMenu.getSizePrice(playerBobaSize);
        double toppingCost = bobaMenu.getToppingPrice(playerBobaTopping);
        return flavorCost + sizeCost + toppingCost;
    }

    private boolean determineSatisfaction(Customer customer) {
        return customer.isSatisfied(playerBobaFlavor, playerBobaSize, playerBobaTopping);
    }    

    private void handleSatisfiedCustomer(double totalCost) {
        System.out.println("\nCustomer is satisfied! You made the right boba!");
        double tipAmount = generateTip();
        if (tipAmount > 0) {
            System.out.println("\nCustomer left a $" + tipAmount + " tip!");
        }
        totalEarnings += totalCost + tipAmount;
    }
    
    private void handleDissatisfiedCustomer(Customer customer, double totalCost) {
        System.out.println("\nCustomer is not satisfied. You made the wrong boba. What should you do " + playerName + "? ");
        System.out.println("\n1. Offer a refund");
        System.out.println("2. Remake the boba order");
        System.out.print("Enter (1 or 2): ");
    
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
    
                switch (choice) {
                    case 1:
                        processRefund(totalCost);
                        break;
                    case 2:
                        remakeBoba(customer, totalCost);
                        break;
                    default:
                        System.out.println("\nInvalid choice. Please enter 1 or 2.");
                }
    
                break; // Exit the loop after processing the choice
            } else {
                System.out.println("\nInvalid input. Please enter a number (1 or 2).");
                scanner.next(); // Consume invalid input
            }
        }
    
        scanner.nextLine(); // Consume the newline character
    }
    

    private void processRefund(double totalCost) {
        String refund = slowWriting("\n....Processing Refund....\n" );
        System.out.println("\nBarista" +playerName+ ": 'Refund processed. Apologies for the inconvenience!'");
        totalEarnings += totalCost;
    }

    private void remakeBoba(Customer customer, double totalCost) {

        //System.out.println("Okay, here's the costumer's order again: "+ costumer);

        // Clear the screen
        System.out.print("\033c");

        // Prompt the player for the corrected choices
        System.out.print("\nEnter corrected boba flavor: ");
        this.playerBobaFlavor = scanner.next();
        scanner.nextLine(); 
    
        System.out.print("Enter corrected boba size: ");
        this.playerBobaSize = scanner.next();
        scanner.nextLine(); 
    
        System.out.print("Enter corrected boba topping: ");
        this.playerBobaTopping = scanner.next();
        scanner.nextLine(); 
    
        // Display payment simulation
        System.out.println("\n~~~~~~ Payment received. Transaction complete! ~~~~~~");
        totalEarnings += totalCost;
    
        // Determine satisfaction based on whether the corrected choices match the customer's preferences
        boolean satisfied = determineSatisfaction(customer);
    
        if (satisfied) {
            handleSatisfiedCustomer(totalCost);
        } else {
            System.out.println("\nCustomer is now angry, and demands a refund! 🤬");
            processRefund(totalCost);
        }
    }
    

    private double generateTip() {
        // Generate a random tip between $0 and $3
        double tipAmount = new Random().nextDouble() * 3.00;
    
        // Round the tip to two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        tipAmount = Double.parseDouble(decimalFormat.format(tipAmount));
    
        return tipAmount;
    }

    private String slowWriting(String text){
        for(int i = 0; i < text.length(); i++) {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 100) {
            }
            System.out.print(text.charAt(i));
        }
        return text;
    }
}
