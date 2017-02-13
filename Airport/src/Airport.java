//Philippe MESONERO

/**
 * How to choose the the airport you are going to ?
 *
 *
 *
 * **/
import java.util.*;

public class Airport implements EventHandler {

    //TODO add landing and takeoff queues, random variables

    private int m_inTheAir;
    private int m_onTheGround;

    private boolean m_freeToLand;

    private double m_runwayTimeToLand;
    private double m_requiredTimeOnGround;
    private int m_flightTime[]; // each airport has an array of flight time respect to where a plane goes.

    private String m_airportName;
    private String m_description; //Airport description

    private int m_totatlPassLanded;// total of all the passengers that have landed form this
    private int m_totalPassDeparted; // total of all the passengers that have departed form this
    private int m_circlingTime;

    private LinkedList<Airplane> m_queueLanding; // each airport has its own queue for landing
    private LinkedList<Airplane> m_queueDeparting;


    public Airport(String name,String description, double runwayTimeToLand, double requiredTimeOnGround, int flightTime[]) {
        m_airportName = name;
        m_inTheAir =  0;
        m_onTheGround = 0;
        m_freeToLand = true;
        m_runwayTimeToLand = runwayTimeToLand;
        m_requiredTimeOnGround = requiredTimeOnGround;
        m_flightTime = flightTime;
        m_description = description;
        m_totatlPassLanded=0;
        m_totalPassDeparted=0;
        m_circlingTime=0;
        m_queueLanding = new LinkedList<Airplane>();
        m_queueDeparting = new LinkedList<Airplane>();

    }

    public String getName() {
        return m_airportName;
    }

    public int getTotalPassLanded(){return m_totatlPassLanded;}

    public int getTotalPassDeparted(){return m_totalPassDeparted;}

    public int getCirclingtime (){return m_circlingTime;}

    public void handle(Event event) {
        AirportEvent airEvent = (AirportEvent)event;

        switch(airEvent.getType()) {
            case AirportEvent.PLANE_ARRIVES:

                System.out.println(Simulator.getCurrentTime() + ": Plane "+airEvent.getAirplane().getName() + " arrived at airport " +m_airportName+" with :"+airEvent.getAirplane().getNumberPassengers() +" passengers.");

                // if the airplane is not already in the queue, we add it
                if (!m_queueLanding.contains(airEvent.getAirplane()))
                {
                    m_queueLanding.add(airEvent.getAirplane());
                    m_inTheAir++;}
                /**FREE TO LAND***/
                //when free to land we landed the element 0 of the linked list i.e the one that that has been waiting the longest
                if(m_freeToLand) {// had to add the airplane creating a new event111
                    AirportEvent landedEvent = new AirportEvent(m_queueLanding.pollFirst(), m_runwayTimeToLand, this, AirportEvent.PLANE_LANDED);
                    m_freeToLand =false;
                }
               /***
                * NOT FREE TO LAND**/

                // if we can't land we increment circling time and notice the console
                else{m_circlingTime++;
                    //AirportEvent airplaneWaitsInTheAir = new AirportEvent(((AirportEvent) event).getAirplane(),1, this, AirportEvent.PLANE_ARRIVES);
                    System.out.println(Simulator.getCurrentTime() + ": Plane "+airEvent.getAirplane().getName() + " stuck in the air at " + m_airportName);
                }
                break;

            case AirportEvent.PLANE_DEPARTS:
                // when you are free to land you are free to depart.
                // I decided to give priority to the landing airplanes due to the gas restriction in the air.
                if (!m_queueDeparting.contains(airEvent.getAirplane())) {
                    m_queueDeparting.add(airEvent.getAirplane());
                }
                if (m_freeToLand) {
                    m_onTheGround--;
                    // update of the numbers of passengers departing.


                    Airport airportDestination = chooseAirport(5);
                    int flightTime = 0;
                    switch (airportDestination.getName()) {
                        case "CDG":
                            flightTime = m_flightTime[0];
                            break;
                        case "LHR":
                            flightTime = m_flightTime[1];
                            break;
                        case "LAX":
                            flightTime = m_flightTime[2];
                            break;
                        case "PEK":
                            flightTime = m_flightTime[3];
                            break;
                        case "LPB":
                            flightTime = m_flightTime[4];
                            break;
                    }
                    // when a plane depart, it took the first element of the waiting list (the one that has been waiting the most).
                    AirportEvent takeoffEvent = new AirportEvent(m_queueDeparting.pollFirst(), flightTime, airportDestination, AirportEvent.PLANE_ARRIVES);
                    System.out.println(Simulator.getCurrentTime() + ": Plane " + takeoffEvent.getAirplane().getName() + " departs from airport " + m_airportName + " with " + airEvent.getAirplane().getNumberPassengers() + " passengers.");

                    m_totalPassDeparted += takeoffEvent.getAirplane().getNumberPassengers();

                }
                else {
                    System.out.println(Simulator.getCurrentTime() + ": Plane " + airEvent.getAirplane().getName()+ " is stuck on ground at "+ m_airportName);
                    m_circlingTime++;
                }
                break;

            case AirportEvent.PLANE_LANDED:
                m_inTheAir--;

                System.out.println(Simulator.getCurrentTime() + ": Plane "+airEvent.getAirplane().getName() + " landed at airport "+ m_airportName+" with "+airEvent.getAirplane().getNumberPassengers() +" passengers.");

                //update of the total numbers of passengers landed
                m_totatlPassLanded += airEvent.getAirplane().getNumberPassengers();
                AirportEvent departureEvent = new AirportEvent(airEvent.getAirplane(),m_requiredTimeOnGround, this,  AirportEvent.PLANE_DEPARTS);

                // when a plane has landed the runway is free, and the other planes waiting can land, then the other planes waiting to take off can fly
                if (!m_queueLanding.isEmpty()){
                    AirportEvent landedEvent = new AirportEvent(m_queueLanding.pollFirst(), m_runwayTimeToLand, this, AirportEvent.PLANE_LANDED);
                    m_freeToLand =false;
                }
                else if (m_queueLanding.isEmpty() && !m_queueDeparting.isEmpty()){
                    AirportEvent landedEvent = new AirportEvent(m_queueDeparting.pollFirst(), 0, this, AirportEvent.PLANE_DEPARTS);
                    m_freeToLand=true;
                }

                else{m_freeToLand=true;}
                break;
        }
    }

    public Airport chooseAirport(int airportNumber){
        Random rn = new Random();
        int airport =rn.nextInt(airportNumber);

        //pick a random number between 0 and 4 to choose a random airport destination
        // the if condition make sure  that we don't choose the same destination that one we are already at.
        if (    (airport == 0 && m_airportName.equals("CDG") )
                ||(airport==1 && m_airportName.equals("LHR"))
                ||(airport==2 && m_airportName.equals("LAX"))
                ||(airport==3 && m_airportName.equals("PEK"))
                ||(airport==4 && m_airportName.equals("LPB")))
        {return chooseAirport(5);}
        else {
            return AirportSim.getAirportList().get(airport);
        }

    }
}
