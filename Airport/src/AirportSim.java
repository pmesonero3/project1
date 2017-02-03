//YOUR NAME HERE

import java.util.TreeSet;


public class AirportSim {
    public static void main(String[] args) {

        Airport lax = new Airport("LAX", 10, 10, 20);

        AirportEvent landingEvent = new AirportEvent(5, lax, AirportEvent.PLANE_ARRIVES);
        Simulator.schedule(landingEvent);

        Simulator.stopAt(50);
        Simulator.run();
    }
}
