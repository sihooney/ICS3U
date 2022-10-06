package RetailStore;

import java.io.*;
import java.util.*;

import javafx.util.Pair;

public class Store {

    private String storeName;
    private final List<Product> products;
    private double salesTax;

    public Store(String storeName, ArrayList<Product> products, double salesTax) {
        this.storeName = storeName;
        this.products = products;
        this.salesTax = salesTax;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public void updateProduct(String productName, Product newProduct) {
        boolean present = false;
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getName().equals(productName)) {
                present = true;
                products.set(i, newProduct);
            }
        }
        if (!present) {
            System.out.println("Product not in list, try adding the product");
        }
    }

    public void addProduct(Product product) {
        if (!products.contains(product)) {
            products.add(product);
        } else {
            System.out.println("Product already in list, try updating the product");
        }
    }

    public void removeProduct(String productName) {
        products.removeIf(p -> p.getName().equals(productName));
    }

    public void setSalesTax(double salesTax) {
        this.salesTax = salesTax;
    }

    public void showProductList() {
        System.out.println(products);
    }

    static class Product {

        private String name;
        private String unit;
        private double price;

        public Product(String name, String unit, double price) {
            this.name = name;
            this.unit = unit;
            this.price = price;
        }

        public String getName() {
            return this.name;
        }

        public String getUnit() {
            return this.unit;
        }

        public double getPrice() {
            return this.price;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public void setPrice(double price) {
            this.price = price;
        }
    }

    public class ReceiptGenerator {

        private int width;
        private final Map<Product, Pair<Double, Double>> purchased;
        private PrintWriter fileWriter;

        public ReceiptGenerator(int receiptWidth) {
            this.width = receiptWidth;
            purchased = new HashMap<>();
        }

        public void setWidth(int receiptWidth) {
            this.width = receiptWidth;
        }

        public void updateProduct(String productName, Product newProduct) {
            boolean present = false;
            for (int i = 0; i < products.size(); i++) {
                if (products.get(i).getName().equals(productName)) {
                    present = true;
                    products.set(i, newProduct);
                }
            }
            if (!present) {
                System.out.println("Product not in list, try adding the product");
            }
        }

        public void addProduct(Product product) {
            if (!products.contains(product)) {
                products.add(product);
            } else {
                System.out.println("Product already in list, try updating the product");
            }
        }

        public void removeProduct(String productName) {
            products.removeIf(p -> p.getName().equals(productName));
        }

        public void showProductList() {
            System.out.println(products);
        }

        public void writeReceipt(File outFile) throws FileNotFoundException {
            fileWriter = new PrintWriter(outFile);
            header();
            getPurchase();
            payment();
            footer();
            fileWriter.close();
        }

        private void header() {
            for (int i = 0; i < width / 2; i++) {
                System.out.print("><");
                fileWriter.print("><");
            }
            if (width % 2 == 1) {
                System.out.println(">");
                fileWriter.println(">");
            } else {
                System.out.println();
                fileWriter.println();
            }
            int spacing = (width - storeName.length()) / 2;
            String formatStr;
            if (width - storeName.length() % 2 == 0) {
                formatStr = String.format("%%%ds%s%%%ds\n", spacing, storeName, spacing);
            } else {
                formatStr = String.format("%%%ds%s%%%ds\n", spacing + 1, storeName, spacing);
            }
            System.out.printf(formatStr, "", "");
            fileWriter.printf(formatStr, "", "");

            for (int i = 0; i < width / 2; i++) {
                System.out.print("><");
                fileWriter.print("><");
            }
            if (width % 2 == 1) {
                System.out.println(">");
                fileWriter.println(">");
            }
            System.out.println("\n");
            fileWriter.println("\n");
        }

        private void getPurchase() {
            Scanner sc = new Scanner(System.in);
            String formatStr = String.format("%%-%ds", width - 5);
            for (Product p : products) {
                String formatTxt = String.format("Amount of %s purchased:", p.getName());
                fileWriter.printf(formatStr, formatTxt);
                System.out.printf(formatStr, formatTxt);
                double amt = sc.nextDouble();
                fileWriter.println(amt);
                purchased.put(p, new Pair<>(amt, amt * p.getPrice()));
            }
            sc.close();
        }

        private void payment() {
            System.out.println();
            fileWriter.println();
            for (int i = 0; i < (width - 7) / 2; i++) {
                System.out.print("-");
                fileWriter.print("-");
            }
            if ((width - 7) % 2 == 1) {
                System.out.print("-");
                fileWriter.print("-");
            }
            System.out.print("Receipt");
            fileWriter.print("Receipt");
            for (int i = 0; i < (width - 7) / 2; i++) {
                System.out.print("-");
                fileWriter.print("-");
            }
            System.out.println("\n");
            fileWriter.println("\n");
            String formatStr = String.format("%%-%ds%%8s%%5s%%5s\n", width - 8 - 5 - 5);
            System.out.printf(formatStr, "Description", "Quantity", "", "Price");
            fileWriter.printf(formatStr, "Description", "Quantity", "", "Price");
            System.out.println();
            fileWriter.println();

            double subtotal = 0;
            for (Map.Entry<Product, Pair<Double, Double>> entry : purchased.entrySet()) {
                String name = entry.getKey().getName();
                String unit = entry.getKey().getUnit();
                double unitCost = entry.getKey().getPrice();
                double amt = entry.getValue().getKey();
                double cost = entry.getValue().getValue();
                subtotal += cost;
                String purchase = String.format("%s @ $%.2f/%s", name, unitCost, unit);
                if (amt % 1 == 0) {
                    formatStr = String.format("%%-%ds%%8d%%5s%%5.2f\n", width - 8 - 5 - 5);
                    System.out.printf(formatStr, purchase, Math.round(amt), "", cost);
                    fileWriter.printf(formatStr, purchase, Math.round(amt), "", cost);
                } else {
                    formatStr = String.format("%%-%ds%%8.2f%%5s%%5.2f\n", width - 8 - 5 - 5);
                    System.out.printf(formatStr, purchase, amt, "", cost);
                    fileWriter.printf(formatStr, purchase, amt, "", cost);
                }
            }

            double tax = subtotal * salesTax;
            formatStr = String.format("%%%ds%%5s%%5.2f\n", width - 5 - 5);
            System.out.printf(formatStr, "Subtotal", "", subtotal);
            fileWriter.printf(formatStr, "Subtotal", "", subtotal);
            System.out.printf(formatStr + "\n", String.format("H.S.T. (%.0f%%)", salesTax * 100), "", subtotal * salesTax);
            fileWriter.printf(formatStr + "\n", String.format("H.S.T. (%.0f%%)", salesTax * 100), "", subtotal * salesTax);
            System.out.printf(formatStr, "Net Total", "", subtotal + tax);
            fileWriter.printf(formatStr, "Net Total", "", subtotal + tax);
        }

        private void footer() {
            System.out.println();
            fileWriter.println();
            int spacing = (width - 37) / 2;
            for (int i = 0; i < spacing; i++) {
                System.out.print("-");
                fileWriter.print("-");
            }
            if ((width - 37) % 2 == 1) {
                System.out.print("-");
                fileWriter.print("-");
            }
            System.out.print("THANK YOU FOR SHOPPING WITH US TODAY!");
            fileWriter.print("THANK YOU FOR SHOPPING WITH US TODAY!");
            for (int i = 0; i < spacing; i++) {
                System.out.print("-");
                fileWriter.print("-");
            }
        }
    }
}
