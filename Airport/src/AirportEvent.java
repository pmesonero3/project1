// Philippe MESONERO

import java.util.Random;

public class AirportEvent extends Event {
    public static final int PLANE_ARRIVES = 0;
    public static final int PLANE_LANDED = 1;
    public static final int PLANE_DEPARTS = 2;

    private Airplane m_airplane;

    // an Airport event has an
    AirportEvent(Airplane airplane, double delay, EventHandler handler, int eventType) {
        super(delay, handler, eventType);
        m_airplane = airplane;
        Random rn = new Random();

        // if new departure we set a random numbers of passengers for the flight.
        if (eventType==PLANE_DEPARTS)
        {airplane.setNumberPassengers(rn.nextInt(airplane.getMaxPassengers()+1));}

        // once you create an event it is automatically scheduled.
        Simulator.schedule(this);
    }

    public Airplane getAirplane(){return  m_airplane;}
}
