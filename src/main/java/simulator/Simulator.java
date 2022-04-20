package simulator;

import distributions.*;
import types.*;

import java.lang.Math;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Simulator {
    public static final ForkJoinPool POOL = new ForkJoinPool();
    public static final int SIMULATIONS = 1000000;              // number of simulations to run
    public static final int SEQUENTIAL_CUTOFF = 10000;          // number of simulations run per thread

    public static void main(String[] args) {
        Result result = POOL.invoke(new simulateTask(SIMULATIONS, SEQUENTIAL_CUTOFF)); // final simulation result
    }

    public static Result sequential(int simulationCount) {
        Result sequentialResult = new Result();

        for (int i = 0; i < simulationCount; i++) {
             // implement simulator here
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