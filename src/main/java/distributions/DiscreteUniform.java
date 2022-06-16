package distributions;

import java.util.concurrent.ThreadLocalRandom;

public class DiscreteUniform implements RandomDistribution {
    private final int range;
    private final int shift;

    /**
     * Generator for random distribution X ~ Unif(a,b)
     *
     * @param a inclusive lower bound for x
     * @param b inclusive upper bound for x
     */
    public DiscreteUniform(int a, int b) {
        assert(0.0 <= a); assert(a <= b);

        this.range = b - a + 1;
        this.shift = a;
    }

    /**
     * Generates a random variable x
     *
     * @return
     */
    public double sample() {
        return 1.0 * ThreadLocalRandom.current().nextInt(range) + shift;
    }
}
