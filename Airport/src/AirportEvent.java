//YOUR NAME HERE

public class AirportEvent extends Event {
    public static final int PLANE_ARRIVES = 0;
    public static final int PLANE_LANDED = 1;
    public static final int PLANE_DEPARTS = 2;

    private Airplane m_airplane;


    AirportEvent(Airplane airplane, double delay, EventHandler handler, int eventType) {
        super(delay, handler, eventType);
        m_airplane = airplane;
    }
}
