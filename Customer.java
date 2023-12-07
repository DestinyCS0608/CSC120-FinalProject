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
        String[] names = {"Destiny", "Shaq", "Danielle", "David"};
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

    @Override
    public String toString() {
        return "Customer " + name + " (FLAVOR: " + preferredFlavor + ", SIZE: " + preferredSize + ", TOPPING: " + preferredTopping + ")";
    }
}
