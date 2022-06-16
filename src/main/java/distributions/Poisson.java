package distributions;

import java.util.concurrent.ThreadLocalRandom;

public class Poisson implements RandomDistribution {
    private final double l;

    public Poisson(double l) {
        assert(l >= 0);

        this.l = l;
    }

    public double sample() {
        double L = Math.exp(-l);
        double p = 1.0;

        double k = 0.0;
        while (p > L) {
            k++;
            p *= ThreadLocalRandom.current().nextDouble();
        }
        return k - 1;
    }
}
