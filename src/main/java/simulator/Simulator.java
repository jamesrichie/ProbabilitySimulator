package simulator;

import java.lang.*;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

import types.*;
import distributions.*;
import sets.cardDeck.*;
import static sets.cardDeck.Ranks.*;
import static sets.cardDeck.Suits.*;

public class Simulator {
    public final ForkJoinPool POOL = new ForkJoinPool();
    public long simulations;       // number of simulations to run
    public long sequentialCutoff;  // number of simulations run per thread

    public Simulator(long simulations, long threadCount) {
        this.simulations = simulations;
        this.sequentialCutoff = simulations / threadCount;
    }

    public void run() {
        long startTime = System.nanoTime();
        Result result = POOL.invoke(new simulateTask(simulations, sequentialCutoff)); // final simulation result
        long endTime = System.nanoTime();

        System.out.println(result);
        System.out.println("\nTook " + (endTime - startTime) * Math.pow(10, -9) + " seconds to run " +  simulations + " simulations");
    }

    public static class simulateTask extends RecursiveTask<Result> {
        long simulationCount, sequentialCutoff;

        public simulateTask(long simulationCount, long sequentialCutoff) {
            this.simulationCount = simulationCount;
            this.sequentialCutoff = sequentialCutoff;
        }

        public Result compute() {
            if (simulationCount <= sequentialCutoff) {
                return sequential(simulationCount);
            }
            simulateTask left = new simulateTask((long) Math.floor(simulationCount / 2.0), sequentialCutoff);
            simulateTask right = new simulateTask((long) Math.ceil(simulationCount / 2.0), sequentialCutoff);

            right.fork();

            Result leftResult = left.compute();
            Result rightResult = right.join();

            return leftResult.add(rightResult);
        }

        public Result sequential(long simulationCount) {
            Result sequentialResult = new Result();

            for (long i = 0; i < simulationCount; i++) {
                // implement simulator here
            }
            return sequentialResult;
        }
    }
}