package types;

public class Result {
    public double total;
    public int sampleSize;

    public Result() {
        total = 0.0;
        sampleSize = 0;
    }

    public Result(double total, int sampleSize) {
        this.total = total;
        this.sampleSize = sampleSize;
    }

    public Result add(Result other) {
        return new Result(total + other.total, sampleSize + other.sampleSize);
    }

    public String toString() {
        return "Total: " + total + "\nSample Size: " + sampleSize + "\nAverage: " + (total / sampleSize);
    }
}
