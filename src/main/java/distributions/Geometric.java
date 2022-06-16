package distributions;

public class Geometric implements RandomDistribution {
    private final double p;

    public Geometric(double p) {
        assert(0.0 <= p); assert(p <= 1.0);

        this.p = p;
    }

    public double sample() {
        Bernoulli randomVariable = new Bernoulli(p);

        double trials = 1.0;

        while (randomVariable.sample() != 1) {
            trials += 1;
        }
        return trials;
    }
}
