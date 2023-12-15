import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.Scanner;

public class Player {

    private String playerName;
    private BobaMenu bobaMenu;
    private Scanner scanner;
    private BigDecimal totalEarnings;

    // Additional fields to store player's choices
    private String playerBobaFlavor;
    private String playerBobaSize;
    private String playerBobaTopping;

    public Player(String playerName, BobaMenu bobaMenu, Scanner scanner) {
        this.playerName = playerName;
        this.bobaMenu = bobaMenu;
        this.totalEarnings = BigDecimal.ZERO;
        this.scanner = scanner;
       
        this.playerBobaFlavor = "";
        this.playerBobaSize = "";
        this.playerBobaTopping = "";
    }

    public BigDecimal getTotalEarnings() {
        return totalEarnings;
    }

    public boolean serveBoba(Customer customer) {
    
        System.out.println("\u001B[3mAre you ready to make the order? (y/n): \u001B[0m"); 
        String readyToOrder = scanner.nextLine().toLowerCase();

        if (!readyToOrder.equals("y")) {
            System.out.println("\n\u001B[3mOkay, the customer will repeat their order.\u001B[0m"); 
            System.out.println(customer); // Display the customer's order again
    
            // Prompt the player again
            System.out.println("\n\u001B[3mAre you ready to make the order now? (y/n):\u001B[0m"); 

            readyToOrder = scanner.nextLine().toLowerCase();
    
            if (!readyToOrder.equals("y")) {
                System.out.println("\n\u001B[3mOkay, let's give this customer to your coworker barista.\u001B[0m"); 
                return false; // Player is not ready to make the order, so move on to the next customer
            }
        }

        // Clear the screen
        System.out.print("\033c");

        // Prompt the player for boba choices
        promptForBobaChoices();
        slowWriting("\n.......... Making Boba .........\n");

        // Calculate total cost using stored choices
        double totalCost = calculateBobaCost(customer);

        // Display total cost and simulate payment
        System.out.println("\nBarista " + playerName+ ": 'Your total is $" + totalCost + "'");
        slowWriting("\n... Costumer is paying ...\n");
        System.out.println("\nPayment Recieved! 💰 💵 💳 ");
        System.out.println("\nBarista " +playerName+ ": Here's your receipt! 🧾");

        slowWriting("\n... Costumer is tasting boba ...");

        // Determine satisfaction based on whether the order is fulfilled correctly
        boolean satisfied = determineSatisfaction(customer);

        if (satisfied) {
            handleSatisfiedCustomer(totalCost);
        } else {
            customer.complain(playerBobaFlavor, playerBobaSize, playerBobaTopping);
            handleDissatisfiedCustomer(customer, totalCost);
        }
        System.out.println("\033[1mTotal Shop Profit: $" + totalEarnings + "\033[0m"); 
        System.out.println("\033[1mGoal to Reach: $ 100\033[0m");  
 
        System.out.println("-----------------------------------");
        return satisfied;
    }

    private boolean promptForBobaChoices() {
        boolean bobaReady = false;
    
        do {
            System.out.print("Enter boba flavor: ");
            playerBobaFlavor = scanner.nextLine().toLowerCase();
            System.out.print("Enter boba size: ");
            playerBobaSize = scanner.nextLine().toLowerCase();
            System.out.print("Enter boba topping: ");
            playerBobaTopping = scanner.nextLine().toLowerCase();
    
            // Ask the user if they are satisfied
            System.out.print("\n\u001B[3mAre you satisfied with your choices? (y/n):\u001B[0m");
            String readyToMake = scanner.nextLine().toLowerCase();
    
            if (readyToMake.equals("y")) {
                System.out.println("\n\u001B[3mGreat, let's make the drink!\u001B[0m");
                bobaReady = true; // Exit the loop if the user is satisfied
            } else {
                System.out.println("\n\u001B[3mOkay, let's try again!\n\u001B[0m");
            }
        } while (!bobaReady);
    
        return true; // The user is satisfied and ready to make the drink
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
        System.out.println("\nCustomer is satisfied! You made the right boba!\n");
        double tipAmount = generateTip();
        if (tipAmount > 0) {
            System.out.println("Customer left a $" + tipAmount + " tip!");
            totalEarnings = totalEarnings.add(BigDecimal.valueOf(totalCost + tipAmount));
        }
    }
    
    private void handleDissatisfiedCustomer(Customer customer, double totalCost) {
        System.out.println("\nCustomer is not satisfied. You made the wrong boba! What should you do " + playerName + "? ");
        System.out.println("\n1. Offer a refund");
        System.out.println("2. Remake the boba order");
        System.out.print("\nEnter (1 or 2): ");
    
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
    
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
        slowWriting("\n....Processing Refund....\n" );
        System.out.println("\nBarista " +playerName+ ": 'Refund processed. Apologies for the inconvenience!'");
        totalEarnings = totalEarnings.add(BigDecimal.valueOf(totalCost));
    }

    private boolean remakeBoba(Customer customer, double totalCost) {  

        System.out.println("\nOkay, here comes the customer's order again:");
        System.out.println(customer);

        System.out.print("\nAre you ready to make the corrected order? (y/n): ");
        String readyToOrder = scanner.nextLine().toLowerCase();

        if (!readyToOrder.equals("y")) {
            // Player is not ready to make the order
            System.out.println("\nCustomer doesn't want to repeat themselves again, and demands a refund! 🤬");
            processRefund(totalCost);
            return false ;
        }

        // Clear the screen
        System.out.print("\033c");


        // Prompt the player for the corrected choices
        System.out.print("\nEnter corrected boba flavor: ");
        this.playerBobaFlavor = scanner.next();
        scanner.nextLine().toLowerCase(); 
    
        System.out.print("Enter corrected boba size: ");
        this.playerBobaSize = scanner.next();
        scanner.nextLine().toLowerCase(); 
    
        System.out.print("Enter corrected boba topping: ");
        this.playerBobaTopping = scanner.next();
        scanner.nextLine().toLowerCase(); 

        slowWriting("\n.......... Making Boba .........\n");

        // Calculate total cost using stored choices
        double newTotalCost = calculateBobaCost(customer);

        // Display total cost and simulate payment
        System.out.println("\nBarista " + playerName+ ": 'Your total is $" + newTotalCost + "'");
        slowWriting("\n... Costumer is paying ...\n");
        System.out.println("\nPayment Recieved! 💰 💵 💳 ");
        System.out.println("\nBarista " +playerName+ ": Here's your receipt! 🧾");

        slowWriting("\n... Costumer is tasting boba ...");
    
        // Display payment simulation
        System.out.println("\nPayment Recieved! 🧾 💰 💵 💳 ");
        System.out.println("\nBarista " +playerName+ ": Great! Here's your receipt! 🧾");
        totalEarnings = totalEarnings.add(BigDecimal.valueOf(newTotalCost));
    
        // Determine satisfaction based on whether the corrected choices match the customer's preferences
        boolean satisfied = determineSatisfaction(customer);
    
        if (satisfied) {
            handleSatisfiedCustomer(newTotalCost);
            return true;
        } else {
            System.out.println("\nCustomer is now angry, and demands a refund! 🤬");
            processRefund(newTotalCost);
            return false;
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
