import java.util.Scanner;
import java.math.BigDecimal;

/**
 * The BobaGame class represents the main class for the boba game.
 * It starts the game, introduces the player to the directions of the game, and manages the flow of the game until the profit goal is reached.
 */
public class BobaGame {

    /**
     * The main method that serves as the starting point for the game
     * @param args 
     */
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            boolean gameStarted = false;
            Barista player = null;
            BigDecimal goal = new BigDecimal("100.00"); 

            while (player == null || player.getProfit().compareTo(goal) < 0) {
                if (!gameStarted) {
                    // Game introduction and description
                    System.out.println("\n**         Welcome to the Boba Game!          **");
                    System.out.println("**********************************************");

                    // Creating player - barista
                    System.out.print("\nHello! What is your name?: ");
                    String playerName = scanner.nextLine();
                    System.out.println("\nNice to meet you " + playerName + "!");
                    player = new Barista(playerName, new BobaMenu(), scanner);

                    //Instructions to the game
                    System.out.println("\nIn this game, you are working in a boba shop as a recently hired Boba tea barista.");
                    System.out.println("Your job is to serve boba to customers based on what the order.");
                    System.out.println("Remember each customer's flavor, size, and topping preferences.");
                    System.out.println("Try to make the right boba to keep your customers satisfied!");
                    System.out.println("Earn tips and increase your profit. The goal is to reach $100 in profit.");
                    System.out.println("Good luck!");

                    System.out.print("\nAre you ready to begin (y/n): ");
                    String begin = scanner.nextLine().toLowerCase();

                    if (!begin.equals("y")) {
                        System.out.println("\nOkay, let's go through the instructions again.");
                        continue; // Go back to the beginning of the loop
                    }

                    gameStarted = true; // Player is ready, and the game has started
                
                } else {
                    System.out.println("\u001B[1m \n🔔 A customer walks in! 🔔\033[0m");

                    // Generate a random customer
                    Customer customer = new Customer();

                    // Display customer and prompt the player to serve boba
                    System.out.println("\n" + customer);
                    // Assuming player.serveBoba returns a boolean indicating if the game should continue
                    if (!player.serveCustomer(customer)) {
                        //break;
                    }
                    
                }
            }

            System.out.println("\nCongratulations! You've reached the profit goal of $100. Game Over! Thanks for playing.");
        }
    }
}
