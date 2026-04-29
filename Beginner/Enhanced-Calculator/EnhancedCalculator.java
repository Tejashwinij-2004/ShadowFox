import java.util.InputMismatchException;
import java.util.Scanner;

public class EnhancedCalculator {

    static Scanner sc = new Scanner(System.in);

    // ================= BASIC OPERATIONS =================
    public static double add(double a, double b) {
        return a + b;
    }

    public static double subtract(double a, double b) {
        return a - b;
    }

    public static double multiply(double a, double b) {
        return a * b;
    }

    public static double divide(double a, double b) throws ArithmeticException {
        if (b == 0) {
            throw new ArithmeticException("Cannot divide by zero!");
        }
        return a / b;
    }

    // ================= SCIENTIFIC =================
    public static double squareRoot(double a) throws ArithmeticException {
        if (a < 0) {
            throw new ArithmeticException("Cannot find square root of negative number!");
        }
        return Math.sqrt(a);
    }

    public static double power(double base, double exponent) {
        return Math.pow(base, exponent);
    }

    // ================= UNIT CONVERSIONS =================
    public static double celsiusToFahrenheit(double c) {
        return (c * 9 / 5) + 32;
    }

    public static double fahrenheitToCelsius(double f) {
        return (f - 32) * 5 / 9;
    }

    public static double inrToUsd(double inr) {
        return inr / 83.0; // Approx conversion
    }

    public static double usdToInr(double usd) {
        return usd * 83.0;
    }

    // ================= INPUT HANDLING =================
    public static double getDoubleInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return sc.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Invalid input! Please enter a valid number.");
                sc.next(); // clear invalid input
            }
        }
    }

    public static int getIntInput(String message) {
        while (true) {
            try {
                System.out.print(message);
                return sc.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice! Please enter a number.");
                sc.next();
            }
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {

        int choice;

        do {
            System.out.println("\n====== ENHANCED CALCULATOR ======");
            System.out.println("1. Addition");
            System.out.println("2. Subtraction");
            System.out.println("3. Multiplication");
            System.out.println("4. Division");
            System.out.println("5. Square Root");
            System.out.println("6. Power");
            System.out.println("7. Temperature Conversion");
            System.out.println("8. Currency Conversion");
            System.out.println("0. Exit");

            choice = getIntInput("Enter your choice: ");

            try {
                switch (choice) {

                    case 1:
                        double a1 = getDoubleInput("Enter first number: ");
                        double b1 = getDoubleInput("Enter second number: ");
                        System.out.println("Result: " + add(a1, b1));
                        break;

                    case 2:
                        double a2 = getDoubleInput("Enter first number: ");
                        double b2 = getDoubleInput("Enter second number: ");
                        System.out.println("Result: " + subtract(a2, b2));
                        break;

                    case 3:
                        double a3 = getDoubleInput("Enter first number: ");
                        double b3 = getDoubleInput("Enter second number: ");
                        System.out.println("Result: " + multiply(a3, b3));
                        break;

                    case 4:
                        double a4 = getDoubleInput("Enter first number: ");
                        double b4 = getDoubleInput("Enter second number: ");
                        System.out.println("Result: " + divide(a4, b4));
                        break;

                    case 5:
                        double num = getDoubleInput("Enter number: ");
                        System.out.println("Square Root: " + squareRoot(num));
                        break;

                    case 6:
                        double base = getDoubleInput("Enter base: ");
                        double exp = getDoubleInput("Enter exponent: ");
                        System.out.println("Result: " + power(base, exp));
                        break;

                    case 7:
                        System.out.println("1. Celsius to Fahrenheit");
                        System.out.println("2. Fahrenheit to Celsius");
                        int tempChoice = getIntInput("Choose option: ");

                        if (tempChoice == 1) {
                            double c = getDoubleInput("Enter Celsius: ");
                            System.out.println("Fahrenheit: " + celsiusToFahrenheit(c));
                        } else if (tempChoice == 2) {
                            double f = getDoubleInput("Enter Fahrenheit: ");
                            System.out.println("Celsius: " + fahrenheitToCelsius(f));
                        } else {
                            System.out.println("Invalid choice!");
                        }
                        break;

                    case 8:
                        System.out.println("1. INR to USD");
                        System.out.println("2. USD to INR");
                        int currChoice = getIntInput("Choose option: ");

                        if (currChoice == 1) {
                            double inr = getDoubleInput("Enter INR: ");
                            System.out.println("USD: " + inrToUsd(inr));
                        } else if (currChoice == 2) {
                            double usd = getDoubleInput("Enter USD: ");
                            System.out.println("INR: " + usdToInr(usd));
                        } else {
                            System.out.println("Invalid choice!");
                        }
                        break;

                    case 0:
                        System.out.println("Thank you for using calculator!");
                        break;

                    default:
                        System.out.println("Invalid menu choice!");
                }

            } catch (ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            }

        } while (choice != 0);

        sc.close();
    }
}