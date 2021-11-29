package distributions;

import java.util.Random;

public class Geometric {
    private final Double p;
    private final Random rand;

    public Geometric(Double p) {
        assert(0.0 <= p); assert(p <= 1.0);

        this.p = p;
        rand = new Random();
    }

    public int sample() {
        Bernoulli randomVariable = new Bernoulli(p);

        int trials = 1;

        while (randomVariable.sample() != 1) {
            trials += 1;
        }
        return trials;
    }
}
