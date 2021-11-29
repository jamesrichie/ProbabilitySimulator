package distributions;

import java.util.Random;

public class Exponential {
    private final Double l;
    private final Random rand;

    public Exponential(Double l) {
        assert(l >= 0);

        this.l = l;
        rand = new Random();
    }

    public Double sample() {
        return Math.log(1 - rand.nextDouble()) / (-l);
    }
}
