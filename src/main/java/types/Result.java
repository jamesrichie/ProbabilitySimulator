package types;

public class Result {
    public Double total;
    public int sampleSize;

    public Result() {
        total = 0.0;
        sampleSize = 0;
    }

    public Result(Double total, int sampleSize) {
        this.total = total;
        this.sampleSize = sampleSize;
    }

    public Result add(Result other) {
        return new Result(total + other.total, sampleSize + other.sampleSize);
    }
}
