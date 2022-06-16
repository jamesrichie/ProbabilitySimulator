package distributions;

public class NegativeBinomial implements RandomDistribution {
    private final int r;
    private final double p;

    public NegativeBinomial(int r, double p) {
        assert(r >= 0);
        assert(0.0 <= p); assert(p <= 1.0);

        this.r = r;
        this.p = p;
    }

    public double sample() {
        Geometric randomVariable = new Geometric(p);

        double sum = 0.0;

        for (int i = 0; i < r; i++) {
            sum += randomVariable.sample();
        }
        return sum;
    }
}
