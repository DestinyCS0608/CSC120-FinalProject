import java.util.Scanner;
import java.math.BigDecimal;

/**
 * The BobaGame class represents the main class for the boba game.
 * It starts the game, introduces the player to the directions of the game, and manages the flow of the game until the profit goal is reached.
 */
public class BobaGame {

    /**
     * The main method that serves as the starting point for the game
     * @param args The command-line arguments (unused in this implementation).
     */
    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {
            boolean gameStarted = false;
            Player player = null;
            BigDecimal goal = new BigDecimal("100.00"); 

            while (player == null || player.getTotalEarnings().compareTo(goal) < 0) {
                if (gameStarted) {
                    System.out.println("\u001B[1m \n🔔 A customer walks in! 🔔\033[0m");

                    // Generate a random customer
                    Customer customer = new Customer();

                    // Display customer and prompt the player to serve boba
                    System.out.println("\n" + customer);
                    // Assuming player.serveBoba returns a boolean indicating if the game should continue
                    if (!player.serveBoba(customer)) {
                        //break;
                    }
                } else {
                    // Game introduction and description
                    System.out.println("**         Welcome to the Boba Game!          **");
                    System.out.println("**********************************************");

                    // Initialize player
                    System.out.print("\nHello! What is your name?: ");
                    String playerName = scanner.nextLine();
                    System.out.println("\nNice to meet you " + playerName + "!");
                    player = new Player(playerName, new BobaMenu(), scanner);

                    System.out.println("\nIn this game, you are working in a boba shop as a recently hired Boba tea barista.");
                    System.out.println("Your goal is to serve boba to customers based on their preferences.");
                    System.out.println("Remeber each customer's flavor, size, and topping preferences.");
                    System.out.println("Try to make the right boba to keep your customers satisfied!");
                    System.out.println("Earn tips and increase your total earnings. The goal is to reach $10 in profit.");
                    System.out.println("Good luck!");

                    System.out.print("\nAre you ready to begin (y/n): ");
                    String begin = scanner.nextLine().toLowerCase();

                    if (!begin.equals("y")) {
                        System.out.println("\nOkay, let's go through the instructions again.");
                        continue; // Go back to the beginning of the loop
                    }

                    gameStarted = true; // Player is ready, and the game has started
                }
            }

            System.out.println("\nCongratulations! You've reached the profit goal of $10. Game Over!");
        }
    }
}
