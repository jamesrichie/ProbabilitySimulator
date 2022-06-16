package distributions;

import java.util.concurrent.ThreadLocalRandom;

public class ContinuousUniform implements RandomVariable {
    private final Double range;
    private final Double shift;

    public ContinuousUniform(Double a, Double b) {
        assert(0.0 <= a); assert(a <= b);

        this.range = b - a;
        this.shift = a;
    }

    public Double sample() {
        return range * ThreadLocalRandom.current().nextDouble() + shift;
    }
}
