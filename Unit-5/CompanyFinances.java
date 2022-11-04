import java.math.BigDecimal;
import java.util.*;
import java.io.*;

public class CompanyFinances {

    public static void main(String[] args) throws IOException {
        // Global variables
        Scanner sc = new Scanner(System.in);
        Scanner inFile;
        PrintWriter outFile;
        Map<Integer, BigDecimal> employeeSales = new HashMap<>();
        Map<Integer, BigDecimal> employeeBonuses = new HashMap<>();
        BigDecimal totalSales = new BigDecimal(0);
        BigDecimal totalBonuses = new BigDecimal(0);

        // Run Code
        // Get user input
        System.out.println("What year are you retrieving the text file from?");
        int year = sc.nextInt();
        sc.close();
        inFile = new Scanner(new File(String.format("%d_sales_summary.txt", year)));

        // Read input file
        inFile.nextLine();
        inFile.nextLine();
        while (inFile.hasNextLine()) {
            int id = Integer.parseInt(inFile.next());
            BigDecimal sales = new BigDecimal(inFile.nextLine().trim());
            if (employeeSales.containsKey(id)) {
                employeeSales.put(id, employeeSales.get(id).add(sales));
            } else {
                employeeSales.put(id, sales);
            }
        }
        inFile.close();

        // Calculate employee bonuses, total sales, and total bonuses
        for (Map.Entry<Integer, BigDecimal> entry : employeeSales.entrySet()) {
            BigDecimal bonus = entry.getValue().multiply(BigDecimal.valueOf(0.05));
            totalSales = totalSales.add(entry.getValue());
            totalBonuses = totalBonuses.add(bonus);
            employeeBonuses.put(entry.getKey(), bonus);
        }

        // Write the bonus summary to the output file
        outFile = new PrintWriter(String.format("%d_bonus_summary.txt", year));
        outFile.printf("%-15s%11s%3s%11s\n", "Employee ID", "Sales Total", "", "Bonus Total");
        for (int i = 0; i < 40; i++) {
            outFile.print('*');
        }
        outFile.println();
        for (Integer id : employeeSales.keySet()) {
            outFile.printf("%-15d%11.2f%3s%11.2f\n", id, employeeSales.get(id), "", employeeBonuses.get(id));
        }
        for (int i = 0; i < 40; i++) {
            outFile.print('*');
        }
        outFile.println();
        outFile.printf("Number of Employees: %d\n", employeeSales.size());
        outFile.printf("Sales Total: %.2f\n", totalSales);
        outFile.printf("Bonus Total: %.2f\n", totalBonuses);
        outFile.close();
    }
}
