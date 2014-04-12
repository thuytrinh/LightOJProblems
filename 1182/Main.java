import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int caseCount = in.nextInt();
        for (int i = 0; i < caseCount; i++) {
            int value = in.nextInt();
            int parityCount = 0;

            do {
                if ((value % 2) != 0) {
                    parityCount++;
                }

                value = value >> 1;
            } while (value != 0);

            System.out.println("Case " + (i + 1) + ": " + ((parityCount % 2 == 0) ? "even" : "odd"));
        }
    }
}