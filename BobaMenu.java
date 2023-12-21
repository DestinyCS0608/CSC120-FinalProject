import java.util.HashMap;
import java.util.Map;

/**
 * The BobaMenus class represents the menu of the boba shop, including prices for flavors, sizes, and toppings.
 * It provides methods to get the prices of specific flavors, sizes, and toppings.
 */
public class BobaMenu {
    private Map<String, Double> flavorPrices;
    private Map<String, Double> sizePrices;
    private Map<String, Double> toppingPrices;

    /**
     * Constructs a new Boba Menu with default prices for flavors, sizes, and toppings.
     */
    public BobaMenu() {
        flavorPrices = new HashMap<>();
        flavorPrices.put("taro", 3.00);
        flavorPrices.put("matcha", 3.50);
        flavorPrices.put("mango", 4.00);

        sizePrices = new HashMap<>();
        sizePrices.put("small", 1.00);
        sizePrices.put("medium", 1.50);
        sizePrices.put("large", 2.00);

        toppingPrices = new HashMap<>();
        toppingPrices.put("tapioca", 0.50);
        toppingPrices.put("jelly", 0.75);

    }

    /**
     * Gets the price of   the flavor.
     * @param flavor The flavor for which to get the price.
     * @return The price of the specified flavor or if not, returns 0.00
     */
    public double getFlavorPrice(String flavor) {
        return flavorPrices.getOrDefault(flavor.toLowerCase(), 0.00);
    }

    /**
     * Gets the price of the  size.
     * @param size The size for which to retrieve the price.
     * @return The price of the specified size or if not, returns 0.00
     */
    public double getSizePrice(String size) {
        return sizePrices.getOrDefault(size.toLowerCase(), 0.00);
    }

    /**
     * Gets the price of the specified topping.
     * @param topping The topping for which to retrieve the price.
     * @return The price of the specified topping or if not, returns 0.00
     */
    public double getToppingPrice(String topping) {
        return toppingPrices.getOrDefault(topping.toLowerCase(), 0.00);
    }
    

}
