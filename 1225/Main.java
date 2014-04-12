import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();

        for (int i = 0; i < caseCount; i++) {
            int inputValue = in.nextInt();

            int processedValue = inputValue;
            int reversedValue = 0;

            while (processedValue != 0) {
                reversedValue *= 10;
                reversedValue += (processedValue % 10);
                processedValue /= 10;
            }

            String answer = (inputValue == reversedValue) ? "Yes" : "No";
            System.out.println("Case " + (i + 1) + ": " + answer);
        }
    }
}