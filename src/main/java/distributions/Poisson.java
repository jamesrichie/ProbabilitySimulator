package distributions;

import java.util.concurrent.ThreadLocalRandom;

public class Poisson implements RandomVariable {
     private final Double l;

    public Poisson(Double l) {
        assert(l >= 0);

        this.l = l;
    }

    private int factorial(Double x) {
        int result = 1;

        for (int i = 2; i <= x; i++) {
            result *= i;
        }
        return result;
    }

    private Double power(Double l, Double x) {
        if (x == 0) {
            return 1.0;
        } else {
            return Math.pow(l, x);
        }
    }

    private Double pmf(Double x) {
        return Math.exp(-l) * power(l, x) / factorial(x);
    }

    public Double sample() {
        Double Y = ThreadLocalRandom.current().nextDouble();
        Double cdf = pmf(0.0);

        Double i = 0.0;

        while (Y > cdf) {
            i++;
            cdf += pmf(i);
        }
        return i;
    }
}
