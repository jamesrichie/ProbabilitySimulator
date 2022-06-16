package distributions;

import java.util.concurrent.ThreadLocalRandom;

public class Bernoulli implements RandomDistribution {
    private final double p;

    public Bernoulli(double p) {
        assert(0.0 <= p); assert(p <= 1.0);

        this.p = p;
    }

    public double sample() {
        if (ThreadLocalRandom.current().nextDouble() < p) {
            return 1.0;
        } else {
            return 0.0;
        }
    }
}
