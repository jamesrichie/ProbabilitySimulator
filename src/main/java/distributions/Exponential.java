package distributions;

import java.util.concurrent.ThreadLocalRandom;

public class Exponential implements RandomVariable {
    private final Double l;

    public Exponential(Double l) {
        assert(l >= 0);

        this.l = l;
    }

    public Double sample() {
        return Math.log(1 - ThreadLocalRandom.current().nextDouble()) / (-l);
    }
}
