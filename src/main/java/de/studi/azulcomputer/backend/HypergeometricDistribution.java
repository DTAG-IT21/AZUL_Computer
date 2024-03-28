package de.studi.azulcomputer.backend;

public class HypergeometricDistribution {

    // Methode zur Berechnung der hypergeometrischen Verteilung
    public static double hypergeometricDistribution(int N, int M, int n, int x) {
        double numerator = combination(M, x) * combination(N - M, n - x);
        double denominator = combination(N, n);
        return numerator / denominator;
    }

    // Hilfsmethode zur Berechnung des Binomialkoeffizienten
    public static double combination(int n, int k) {
        return factorial(n) / (factorial(k) * factorial(n - k));
    }

    // Hilfsmethode zur Berechnung der Fakultät
    public static double factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        double result = 1;
        for (int i = 2; i <= n; i++) {
            result *= i;
        }
        return result;
    }
}
