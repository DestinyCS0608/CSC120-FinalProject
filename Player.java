/** This section was generated with the help of AI-completion tool. **/
 
import java.util.Random;
import java.util.Scanner;
import java.math.BigDecimal;
import java.text.DecimalFormat;

/**
 * The Player class represents a player who is a barista who serves boba drinks to customers.
 * It manages the interaction with customers, boba choices, orders, and customer satisfaction.
 */
public class Player {

    private String name;
    private BobaMenu bobaMenu;
    private String playerFlavor;
    private String playerSize;
    private String playerTopping;
    private BigDecimal profit;
    private Scanner scanner;

    /**
     * Constructs a new player with the specified name, boba menu, and scanner.
     * @param name The player's name.
     * @param bobaMenu   The boba menu to reference for pricing of the drinks.
     * @param scanner    The scanner for user input.
     */
    public Player(String name, BobaMenu bobaMenu, Scanner scanner) {
        this.name = name;
        this.bobaMenu = bobaMenu;
        this.profit = BigDecimal.ZERO;       
        this.playerFlavor = null;
        this.playerSize = null;
        this.playerTopping = null;
        this.scanner = scanner;
    }

    /**
     * Gets the total earnings of the player in decimal format.
     * @return The total earnings of the player.
     */
    public BigDecimal getProfit() {
        return profit;
    }

     /**
     * Serves boba to a customer, managing the order process and customer satisfaction.
     * @param customer The customer to serve.
     * @return true if the customer is satisfied and false if they're not.
     */
    public boolean serveCustomer(Customer customer) {
    
        System.out.println("\u001B[3mAre you ready to make the order? (y/n): \u001B[0m"); 
        String makeBoba = scanner.nextLine().toLowerCase();

        if (!makeBoba.equals("y")) {
            System.out.println("\n\u001B[3mOkay, the customer will repeat their order.\u001B[0m"); 
            System.out.println(customer); 
            System.out.println("\n\u001B[3mAre you ready to make the order now? (y/n):\u001B[0m"); 
            makeBoba = scanner.nextLine().toLowerCase();
    
             // Player is not ready to make the order, so move on to the next customer
            if (!makeBoba.equals("y")) {
                System.out.println("\n\u001B[3mOkay, let's give this customer to your coworker barista.\u001B[0m"); 
                return false;
            }
        }

        // Clear the screen
        System.out.print("\033c");

        // Prompt the player for boba choices
        promptForBobaChoices();
        slowWriting("\n.......... Making Boba .........\n");

        // Calculate  cost of boba using the customer  choices
        double bobaCost = calculateBobaCost(customer);

        // Display total cost and payment
        System.out.println("\nBarista " + name+ ": 'Your total is $" + bobaCost + "'");
        slowWriting("\n... Costumer is paying ...\n");
        System.out.println("\nPayment Recieved! 💰 💵 💳 ");
        System.out.println("\nBarista " +name+ ": Here's your receipt! 🧾");

        slowWriting("\n... Costumer is tasting boba ...");

        // Determine customer satisfactions based on whether the order was made correctly
        boolean satisfied = decideSatisfaction(customer);

        if (satisfied) {
            satisfiedCustomer( customer, bobaCost);
        } else {
            customer.complain(playerFlavor, playerSize, playerTopping);
            dissatisfiedCustomer(customer, bobaCost);
        }
        System.out.println("\033[1mTotal Shop Profit: $" + profit + "\033[0m"); 
        System.out.println("\033[1mGoal to Reach: $ 100\033[0m");  
        System.out.println("-----------------------------------");
        return satisfied;
    }

    /**
     * Prompts the player for boba choices
     * @return true if the player is ready with the boba choices and false if they're not.
     */
    private boolean promptForBobaChoices() {
        boolean ready = false;
    
        do {
            System.out.print("Enter boba flavor: ");
            playerFlavor = scanner.nextLine().toLowerCase();
            System.out.print("Enter boba topping: ");
            playerTopping = scanner.nextLine().toLowerCase();
            System.out.print("Enter boba size: ");
            playerSize = scanner.nextLine().toLowerCase();
    
            // Ask the player if they are satisfied
            System.out.print("\n\u001B[3mAre you satisfied with your choices? (y/n):\u001B[0m");
            String makeBoba = scanner.nextLine().toLowerCase();
    
            if (!makeBoba.equals("y")) {
                System.out.println("\n\u001B[3mOkay, let's try again!\n\u001B[0m");
            } else {
                System.out.println("\n\u001B[3mGreat, let's make the drink!\u001B[0m");
                ready = true; // Exits the loop if  the user is ready
            }
        } while (!ready);
    
        return true; // The user is satisfied and ready to make the drink
    }
    
    /**
     * Calculates the total cost of the boba order based on player's choices, the customer, and the BobaMenu.
     * @param customer The customer that the boba order is for.
     * @return The total cost of the boba order.
     */
    private double calculateBobaCost(Customer customer) {
        double flavorCost = bobaMenu.getFlavorPrice(playerFlavor);
        double sizeCost = bobaMenu.getSizePrice(playerSize);
        double toppingCost = bobaMenu.getToppingPrice(playerTopping);
        return flavorCost + sizeCost + toppingCost;
    }

