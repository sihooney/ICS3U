package RetailStore;

import java.util.*;
import java.io.*;

public class ReceiptDriver {
    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your store's name: ");
        String name = sc.nextLine();
        System.out.println("Enter the receipt width: ");
        int width = Integer.parseInt(sc.nextLine());
        System.out.println("How many different items: ");
        int num = Integer.parseInt(sc.nextLine());
        List<Product> products = new ArrayList<>(num);
        for (int i = 0; i < num; i++) {
            String item = sc.nextLine();
            String unit = sc.nextLine();
            double price = Double.parseDouble(sc.nextLine());
            products.add(new Product(item, unit, price));
        }
        System.out.println("Input the sales tax: ");
        double tax = Double.parseDouble(sc.nextLine());
        System.out.println("Input the file name for the receipt: ");
        String fileName = sc.nextLine();
        File testFile = new File(fileName + ".txt");
        if (testFile.createNewFile()) {
            System.out.println("Receipt file created");
        } else {
            System.out.println("Receipt file already exists");
        }
        ReceiptGenerator test = new ReceiptGenerator(width, name, products, tax);
        test.writeReceipt(testFile);
    }
}
