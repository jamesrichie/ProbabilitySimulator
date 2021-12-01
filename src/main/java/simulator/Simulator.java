package simulator;

import distributions.*;
import types.*;

import java.lang.Math;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Simulator {
    public static final ForkJoinPool POOL = new ForkJoinPool();
    public static final int SIMULATIONS = 10000000;
    public static final int SEQUENTIAL_CUTOFF = 10000;

    public static void main(String[] args) {
        Result result = POOL.invoke(new simulateTask(SIMULATIONS, SEQUENTIAL_CUTOFF));
        System.out.println(result.getAverage());
    }

    public static Result sequential(int simulationCount) {
        Poisson poiRV = new Poisson(2.0);
        Geometric geoRV = new Geometric(0.2);
        Binomial binRV = new Binomial(100,0.05);
        DiscreteUniform disRV = new DiscreteUniform(1,4);

        Result sequentialResult = new Result();

        for (int i = 0; i < simulationCount; i++) {
            boolean inside = true;

            while (inside) {
                int door = disRV.sample();

                inside = false;

                if (door == 1) {
                    sequentialResult.total += poiRV.sample();
                } else if (door == 2) {
                    sequentialResult.total += geoRV.sample();
                } else if (door == 3) {
                    sequentialResult.total += binRV.sample();
                } else {
                    sequentialResult.total += 2;
                    inside = true;
                }
            }
            sequentialResult.sampleSize += 1;
        }
        return sequentialResult;
    }

    public static class simulateTask extends RecursiveTask<Result> {
        int simulationCount, sequentialCutoff;

        public simulateTask(int simulationCount, int sequentialCutoff) {
            this.simulationCount = simulationCount;
            this.sequentialCutoff = sequentialCutoff;
        }

        protected Result compute() {
            if (simulationCount <= sequentialCutoff) {
                return sequential(simulationCount);
            }

            simulateTask left = new simulateTask((int) Math.floor(simulationCount / 2.0), sequentialCutoff);
            simulateTask right = new simulateTask((int) Math.ceil(simulationCount / 2.0), sequentialCutoff);

            right.fork();

            Result leftResult = left.compute();
            Result rightResult = right.compute();

            return leftResult.add(rightResult);
        }
    }
}