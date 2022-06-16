package distributions;

public class Binomial implements RandomDistribution {
    private final int n;
    private final double p;

    public Binomial(int n, double p) {
        assert(n >= 0);
        assert(0.0 <= p); assert(p <= 1.0);

        this.n = n;
        this.p = p;
    }

    public double sample() {
        Bernoulli randomVariable = new Bernoulli(p);

        double sum = 0.0;

        for (int i = 0; i < n; i++) {
            sum += randomVariable.sample();
        }
        return sum;
    }
}
