package utilits;

import blocks.Product;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyComparator {
    public static List<Product> comparePriceFromLowToHigh(List<Product> allProducts) {
        Collections.sort(allProducts, Comparator.comparingDouble(Product::getRegularPrice));
        return allProducts;
    }

    public static List<Product> compareNameFromAtoZ(List<Product> allProducts) {
        Collections.sort(allProducts, Comparator.comparing(Product::getProductName));
        return allProducts;
    }

    public static List<Product> comparePriceFromHighToLow(List<Product> allProducts) {
        Collections.sort(allProducts, Comparator.comparingDouble(Product::getRegularPrice));
        Collections.reverse(allProducts);
        return allProducts;
    }

    public static List<Product> compareNameFromZtoA(List<Product> allProducts) {
        Collections.sort(allProducts, Comparator.comparing(Product::getProductName));
        Collections.reverse(allProducts);
        return allProducts;
    }

}