    /**
     * Determines the customer satisfaction based on whether the boba order matches the customer's preferences.
     * @param customer The customer for whom the boba is being prepared.
     * @return true if the customer is satisfied and false if not
     */
    private boolean decideSatisfaction(Customer customer) {
        return customer.CustomerSatisfied(playerFlavor, playerSize, playerTopping);
    }    

    /**
     * Handles the event when a customer is satisfied, including processing tips.
     * @param bobaCost The total cost of the boba order.
     */
    private void satisfiedCustomer(Customer customer, double bobaCost) {
        System.out.println("\nCustomer is satisfied! You made the right boba!\n");
        double tipAmount = tip();
        if (tipAmount > 0) {
            System.out.println("Customer left a $" + tipAmount + " tip!");
            profit = profit.add(BigDecimal.valueOf(bobaCost + tipAmount));
        }
    }
    
    /**
     * Generates a random tip amount between $0 and $2.
     * @return The generated tip amount.
     */
    private double tip() {
        // Generate a random tip between $0 and $2
        double tip = new Random().nextDouble() * 2.00;
    
        // Round the tip to two decimal places
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        tip = Double.parseDouble(decimalFormat.format(tip));
    
        return tip;
    }
    /**
     * Handles the event when a customer is dissatisfied, providing options to remake or refund the boba.
     * @param customer   The dissatisfied customer.
     * @param bobaCost  The total cost of the original boba order.
     */
    private void dissatisfiedCustomer(Customer customer, double bobaCost) {
        System.out.println("\nCustomer is not satisfied. You made the wrong boba! What should you do " + name + "? ");
        System.out.println("\n1. Offer a refund");
        System.out.println("2. Remake the boba order");
        System.out.print("\nEnter (1 or 2): ");
    
        while (true) {
            if (scanner.hasNextInt()) {
                int choice = scanner.nextInt();
                scanner.nextLine();
                switch (choice) {

                    case 2:
                        remakeBoba(customer, bobaCost);
                        break;
                    case 1:
                        refund(bobaCost);
                        break;
                }   
                break; // Exit the loop after processing the choice
            } else {
                System.out.println("\nInvalid input. Please enter a number (1 or 2).");
                scanner.next(); 
            }
        } 
        scanner.nextLine(); // Consume the newline character
    }
    
    /**
     * Processes a refund when a customer doesn't like their oder.
     * @param totalBobaCost The total cost of the original boba order.
     */
    private void refund(double bobaCost) {
        slowWriting("\n....Processing Refund....\n" );
        System.out.println("\nBarista " +name+ ": 'Refund processed. We're sorry for the inconvience!'");
        profit = profit.add(BigDecimal.valueOf(bobaCost));
    }

    /**
     * Remakes the boba order for a dissatisfied customer with a prompt for the users corrected choices.
     * @param customer  The dissatisfied customer.
     * @param bobaCost The total cost of the original boba order.
     * @return true if the customer is satisfied with the remake and false if otherwise.
     */
    private boolean remakeBoba(Customer customer, double bobaCost) {  

        System.out.println("\nOkay, here comes the customer's order again:");
        System.out.println(customer);

        System.out.print("\nAre you ready to make the corrected order? (y/n): ");
        String readyToOrder = scanner.nextLine().toLowerCase();

        if (!readyToOrder.equals("y")) {
            // Player is not ready to make the order
            System.out.println("\nCustomer doesn't want to repeat themselves again, and demands a refund! 🤬");
            refund(bobaCost);
            return false ;
        }

        // Clears out the the screen
        System.out.print("\033c");

        // Prompt the player for the corrected choices
        System.out.print("\nEnter corrected boba flavor: ");
        this.playerFlavor = scanner.next();
        scanner.nextLine().toLowerCase(); 
        System.out.print("Enter corrected boba topping: ");
        this.playerTopping = scanner.next();
        scanner.nextLine().toLowerCase(); 
        System.out.print("Enter corrected boba size: ");
        this.playerSize = scanner.next();
        scanner.nextLine().toLowerCase(); 

        slowWriting("\n.......... Making Boba .........\n");

        // Calculate total cost using stored choices
        double newBobaCost = calculateBobaCost(customer);

        // Display total cost and payment
        System.out.println("\nBarista " + name+ ": 'Your total is $" + newBobaCost + "'");
        slowWriting("\n... Costumer is paying ...\n");
        System.out.println("\nPayment Recieved! 💰 💵 💳 ");
        System.out.println("\nBarista " +name+ ": Great! Here's your receipt! 🧾");

        slowWriting("\n... Costumer is tasting boba ...");
    
        // Determine satisfaction based on whether the corrected choices match the customer's preferences
        boolean satisfied = decideSatisfaction(customer);
    
        if (satisfied) {
            satisfiedCustomer(customer, newBobaCost);
            return true;
        } else {
            System.out.println("\nCustomer is now angry, and demands a refund! 🤬");
            refund(newBobaCost);
            return false;
        }
    }

    /** @Overide? I think because Customer has one too.. 
     * Prints text in slow writing or typerwriter effect.
     * @param text The string to be written.
     * @return The same string that was printed.
     * Source: Stack Overflow https://stackoverflow.com/questions/32918414/java-printing-text-letter-by-letter-in-console-ft-lag  
     * Modified to print slower..
     */
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
