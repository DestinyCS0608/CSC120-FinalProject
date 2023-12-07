import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Player {
    private static final Random random = new Random();

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
        this.scanner = scanner;
        this.totalEarnings = 0.00;
       
        this.playerBobaFlavor = "";
        this.playerBobaSize = "";
        this.playerBobaTopping = "";
    }

    public double getTotalEarnings() {
        return totalEarnings;
    }

    public boolean serveBoba(Customer customer) {
        // Prompt the player for boba choices
        promptForBobaChoices();

        // Calculate total cost using stored choices
        double totalCost = calculateBobaCost(customer);

        // Display total cost and simulate payment
        System.out.println("Total cost for serving boba: $" + totalCost);
        System.out.println(".......Costumer is paying...");
        System.out.println("  Payment received. Transaction complete! ");

        // Determine satisfaction based on whether the order is fulfilled correctly
        boolean satisfied = determineSatisfaction(customer);

        if (satisfied) {
            handleSatisfiedCustomer(totalCost);
        } else {
            handleDissatisfiedCustomer(customer, totalCost);
        }

        return satisfied;
    }


    private void promptForBobaChoices() {
        System.out.print("Enter boba flavor: ");
        playerBobaFlavor = scanner.nextLine();
        System.out.print("Enter boba size: ");
        playerBobaSize = scanner.nextLine();
        System.out.print("Enter boba topping: ");
        playerBobaTopping = scanner.nextLine();
    }

    private double calculateBobaCost(Customer customer) {
        double flavorCost = bobaMenu.getFlavorPrice(playerBobaFlavor);
        double sizeCost = bobaMenu.getSizePrice(playerBobaSize);
        double toppingCost = bobaMenu.getToppingPrice(playerBobaTopping);
        return flavorCost + sizeCost + toppingCost;
    }

    private boolean determineSatisfaction(Customer customer) {
        // Check if the player's choices match the customer's preferences
        return playerBobaFlavor.equalsIgnoreCase(customer.getPreferredFlavor()) &&
               playerBobaSize.equalsIgnoreCase(customer.getPreferredSize()) &&
               playerBobaTopping.equalsIgnoreCase(customer.getPreferredTopping());
    }


    
    private void handleSatisfiedCustomer(double totalCost) {
        System.out.println("Customer is satisfied! You made the right boba!");
        double tipAmount = generateTip();
        if (tipAmount > 0) {
            System.out.println("Customer left a $" + tipAmount + " tip!");
        }
        totalEarnings += totalCost + tipAmount;
    }
 
    private void handleDissatisfiedCustomer(Customer customer, double totalCost) {
        System.out.println("Customer is not satisfied. You made the wrong boba. What should you do " + playerName + "? ");
        System.out.println("1. Refund");
        System.out.println("2. Remake the boba");
    
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
                    System.out.println("Invalid choice. No action taken.");
            }
        } else {
            System.out.println("Invalid input. ");
            scanner.next(); 
        }
        
       
        scanner.nextLine(); 
    }

    

    private void processRefund(double totalCost) {
        System.out.println("Refund processed. Apologies for the inconvenience.");
        totalEarnings =+ totalCost;
    }

    private void remakeBoba(Customer customer, double totalCost) {
        System.out.println("Remaking boba...");
    
        // Prompt the player for the corrected choices
        System.out.print("Enter corrected boba flavor: ");
        this.playerBobaFlavor = scanner.next();
        scanner.nextLine(); 
    
        System.out.print("Enter corrected boba size: ");
        this.playerBobaSize = scanner.next();
        scanner.nextLine(); 
    
        System.out.print("Enter corrected boba topping: ");
        this.playerBobaTopping = scanner.next();
        scanner.nextLine(); 
    
        // Display payment simulation
        System.out.println("~~~~~~ Payment received. Transaction complete! ~~~~~~");
        totalEarnings += totalCost;
    
        // Determine satisfaction based on whether the corrected choices match the customer's preferences
        boolean satisfied = determineSatisfaction(customer);
    
        if (satisfied) {
            handleSatisfiedCustomer(totalCost);
        } else {
            System.out.println("Customer is still dissatisfied. Better luck next time!");
        }
    }
    
        


    private double generateTip() {
        // Generate a random tip between $0 and $3
        double tipAmount = new Random().nextDouble() * 3.00;
    
        // Round the tip to two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("#.##");
        tipAmount = Double.parseDouble(decimalFormat.format(tipAmount));
    
        return tipAmount;
    }
}
