package distributions;

import java.util.concurrent.ThreadLocalRandom;

public class Exponential implements RandomDistribution {
    private final double l;

    public Exponential(double l) {
        assert(l >= 0);

        this.l = l;
    }

    public double sample() {
        return Math.log(1 - ThreadLocalRandom.current().nextDouble()) / (-l);
    }
}
