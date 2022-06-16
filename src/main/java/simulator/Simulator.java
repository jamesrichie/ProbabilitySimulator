package simulator;

import distributions.*;
import types.*;

import java.lang.Math;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class Simulator {
    public final ForkJoinPool POOL = new ForkJoinPool();
    public int simulations;       // number of simulations to run
    public int sequentialCutoff;  // number of simulations run per thread

    public Simulator(int simulations, int threadCount) {
        this.simulations = simulations;
        this.sequentialCutoff = simulations / threadCount;
    }

    public void run() {
        long startTime = System.nanoTime();
        Result result = POOL.invoke(new simulateTask(simulations, sequentialCutoff)); // final simulation result
        long endTime = System.nanoTime();

        System.out.println("Result (took " + (endTime - startTime) * Math.pow(10, -9) + " seconds)");
        System.out.println(result);
    }

    public class simulateTask extends RecursiveTask<Result> {
        int simulationCount, sequentialCutoff;

        public simulateTask(int simulationCount, int sequentialCutoff) {
            this.simulationCount = simulationCount;
            this.sequentialCutoff = sequentialCutoff;
        }

        public Result compute() {
            if (simulationCount <= sequentialCutoff) {
                return sequential(simulationCount);
            }
            simulateTask left = new simulateTask((int) Math.floor(simulationCount / 2.0), sequentialCutoff);
            simulateTask right = new simulateTask((int) Math.ceil(simulationCount / 2.0), sequentialCutoff);

            right.fork();

            Result leftResult = left.compute();
            Result rightResult = right.join();

            return leftResult.add(rightResult);
        }

        public Result sequential(int simulationCount) {
            Result sequentialResult = new Result();
            RandomDistribution generator = new Poisson(50.0);

            for (int i = 0; i < simulationCount; i++) {
                // implement simulator here
                sequentialResult.total += generator.sample();
                sequentialResult.sampleSize++;
            }
            return sequentialResult;
        }
    }
}