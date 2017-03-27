//Philippe MESONERO


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;


/***
 * We run the simulation 1000 time and at the and of each simulation we write in a test file the total
 * of passengers that landed, took off and circling time. then we can parse the texte file into a excel sheet to
 * extract important data.
 * ***/

public class AirportSim {
    public static List<Airport> airportlist = new LinkedList<Airport>();

public static List<Airport> getAirportList (){
    return airportlist;
}

    public static void main(String[] args) {
        try {
           //opening of the txt file to write results in it.
            String content = "This is the content to write into a file";
            File file = new File("TimeToLand12345.txt");
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);



        // the flight time between airports have been fixed according to the real time travel.
        int timeflightParis[] = {0, 1, 13, 15, 20};
        int timeflightLondon[] = {1, 0, 12, 16, 19};
        int timeflightLosAngeles[] = {13, 12, 0, 15, 9};
        int timeflightPekin[] = {15, 16, 15, 0, 17};
        int timeflightLaPaz[] = {20, 19, 9, 17, 0};
        /**
         * TIME TRAVE
         *             Paris | London | Los Angeles | Pekin | La Paz
         * Paris        x       1h         13h         15h       20h
         * London       1h       x         12h         16h      19h
         * Los Angeles  13h     12h         x          15h       9h
         * Pekin        15h      16h        15h         x        17h
         * La PAz       20h      19h        9h          17h       x
         * */
            for (int k = 0; k<1000;k++){

        // Time required on the ground = 70 min in 2000
        //cf : http://www.boeing.com/commercial/aeromagazine/aero_01/textonly/t01txt.html
                double timeland = 3;
        Airport cdg = new Airport("CDG", "Charles de Gaulle Airport Paris", 0.5 ,1.1, timeflightParis);
        Airport lhr = new Airport("LHR", "London Heathrow Airport", 0.5, 1.1, timeflightLondon);
        Airport lax = new Airport("LAX", "Los Angeles Airport", 0.5, 1.1, timeflightLosAngeles);
        Airport pek = new Airport("PEK", "Beijing Capital International Airport", 0.5, 1.1, timeflightPekin);
        Airport lpb = new Airport("LPB", "El Alto airport in La Paz", 0.5, 1.1, timeflightLaPaz);

        //airport list is only used for pickling a random aiport when departing.
        airportlist.add(0, cdg);
        airportlist.add(1, lhr);
        airportlist.add(2, lax);
        airportlist.add(3, pek);
        airportlist.add(4, lpb);


        Airplane boeing747_1 = new Airplane("Boeing 747 AX001", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_2 = new Airplane("Boeing 747 AX002", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_3 = new Airplane("Boeing 747 AX003", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_4 = new Airplane("Boeing 747 AX004", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_5 = new Airplane("Boeing 747 AX005", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_6 = new Airplane("Boeing 747 AX006", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_7 = new Airplane("Boeing 747 AX007", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_8 = new Airplane("Boeing 747 AX008", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_9 = new Airplane("Boeing 747 AX009", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_10 = new Airplane("Boeing 747 AX010", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_11 = new Airplane("Boeing 747 AX011", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_12 = new Airplane("Boeing 747 AX012", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_13 = new Airplane("Boeing 747 AX013", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_14 = new Airplane("Boeing 747 AX014", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_15 = new Airplane("Boeing 747 AX015", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_16 = new Airplane("Boeing 747 AX016", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_17 = new Airplane("Boeing 747 AX017", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_18 = new Airplane("Boeing 747 AX018", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_19 = new Airplane("Boeing 747 AX019", 416, 1000); //remark maxspeed in km/h
        Airplane boeing747_20 = new Airplane("Boeing 747 AX020", 416, 1000); //remark maxspeed in km/h

        AirportEvent arrived1 = new AirportEvent(boeing747_1, 5, cdg, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived2 = new AirportEvent(boeing747_2, 5, lax, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived3 = new AirportEvent(boeing747_3, 5, lhr, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived4 = new AirportEvent(boeing747_4, 5, pek, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived5 = new AirportEvent(boeing747_5, 5, lpb, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived6 = new AirportEvent(boeing747_6, 10, cdg, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived7 = new AirportEvent(boeing747_7, 10, lax, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived8 = new AirportEvent(boeing747_8, 10, lhr, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived9 = new AirportEvent(boeing747_9, 10, pek, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived10 = new AirportEvent(boeing747_10, 12, lpb, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived11 = new AirportEvent(boeing747_11, 12, cdg, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived12 = new AirportEvent(boeing747_12, 12, lax, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived13 = new AirportEvent(boeing747_13, 12, lhr, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived14 = new AirportEvent(boeing747_14, 12, pek, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived15 = new AirportEvent(boeing747_15, 12, lpb, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived16 = new AirportEvent(boeing747_16, 15, cdg, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived17 = new AirportEvent(boeing747_17, 15, lax, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived18 = new AirportEvent(boeing747_18, 15, lhr, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived19 = new AirportEvent(boeing747_19, 15, pek, AirportEvent.PLANE_ARRIVES);
        AirportEvent arrived20 = new AirportEvent(boeing747_20, 15, lpb, AirportEvent.PLANE_ARRIVES);


        Simulator.stopAt(168); //runs for 168 hours ~ 1 week
        Simulator.run();

        System.out.println("Airport " + cdg.getName() + " had " + cdg.getTotalPassDeparted() + " passengers departing and " + cdg.getTotalPassLanded() + " landing with " + cdg.getCirclingtime() + " circling times");
        System.out.println("Airport " + lhr.getName() + " had " + lhr.getTotalPassDeparted() + " passengers departing and " + lhr.getTotalPassLanded() + " landing with " + lhr.getCirclingtime() + " circling times");
        System.out.println("Airport " + lax.getName() + " had " + lax.getTotalPassDeparted() + " passengers departing and " + lax.getTotalPassLanded() + " landing with " + lax.getCirclingtime() + " circling times");
        System.out.println("Airport " + pek.getName() + " had " + pek.getTotalPassDeparted() + " passengers departing and " + pek.getTotalPassLanded() + " landing with " + pek.getCirclingtime() + " circling times");
        System.out.println("Airport " + lpb.getName() + " had " + lpb.getTotalPassDeparted() + " passengers departing and " + lpb.getTotalPassLanded() + " landing with " + lpb.getCirclingtime() + " circling times");

        // one line of the Text file represent
                //CDG-DEPART CDG-Landing CDG-Circling LHR-DEPART LHR-Landing LHR-Circling LAX-DEPART LAX-Landing LAX-Circling PEK-DEPART PEK-Landing PEK-Circling LPB-DEPART LPB-Landing LPB-Circling
        bw.write(cdg.getTotalPassDeparted() + " " + cdg.getTotalPassLanded() + " " + cdg.getCirclingtime()+ " " +
                    lhr.getTotalPassDeparted() + " " + lhr.getTotalPassLanded() + " " + lhr.getCirclingtime()+" " +
                    lax.getTotalPassDeparted() + " " + lax.getTotalPassLanded() + " " + lax.getCirclingtime()+ " " +
                    pek.getTotalPassDeparted() + " " + pek.getTotalPassLanded() + " " + pek.getCirclingtime()+" "+
                    lpb.getTotalPassDeparted() + " " + lpb.getTotalPassLanded() + " " + lpb.getCirclingtime()+ "\n");
        Simulator.setInstance();
            }
        bw.close(); // Be sure to close BufferedWriter
        }
        catch(IOException ioe){

        }

    }

}


