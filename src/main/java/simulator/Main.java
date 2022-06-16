package simulator;

import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Simulator simulator = new Simulator((int) Math.pow(2, 24), 64);
        simulator.run();
    }
}
