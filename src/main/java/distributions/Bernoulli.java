package distributions;

import java.util.Random;

public class Bernoulli {
    private final Double p;
    private final Random rand;

    public Bernoulli(Double p) {
        assert(0.0 <= p); assert(p <= 1.0);

        this.p = p;
        rand = new Random();
    }

    public int sample() {
        if (rand.nextDouble() < p) {
            return 1;
        } else {
            return 0;
        }
    }
}
