import java.util.Scanner;

public class BobaGame {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            // Initialize player
            System.out.print("Enter your name: ");
            String playerName = scanner.nextLine();
            Player player = new Player(playerName, new BobaMenu(), scanner);

            // Game loop
            while (true) {
                // Generate a random customer
                Customer customer = new Customer();

                // Display customer and prompt the player to serve boba
                System.out.println("\nCustomer in the shop: " + customer);
                player.serveBoba(customer);

                // Ask if the player wants to continue
                System.out.print("\nServe another customer? (y/n): ");
                String continuePlaying = scanner.nextLine().toLowerCase();
                if (!continuePlaying.equals("y")) {
                    System.out.println("Thanks for playing! Game over.");
                    break;
                }
            }
        }
    }
}
