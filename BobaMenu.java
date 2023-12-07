import java.util.HashMap;
import java.util.Map;

public class BobaMenu {
    private Map<String, Double> flavorPrices;
    private Map<String, Double> sizePrices;
    private Map<String, Double> toppingPrices;

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
        toppingPrices.put("popping", 0.75);
    }

    public double getFlavorPrice(String flavor) {
        return flavorPrices.getOrDefault(flavor.toLowerCase(), 0.00);
    }

    public double getSizePrice(String size) {
        return sizePrices.getOrDefault(size.toLowerCase(), 0.00);
    }

    public double getToppingPrice(String topping) {
        return toppingPrices.getOrDefault(topping.toLowerCase(), 0.00);
    }
}
