import java.util.*;
import java.io.*;

public class ChowTech {

    static int[] employeeIDs = new int[0];
    static double[] salesSummary = new double[10000];
    static double[] bonusSummary = new double[10000];
    static double totalSales = 0;
    static double totalBonuses = 0;
    static File inFile;
    static File outFile;

    public static void main(String[] args) throws FileNotFoundException {
        // Global variables
        Scanner sc = new Scanner(System.in);

        // Run Code
        // Get user input
        System.out.println("What year are you retrieving the text file from?");
        int year = sc.nextInt();
        inFile = new File(String.format("%d_sales_summary.txt", year));
        outFile = new File(String.format("%d_bonus_summary.txt", year));
        calculateBonuses();
        writeBonuses();
    }

    public static void calculateBonuses() throws FileNotFoundException {
        Scanner reader = new Scanner(inFile);

        reader.nextLine();
        reader.nextLine();
        while (reader.hasNextLine()) {
            int id = Integer.parseInt(reader.next());
            double sales = Double.parseDouble(reader.nextLine().trim());
            if (!inArray(id, employeeIDs)) {
                int[] temp = new int[employeeIDs.length + 1];
                System.arraycopy(employeeIDs, 0, temp, 0, employeeIDs.length);
                temp[temp.length - 1] = id;
                employeeIDs = temp;
            }
            salesSummary[id] += sales;
        }

        for (Integer id : employeeIDs) {
            totalSales += salesSummary[id];
            double bonus = salesSummary[id] * 0.05;
            bonusSummary[id] = bonus;
            totalBonuses += bonus;
        }
    }

    public static void writeBonuses() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(outFile);
        out.printf("%-15s%11s%3s%11s\n", "Employee ID", "Sales Total", "", "Bonus Total");
        for (int i = 0; i < 40; i++) {
            out.print('*');
        }
        out.println();
        for (Integer id : employeeIDs) {
            out.printf("%-15d%11.2f%3s%11.2f\n", id, salesSummary[id], "", bonusSummary[id]);
        }
        for (int i = 0; i < 40; i++) {
            out.print('*');
        }
        out.println();
        out.printf("Number of Employees: %d\n", employeeIDs.length);
        out.printf("Sales Total: %.2f\n", totalSales);
        out.printf("Bonus Total: %.2f\n", totalBonuses);
        out.close();
    }

    public static boolean inArray(int num, int[] arr) {
        for (Integer i : arr) {
            if (num == i) {
                return true;
            }
        }
        return false;
    }
}
