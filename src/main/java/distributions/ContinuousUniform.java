package distributions;

import java.util.Random;

public class ContinuousUniform {
    private final Double range;
    private final Double shift;
    private final Random rand;

    public ContinuousUniform(Double a, Double b) {
        assert(0.0 <= a); assert(a <= b);

        this.range = b - a;
        this.shift = a;
        rand = new Random();
    }

    public Double sample() {
        return range * rand.nextDouble() + shift;
    }
}
