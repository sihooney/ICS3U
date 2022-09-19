import java.util.*;

public class ChowChowFruitCentre {

    public static void main(String[] args) {
        // Global Variables
        Scanner sc = new Scanner(System.in);
        int apples;
        int oranges;
        double lychees;
        double blueberries;
        double appleCost;
        double orangeCost;
        double lycheeCost;
        double blueberriesCost;
        double subtotal;
        double tax;

        // Run Code
        header();

        System.out.printf("%-36s", "Number of apples purchased:");
        apples = sc.nextInt();
        System.out.printf("%-36s", "Number of oranges purchased:");
        oranges = sc.nextInt();
        System.out.printf("%-36s", "Amount of lychees purchased:");
        lychees = sc.nextDouble();
        System.out.printf("%-36s", "Amount of blueberries purchased:");
        blueberries = sc.nextDouble();
        sc.close();
        appleCost = apples * 0.83;
        orangeCost = oranges * 0.75;
        lycheeCost = lychees * 2.49;
        blueberriesCost = blueberries * 1.42;
        subtotal = appleCost + orangeCost + lycheeCost + blueberriesCost;
        tax = subtotal * 0.13;

        System.out.println();
        for (int i = 0; i < 22; i++) {
            System.out.print("-");
        }
        System.out.print("Receipt");
        for (int i = 0; i < 21; i++) {
            System.out.print("-");
        }
        System.out.println();
        System.out.printf("%-32s%s%5s%s\n", "Description", "Quantity", "", "Price");
        System.out.printf("%-32s%8d%5s%5.2f\n", "Apples @ $0.83/each", apples, "", appleCost);
        System.out.printf("%-32s%8d%5s%5.2f\n", "Oranges @ $0.75/each", oranges, "", orangeCost);
        System.out.printf("%-32s%8.2f%5s%5.2f\n", "Lychees @ $2.49/lbs", lychees, "", lycheeCost);
        System.out.printf("%-32s%8.2f%5s%5.2f\n\n", "Apples @ $1.42lbs", blueberries, "", blueberriesCost);

        System.out.printf("%-32s%s%5s%5.2f\n", "", "Subtotal", "", subtotal);
        System.out.printf("%40s%5s%5.2f\n\n", "H.S.T. (13%)", "", tax);
        System.out.printf("%40s%5s%5.2f\n", "Net Total", "", subtotal + tax);

        footer();
    }

    public static void header() {
        for (int i = 0; i < 25; i++) {
            System.out.print("><");
        }
        System.out.println();
        System.out.printf("%14sChow Chow Fruit Centre%14s\n", "", "");
        for (int i = 0; i < 25; i++) {
            System.out.print("><");
        }
        System.out.println("\n");
    }

    public static void footer() {
        System.out.println();
        for (int i = 0; i < 7; i++) {
            System.out.print("-");
        }
        System.out.print("THANK YOU FOR SHOPPING WITH US TODAY!");
        for (int i = 0; i < 6; i++) {
            System.out.print("-");
        }
    }
}
