package distributions;

public class Geometric implements RandomVariable {
    private final Double p;

    public Geometric(Double p) {
        assert(0.0 <= p); assert(p <= 1.0);

        this.p = p;
    }

    public Double sample() {
        Bernoulli randomVariable = new Bernoulli(p);

        Double trials = 1.0;

        while (randomVariable.sample() != 1) {
            trials += 1;
        }
        return trials;
    }
}
