//YOUR NAME HERE

//TODO add number of passengers, speed

public class Airplane {
    private String m_name;
    private int m_numberPassengers;
    private int m_maxSpeed;

    public Airplane(String name) {
        m_name = name;
    }

    public String getName() {
        return m_name;
    }

    public int getMaxSpeed() {return m_maxSpeed;}

    public int getNumberPassengers(){return m_numberPassengers;}

}
