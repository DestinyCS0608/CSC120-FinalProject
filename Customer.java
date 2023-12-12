import java.util.Random;

public class Customer {
    private static final Random random = new Random();

    private String name;
    private String preferredFlavor;
    private String preferredSize;
    private String preferredTopping;

    
    public Customer() {
        this.name = generateRandomName();
        this.preferredFlavor = generateRandomFlavor();
        this.preferredSize = generateRandomSize();
        this.preferredTopping = generateRandomTopping();
    }

    public String getName() {
        return name;
    }

    public String getPreferredFlavor() {
        return preferredFlavor;
    }

    public String getPreferredSize() {
        return preferredSize;
    }

    public String getPreferredTopping() {
        return preferredTopping;
    }

    private String generateRandomName() {
        String[] names = {"High Schooler", "Mom on the Run", "Corporate Worker", "Stressed College Student"};
        return names[random.nextInt(names.length)];
    }

    private String generateRandomFlavor() {
        String[] flavors = {"taro", "matcha", "mango"};
        return flavors[random.nextInt(flavors.length)];
    }

    private String generateRandomSize() {
        String[] sizes = {"small", "medium", "large"};
        return sizes[random.nextInt(sizes.length)];
    }

    private String generateRandomTopping() {
        String[] toppings = {"tapioca", "popping"};
        return toppings[random.nextInt(toppings.length)];
    }

    public boolean isSatisfied(String playerBobaFlavor, String playerBobaSize, String playerBobaTopping) {
        return playerBobaFlavor.equalsIgnoreCase(preferredFlavor) &&
               playerBobaSize.equalsIgnoreCase(preferredSize) &&
               playerBobaTopping.equalsIgnoreCase(preferredTopping);
    }
    
    public String toString() {
         String text = name + ": I would like a " + preferredFlavor + " boba with " + preferredTopping + " as a topping, in a " + preferredSize + " size.";
         for(int i = 0; i < text.length(); i++) {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < 75) {
            }
            System.out.print(text.charAt(i));
        }
        return "";
    }
}


