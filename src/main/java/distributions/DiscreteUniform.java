package distributions;

import java.util.Random;

public class DiscreteUniform {
    private final int range;
    private final int shift;
    private final Random rand;

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
        rand = new Random();
    }

    /**
     * Generates a random variable x
     *
     * @return
     */
    public int sample() {
        return rand.nextInt(range) + shift;
    }
}
