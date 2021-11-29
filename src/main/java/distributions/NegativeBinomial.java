package distributions;

public class NegativeBinomial {
    private final int r;
    private final Double p;

    public NegativeBinomial(int r, Double p) {
        assert(r >= 0);
        assert(0.0 <= p); assert(p <= 1.0);

        this.r = r;
        this.p = p;
    }

    public int sample() {
        Geometric randomVariable = new Geometric(p);

        int sum = 0;

        for (int i = 0; i < r; i++) {
            sum += randomVariable.sample();
        }
        return sum;
    }
}
