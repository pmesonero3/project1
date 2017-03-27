//YOUR NAME HERE

import java.util.Random;

//TODO add number of passengers, speed
public class Airplane {
    private String m_name;
    private int m_numberPassengers;
    private int m_maxSpeed;
    private int m_maxPassengers;

    // an airplane has a depart airport

    public Airplane(String name,int maxPass,int maxspeed) {
        Random rn = new Random();
        m_name = name;
        m_maxSpeed = maxspeed;
        m_numberPassengers = rn.nextInt(maxPass+1);
        m_maxPassengers = maxPass;
    }

    public String getName() {
        return m_name;
    }

    public int getMaxSpeed() {return m_maxSpeed;}

    public int getNumberPassengers(){return m_numberPassengers;}

    public void setName(String name){m_name = name;}

    public void setNumberPassengers(int n){m_numberPassengers = n;}
    public void setMaxSpeed(int n ){m_maxSpeed = n;}
    public int getMaxPassengers() {return m_maxPassengers;}


}
