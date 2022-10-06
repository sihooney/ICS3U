import java.math.BigDecimal;
import java.util.*;
import java.io.*;

public class Employees {

    private final Map<Integer, BigDecimal> employeeSales;
    private final Map<Integer, BigDecimal> employeeBonuses;
    private BigDecimal totalSales;
    private BigDecimal totalBonuses;
    private final File inFile;
    private final File outFile;

    public Employees(File inFile, File outFile) {
        employeeSales = new HashMap<>();
        employeeBonuses = new HashMap<>();
        this.inFile = inFile;
        this.outFile = outFile;
    }

    public void readSales() throws FileNotFoundException {
        Scanner input = new Scanner(inFile);
        input.nextLine();
        input.nextLine();
        while (input.hasNextLine()) {
            int id = Integer.parseInt(input.next());
            BigDecimal sales = new BigDecimal(input.nextLine().trim());
            if (employeeSales.containsKey(id)) {
                employeeSales.put(id, employeeSales.get(id).add(sales));
            } else {
                employeeSales.put(id, sales);
            }
        }
        input.close();
    }

    public void calculateBonuses() {
        totalSales = new BigDecimal(0);
        totalBonuses = new BigDecimal(0);
        for (Map.Entry<Integer, BigDecimal> entry : employeeSales.entrySet()) {
            BigDecimal bonus = entry.getValue().multiply(BigDecimal.valueOf(0.05));
            totalSales = totalSales.add(entry.getValue());
            totalBonuses = totalBonuses.add(bonus);
            employeeBonuses.put(entry.getKey(), bonus);
        }
    }

    public void writeBonuses() throws FileNotFoundException {
        PrintWriter out = new PrintWriter(outFile);
        out.printf("%-15s%11s%3s%11s\n", "Employee ID", "Sales Total", "", "Bonus Total");
        for (int i = 0; i < 40; i++) {
            out.print('*');
        }
        out.println();
        for (Integer id : employeeSales.keySet()) {
            out.printf("%-15d%11.2f%3s%11.2f\n", id, employeeSales.get(id), "", employeeBonuses.get(id));
        }
        for (int i = 0; i < 40; i++) {
            out.print('*');
        }
        out.println();
        out.printf("Number of Employees: %d\n", employeeSales.size());
        out.printf("Sales Total: %.2f\n", totalSales);
        out.printf("Bonus Total: %.2f\n", totalBonuses);
        out.close();
    }

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println("What year are you retrieving the text file from?");
        int year = sc.nextInt();
        File input = new File(String.format("%d_sales_summary.txt", year));
        File output = new File(String.format("%d_bonus_summary.txt", year));
        Employees company = new Employees(input, output);
        company.readSales();
        company.calculateBonuses();
        company.writeBonuses();
    }
}
