public class RecursivePowerFunction {
    public static double power(double base, int exponent) {
        // Base case: any number to the power of 0 is 1
        if (exponent == 0) {
            return 1;
        }
        // If exponent is negative, compute the positive exponent and take reciprocal
        if (exponent < 0) {
            return 1 / power(base, -exponent);
        }
        // Recursive case: multiply base with the result of power with exponent decreased by 1
        return base * power(base, exponent - 1);
    }

    public static void main(String[] args) {
        double base = 2.0;
        int exponent = 3;
        double result = power(base, exponent);
        System.out.println(base + " raised to the power of " + exponent + " is: " + result);

        base = 2.0;
        exponent = -2;
        result = power(base, exponent);
        System.out.println(base + " raised to the power of " + exponent + " is: " + result);
    }
}
