package distributions;

public class Binomial {
    private final int n;
    private final Double p;

    public Binomial(int n, Double p) {
        assert(n >= 0);
        assert(0.0 <= p); assert(p <= 1.0);

        this.n = n;
        this.p = p;
    }

    public int sample() {
        Bernoulli randomVariable = new Bernoulli(p);

        int sum = 0;

        for (int i = 0; i < n; i++) {
            sum += randomVariable.sample();
        }
        return sum;
    }
}
