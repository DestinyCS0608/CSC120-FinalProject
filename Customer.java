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
        String[] names = {"👩‍🎓 High Schooler", "👩‍👧‍👧Mom on the Run", "👩‍💼Corporate Worker", "👩‍💻Stressed College Student"};
        String randomName = names[random.nextInt(names.length)];
       
        // Bold formatting using ANSI escape codes
        return "\033[1m" + randomName + "\033[0m";
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
        String[] toppings = {"tapioca", "jelly"};
        return toppings[random.nextInt(toppings.length)];
    }

    public boolean isSatisfied(String playerBobaFlavor, String playerBobaSize, String playerBobaTopping) {
        return playerBobaFlavor.equalsIgnoreCase(preferredFlavor) &&
               playerBobaSize.equalsIgnoreCase(preferredSize) &&
               playerBobaTopping.equalsIgnoreCase(preferredTopping);
    }
    
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


