package simulator;

import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator((long) Math.pow(2, 30), 64);
        simulator.run();
    }
}
