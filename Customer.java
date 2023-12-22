import java.util.Random;

/**
 * The Customer class represents a customer ordering a boba drink with preferences for flavor, size, and topping.
 * It generates random customers and their random preferences.
 * It also provides methods to check if the customer is satisfied with a given boba order, and if not complains about it.
 */

public class Customer {
    private static final Random random = new Random();

    private String name;
    private String preferredFlavor;
    private String preferredSize;
    private String preferredTopping;

    /**
     * Constructs a new random customer with random choices of for name, flavor, size, and topping.
     */
    
    public Customer() {
        this.name = generateRandomName();
        this.preferredFlavor = generateRandomFlavor();
        this.preferredSize = generateRandomSize();
        this.preferredTopping = generateRandomTopping();
    }

    /**
     * Generates a random name for the customer from an array list.
     * @return A randomly chosen name for the customer.
     */
    private String generateRandomName() {
        String[] names = {"👩‍🎓 High Schooler", "👩‍👧‍👧Mom on the Run", "👩‍💼Corporate Worker", "👩‍💻Stressed College Student"};
        String randomName = names[random.nextInt(names.length)];
       
        // Bold formatting using ANSI escape codes
        return "\033[1m" + randomName + "\033[0m";
    }

    /**
     * Generates a random flavor for the customer's boba preference.
     * @return A randomly chosen flavor for the customer's boba.
     */
    private String generateRandomFlavor() {
        String[] flavors = {"taro", "matcha", "mango"};
        return flavors[random.nextInt(flavors.length)];
    }

    /**
     * Generates a random size for the customer's boba preference.
     * @return A randomly chosen size for the customer's boba.
     */
    private String generateRandomSize() {
        String[] sizes = {"small", "medium", "large"};
        return sizes[random.nextInt(sizes.length)];
    }

    /**
     * Generates a random topping for the customer's boba preference.
     * @return A randomly chosen topping for the customer's boba order.
     */
    private String generateRandomTopping() {
        String[] toppings = {"tapioca", "jelly"};
        return toppings[random.nextInt(toppings.length)];
    }

    /**
     * Checks if the customer is satisfied with a boba order.
     * @param playerBobaFlavor  The flavor of the player's boba.
     * @param playerBobaSize    The size of the player's boba.
     * @param playerBobaTopping The topping of the player's boba.
     * @return true if the customer is satisfied; and false if they're not.
     */
    public boolean CustomerSatisfied(String playerBobaFlavor, String playerBobaSize, String playerBobaTopping) {
        return playerBobaFlavor.equalsIgnoreCase(preferredFlavor) &&
               playerBobaSize.equalsIgnoreCase(preferredSize) &&
               playerBobaTopping.equalsIgnoreCase(preferredTopping);
    }
    
    /**
     * Prints complaints if the player's boba order does not match the customer's preferences.
     * @param playerBobaFlavor  The flavor of the player's boba.
     * @param playerBobaSize    The size of the player's boba.
     * @param playerBobaTopping The topping of the player's boba.
     * Help from Professor Jordan!
     */
    public void complain(String playerBobaFlavor, String playerBobaSize, String playerBobaTopping){
        System.out.println("\n");
        if (!preferredFlavor.equalsIgnoreCase(playerBobaFlavor)) {
            System.out.println(name + ": 'This boba tastes off... 😣 '");
        }
        if (!preferredSize.equalsIgnoreCase(playerBobaSize)) {
            System.out.println(name +": 'I don't remembering ordering this size.. 🧋 '");
        }
        if (!preferredTopping.equalsIgnoreCase(playerBobaTopping)){
            System.out.println(name +": 'Woah, the toppings were surprising!... 😦 '");
        }
    }

    /**
     * Prints out the  customer's preferred boba order with a typewriter effect.
     * @return A string of the customer's preferred boba order.
     * Source: Stack Overflow https://stackoverflow.com/questions/32918414/java-printing-text-letter-by-letter-in-console-ft-lag  
     * Modified to print slower
     */
    public String toString() {
        String text = "\033[1m" + name + "\033[0m: 'I would like a " +
        preferredSize + " " + preferredFlavor + " boba with " +
        preferredTopping + " as a topping.'";
         for(int i = 0; i < text.length(); i++) {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 75) {
            }
            System.out.print(text.charAt(i));
        }
        return "";
    }
}


