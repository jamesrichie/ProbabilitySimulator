package distributions;

import java.util.concurrent.ThreadLocalRandom;

public class ContinuousUniform implements RandomDistribution {
    private final double range;
    private final double shift;

    public ContinuousUniform(double a, double b) {
        assert(0.0 <= a); assert(a <= b);

        this.range = b - a;
        this.shift = a;
    }

    public double sample() {
        return range * ThreadLocalRandom.current().nextDouble() + shift;
    }
}
