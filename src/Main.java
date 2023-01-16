import java.util.Scanner;

public class Main {
    public static int firstDigit;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("CNP:");
            Long cnpCode = scanner.nextLong();
            if (length(cnpCode) == 13) {
                String cnpString = cnpCode + "";
                CNP cnp = new CNP(firstDigit, cnpString);
            } else
                System.err.println("The length of the CNP its not 13. This CNP have " + length(cnpCode) + " digits.");
        } catch (Exception e) {
            System.err.println("CNP need to be numeric type...");
        }
    }

    private static int length(long cnp) {
        int length = 0;
        while (cnp > 0) {
            if (cnp < 10) {
                firstDigit = (int) cnp;
            }
            length++;
            cnp /= 10;
        }
        return length;
    }
}