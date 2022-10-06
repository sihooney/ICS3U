import java.util.Scanner;

public class NumberGuessing {
    public static void main(String[] args) {
        // Global variables
        Scanner sc = new Scanner(System.in);
        int target = (int) (Math.random() * 999) + 1;
        int count = 1;
        boolean found = false;

        // Run code
        while (count < 11) {
            System.out.println("Guess a number: ");
            int guess = sc.nextInt();
            if (guess < 1 || guess > 999) {
                System.out.println("Invalid Input! Ensure your number is between 1 and 999!");
            } else {
                if (guess == target) {
                    System.out.println("Congratulations! That is the secret number!");
                    found = true;
                    break;
                } else if (guess > target) {
                    System.out.printf("Choose a smaller number! You have %d guesses left.\n", 10 - count);
                } else {
                    System.out.printf("Choose a larger number! You have %d guesses left.\n", 10 - count);
                }
                count++;
            }
        }
        if (!found) {
            System.out.printf("Too bad! Try harder next time! The correct number was %d\n", target);
        }
    }
}
