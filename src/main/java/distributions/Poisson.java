package distributions;

import java.util.Random;

public class Poisson {
     private final Double l;
     private final Random rand;

    public Poisson(Double l) {
        assert(l >= 0);

        this.l = l;
        rand = new Random();
    }

    private int factorial(int x) {
        int result = 1;

        for (int i = 2; i <= x; i++) {
            result *= i;
        }
        return result;
    }

    private Double power(Double l, int x) {
        if (x == 0) {
            return 1.0;
        } else {
            return Math.pow(l, x);
        }
    }

    private Double pmf(int x) {
        return Math.exp(-l) * power(l, x) / factorial(x);
    }

    public int sample() {
        Double Y = rand.nextDouble();
        Double cdf = pmf(0);

        int i = 0;

        while (Y > cdf) {
            i++;
            cdf += pmf(i);
        }
        return i;
    }
}
