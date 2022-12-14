package types;

public class Result {
    public double totalSuccesses;
    public int totalSamples;

    public Result() {
        totalSuccesses = 0.0;
        totalSamples = 0;
    }

    public Result(double totalSuccesses, int totalSamples) {
        this.totalSuccesses = totalSuccesses;
        this.totalSamples = totalSamples;
    }

    public Result add(Result other) {
        return new Result(totalSuccesses + other.totalSuccesses, totalSamples + other.totalSamples);
    }

    public String toString() {
        return "Total Successes: " + totalSuccesses + "\nSample Size: " + totalSamples + "\nAverage success rate: " + (totalSuccesses / totalSamples);
    }
}
